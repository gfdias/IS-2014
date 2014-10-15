package project.com;

import java.util.ArrayList;

import javax.xml.bind.JAXBElement;

import project.com.schema.Topictype;

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
		     
		     System.out.println(xmlString);
		}
		
		System.out.println("End Read Web");	
		
	}
	

}
