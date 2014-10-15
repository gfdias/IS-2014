package project.com;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBElement;

import project.com.schema.Topictype;
import sender.Sender;

public class WebCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		ReadWeb a=new ReadWeb();
		ArrayList<Header> headers=a.getFromWeb("http://edition.cnn.com");
		for (Header header : headers) {
			
			//get topic
		     TopicNews aux = new TopicNews(header.getUrl(),header.getName());
		     JAXBElement<Topictype> topic=aux.fetchLatestNews();
		     
		     //topic to xml
		     ImportExportXml newExport = new ImportExportXml();
		     String xmlString= newExport.getXMLString(topic);
		     
		     
		     //send to jms
		     Sender client = new Sender();
			try {
				client.sendAsync(xmlString);
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		    try {
				client.stop();
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
		
		System.out.println("End Read Web");	
		
	}
	

}
