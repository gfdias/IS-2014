package statsProducer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

import project.com.Header;
import project.com.ImportExportXml;
import project.com.NewsCon;
import project.com.schema.Newstype;
import project.com.schema.Topictype;
import sender.Receiver;

public class StatsProducer implements MessageListener {
	String clientID="StatsProducer1";
	static int numOfNews;
	public static ArrayList<Header> headerDates=null;
	
	public static void main(String args[]) {
		importHeader();
		numOfNews=0;
		
		System.out.println("Stats prducer started");

		StatsProducer aux = new StatsProducer();
		boolean tryConnection = false;
		do {
			System.out.print("Connecting....");
			if (!aux.setClient()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Try again? Y/n");
				String s;
				try {
					s = br.readLine();
					if (s.isEmpty() || s.toLowerCase().startsWith("y")) {
						tryConnection = true;
					}else{
						tryConnection=false;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} while (tryConnection);
		System.out.println("Stats producer ended");
		System.exit(0);
	}

	public boolean setClient() {
		Receiver client;
		boolean ended = false;
		try {
			client = new Receiver(clientID);
			client.getRecv().setMessageListener(this);
			System.out.println("Connection established...");
			System.in.read();
			saveHeader();
			ended = true;
			client.stop();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("JMS CONNECTION FAIL");
		}
		return ended;
	}
	public void addDateToHeader(String headerName,String url,Date date){
		boolean findHeader=false;
		for (int i = 0; i < headerDates.size(); i++) {
			if(!headerDates.get(i).getName().equals(headerName))continue;
			ArrayList<NewsCon> aux=headerDates.get(i).getNewsArray();
			for (int j = 0; j < aux.size(); j++) {
				if (!aux.get(j).getUrl().equals(url)) continue;
				System.out.println("repited");
				return;
			}
			NewsCon add=new NewsCon(date, url);
			aux.add(add);
			return;
		}
		Header aux=new Header(headerName);
		ArrayList<NewsCon> helper=aux.getNewsArray();
		NewsCon add=new NewsCon(date, url);
		helper.add(add);
		headerDates.add(aux);
		
	}
	
	public void validationDates(){
		numOfNews=0;
		ArrayList<Header> current=new ArrayList<Header>();
		for (Header header : headerDates) {
			System.out.println(header.getName());
			ArrayList<Integer> a=new ArrayList<Integer>();
			Header myHeader=new Header(header.getName());
			
			for (int j = 0; j < header.getNewsArray().size(); j++) {
				NewsCon aux=header.getNewsArray().get(j);
							
				long diff = new Date().getTime()- aux.getDate().getTime() ;
				long hours = TimeUnit.MILLISECONDS.toHours(diff); 
				
				if(hours<12){
					myHeader.getNewsArray().add(aux);
					numOfNews++;
				}
				else{
					a.add(j);
				}
			}
			current.add(myHeader);
			
		}
		writeData(current);
		
		
	}
	 public static boolean validateAgainstXSD(String xmlString, String loc)
	{
		InputStream xml=null;
		InputStream xsd=null;

		try {
			xml = IOUtils.toInputStream(xmlString, "UTF-8");
			xsd = new FileInputStream(loc);

		} catch (IOException e) {
			e.printStackTrace();
		}				
		
		
	    try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    }
	    catch(Exception ex)
	    {
	        return false;
	    }
	}
	
	public void onMessage(Message msg) {
		TextMessage tmsg = (TextMessage) msg;
		try {
			ImportExportXml aux= new ImportExportXml();
		
			String msg1=tmsg.getText();				
			
			boolean isValid = validateAgainstXSD(msg1, "newscontent.xsd");
			if (isValid == true) {
				Topictype a = aux.stringToTopic(msg1);
				System.out.println(a.getTopicname().name());
				for (Newstype news : a.getNews()) {
					Date date = news.getDate().toGregorianCalendar().getTime();
					addDateToHeader(a.getTopicname().name(), news.getUrl(),
							date);
				}
				validationDates();
			}
			
			System.out.println("news with less than 12h-> "+numOfNews);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData(ArrayList<Header> b){
		PrintWriter out;
		try {
			out = new PrintWriter("Stats/stats.txt");
			out.println(numOfNews + " News with less than 12 hours");
			
			for (Header header : b) {
				out.println("\n"+header.getName());
				ArrayList<Integer> a=new ArrayList<Integer>();
				for (int j = 0; j < header.getNewsArray().size(); j++) {
					NewsCon aux=header.getNewsArray().get(j);
					out.println(aux.getDate()+"\t"+"http://edition.cnn.com"+aux.getUrl());
				}
			}
			
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void saveHeader(){
	try
    {
       FileOutputStream fileOut =new FileOutputStream("Stats/header.ser");
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(headerDates);
       out.close();
       fileOut.close();
       
       System.out.printf("Serialized data is saved in /Stats/header.ser");
    }catch(IOException i)
    {
        i.printStackTrace();
    }
	}
	
	public static void importHeader(){
		 try
	      {
	         FileInputStream fileIn = new FileInputStream("Stats/header.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         headerDates = (ArrayList<Header>) in.readObject();
	         
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	    	  System.out.println("Creating new");
	    	  headerDates= new ArrayList<Header>();
	       //  i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}
