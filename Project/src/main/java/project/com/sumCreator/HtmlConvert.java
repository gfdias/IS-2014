package project.com.sumCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.xml.transform.*;

import project.com.ImportExportXml;
import project.com.schema.Topictype;
import sender.Receiver;
import statsProducer.StatsProducer;

import java.net.*;
import java.io.*;


public class HtmlConvert implements MessageListener{

	String clientID="HtmlConvert";
	public static void main(String[] args) {

		getNews();

		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(
							"template.xsl"));

			transformer.transform(new javax.xml.transform.stream.StreamSource(
					"U.S..xml"), new javax.xml.transform.stream.StreamResult(
							new FileOutputStream("new.html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			//System.out.println("RECEIVED NEWS");
			ImportExportXml aux= new ImportExportXml();
			Topictype a= aux.stringToTopic(tmsg.getText());
			System.out.println(a.getTopicname());
			saveXml(a, "Received_Html/"+a.getTopicname());

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
