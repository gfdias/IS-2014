package project.com.sumCreator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import project.com.ImportExportXml;
import project.com.schema.Topictype;
import sender.Receiver;


public class HtmlConvert implements MessageListener{

	String clientID="HtmlConvert";
	public static void main(String[] args) {

		getNews();

	}

	public static void getNews() {

		System.out.println("Html get news");

		HtmlConvert aux = new HtmlConvert();
		boolean tryConnection = false;
		do {
			System.out.print("Connecting....");
			if (!aux.setClient()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.print("Try again? Y/n");
				String s;
				try {
					s = br.readLine();
					if (s.isEmpty() || s.toLowerCase().startsWith("y")) {
						tryConnection = true;
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
			ended = true;
			client.stop();
		} catch (Exception e) {
			System.out.println("JMS CONNECTION FAIL");
		}
		return ended;
	}
	public static void saveXml(Topictype topic, String topicName) {
		ImportExportXml newExport = new ImportExportXml();
		if (newExport.exportReport(topic, topicName)) {
			System.out.println("XML SAVED");
		}
	}

	public void onMessage(Message msg) {
		TextMessage tmsg = (TextMessage) msg;
		try {
			ImportExportXml aux= new ImportExportXml();
			Topictype a= aux.stringToTopic(tmsg.getText());
			System.out.println(a.getTopicname());
						
			saveXml(a, "Received_Html/"+a.getTopicname());
			try {
				InputStream inXml = new FileInputStream("Received_Html/"+a.getTopicname()+".xml");
				InputStream inXsd = new FileInputStream("newscontent.xsd");
				boolean isValid = validateAgainstXSD(inXml, inXsd);
				if(isValid == true){
					System.out.println("Converte essa merda");
					convertToHtml(a.getTopicname().toString());
				}


			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validateAgainstXSD(InputStream xml, InputStream xsd)
	{
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
	
	public void convertToHtml(String fileName){
		
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(
							"template.xsl"));

			transformer.transform(new javax.xml.transform.stream.StreamSource(
					"Received_Html/"+fileName+".xml"), new javax.xml.transform.stream.StreamResult(
							new FileOutputStream("Received_Html/"+fileName+".html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
