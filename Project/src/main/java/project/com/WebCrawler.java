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
	
		/*ReadWeb a=new ReadWeb();
		ArrayList<Header> headers=a.getFromWeb("http://edition.cnn.com");
	    ImportExportXml newExport = new ImportExportXml();

		for (Header header : headers) {
			
			//get topic
		     TopicNews aux = new TopicNews(header.getUrl(),header.getName());
		     JAXBElement<Topictype> topic=aux.fetchLatestNews();
		     
		     //topic to xml
		     String xmlString= newExport.getXMLString(topic);
		     
		     
		     //send to jms
		     Sender client = new Sender();
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
		*/
		System.out.println("End Read Web");	
		
		System.out.println("See if have something to send to jms server");
		somethingToSend();
	}
	
	public static void somethingToSend(){
		File folder = new File("Save/");
	    ImportExportXml newExport = new ImportExportXml();

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("Save/"+listOfFiles[i].getName());

				Topictype a = newExport.importTopic("Save/"+listOfFiles[i].getName());
				System.out.println("asds");
				String xmlString = newExport.getXMLString(a);
				System.out.println("Try to send " + listOfFiles[i].getName());

				Sender client = new Sender();
				try {
					client.sendAsync(xmlString);
					client.stop();

				} catch (CommunicationException w) {
					System.out.println("JMS IS DOWN");
				} catch (JMSException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void saveXml(JAXBElement<Topictype> topic,String topicName){
	    ImportExportXml newExport = new ImportExportXml();
	    if(newExport.exportReport(topic, topicName)){
			System.out.println("XML SAVED");
	    }
	}
}
