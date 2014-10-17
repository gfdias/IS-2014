package project.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.jms.JMSException;
import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBElement;

import project.com.schema.Topictype;
import sender.Sender;

public class WebCrawler {

	public static void main(String[] args) throws JMSException, NamingException {
		// TODO Auto-generated method stub
		/*Sender client = new Sender();
		client.sendAsync("AMO ISTO");
		client.stop();*/
		
		if (haveFilesToSend()) {
			somethingToSend();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("All send\nDo you want to read the web? y/N");
			String s;
			try {
				s = br.readLine();
				if (s.isEmpty() || s.toLowerCase().startsWith("n")) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ReadWeb a = new ReadWeb();
		ArrayList<Header> headers = a.getFromWeb("http://edition.cnn.com");
		ImportExportXml newExport = new ImportExportXml();

		ArrayList<String> news = new ArrayList<String>();

		for (Header header : headers) {
			// get topic
			TopicNews aux = new TopicNews(header.getUrl(), header.getName());
			Topictype topic = aux.fetchLatestNews();

			// topic to xml
			header.setNews(topic);
		}

		System.out.println("SENDING NEWS.....");
		for (Header header : headers) {
			try {
				Sender client = new Sender();
				String xmlString = newExport.getXMLString(header.getNews());
				client.sendAsync(xmlString);
				client.stop();

			} catch (Exception w) {
				System.out.println("JMS IS DOWN");
				// save to files
				saveXml(header.getNews(), header.getName());
			}
		}
		System.out.println("End Read Web");

	}

	public static boolean haveFilesToSend() {
		File folder = new File("Save/");
		boolean somethingToSend = false;
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (!listOfFiles[i].getName().endsWith(".xml"))
				continue;
			somethingToSend = true;
		}
		if (!somethingToSend) {
			System.out.println("Nothing to send to jms server");
		} else {
			System.out.println("Some files to send");
		}
		return somethingToSend;
	}

	public static void somethingToSend() {
		File folder = new File("Save/");
		ImportExportXml newExport = new ImportExportXml();

		File[] listOfFiles = folder.listFiles();

		int tryNumber = 0;
		do {
			ArrayList<String> news = new ArrayList<String>();

			System.out.println("Rading files");
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (!listOfFiles[i].getName().endsWith(".xml"))
						continue;
					Topictype a = newExport.importTopic("Save/"
							+ listOfFiles[i].getName());
					String xmlString = newExport.getXMLString(a);
					System.out.println("Imported " + listOfFiles[i].getName());
					news.add(xmlString);
				}
			}
			try {
				for (int i = 0; i < news.size(); i++) {
					Sender client = new Sender();

					try {
						System.out.println("Sending.....");
						client.sendAsync(news.get(i));
					}finally{
						client.stop();
						File file = new File("Save/" + listOfFiles[i].getName());
						file.delete();
					}
				}
			} catch (CommunicationException w) {
				System.out.println("COULD NO SEND->JMS IS DOWN");
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			tryNumber++;
			try {
				if (haveFilesToSend()) {
					System.out.println("SLEEPING FOR 5 SEC");
					Thread.sleep(5000);
				} else {
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (tryNumber < 6);
	}

	public static void saveXml(Topictype topic, String topicName) {
		ImportExportXml newExport = new ImportExportXml();
		if (newExport.exportReport(topic, "Save/"+topicName)) {
			System.out.println("XML SAVED");
		}
	}
}
