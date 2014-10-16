package project.com;

import java.io.File;
import java.util.ArrayList;

import javax.jms.JMSException;
import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBElement;

import project.com.schema.Topictype;
import sender.Sender;

public class WebCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		ReadWeb a=new ReadWeb();
		ArrayList<Header> headers=a.getFromWeb("http://edition.cnn.com");
	    ImportExportXml newExport = new ImportExportXml();
	    Sender client = new Sender();

		for (Header header : headers) {
			
			//get topic
		     TopicNews aux = new TopicNews(header.getUrl(),header.getName());
		     Topictype topic=aux.fetchLatestNews();
		     
		     //topic to xml
		     String xmlString= newExport.getXMLString(topic);
		     
		     
		     //send to jms
			try {
				client.sendAsync(xmlString);
				client.stop();

			}catch (CommunicationException w){
				System.out.println("JMS IS DOWN");
				//save to files
				saveXml(topic,header.getName());
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			
		}
		
		

		System.out.println("End Read Web");	
		
		somethingToSend();
	}
	
	public static void somethingToSend(){
		File folder = new File("Save/");
	    ImportExportXml newExport = new ImportExportXml();

		File[] listOfFiles = folder.listFiles();

		int tryNumber=0;
		do{
			System.out.println("See if have something to send to jms server");
			boolean somethingToSend=false;
			for (int i = 0; i < listOfFiles.length; i++) {
				if (!listOfFiles[i].getName().endsWith(".xml")) continue;
				somethingToSend=true;
			}
			if (!somethingToSend){
				System.out.println("Nothing to send to jms server");
				return;
			}else{
				System.out.println("Some files to send");

			}
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (!listOfFiles[i].getName().endsWith(".xml")) continue;
					Topictype a = newExport.importTopic("Save/"+listOfFiles[i].getName());
					String xmlString = newExport.getXMLString(a);
					System.out.println("Try to send " + listOfFiles[i].getName());
	
					Sender client = new Sender();
					try {
						client.sendAsync(xmlString);
						client.stop();
						File file=new File("Save/"+listOfFiles[i].getName());
						file.delete();
	
					} catch (CommunicationException w) {
						System.out.println("COULD NO SEND->JMS IS DOWN");
					} catch (JMSException e) {
						e.printStackTrace();
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
			}
			tryNumber++;
			try {
				System.out.println("SLEEPING FOR 10 SEC");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while (tryNumber<6);
	}
	
	
	public static void saveXml(Topictype topic,String topicName){
	    ImportExportXml newExport = new ImportExportXml();
	    if(newExport.exportReport(topic, topicName)){
			System.out.println("XML SAVED");
	    }
	}
}
