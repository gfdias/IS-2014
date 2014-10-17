package statsProducer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageListener;
import javax.naming.NamingException;

import sender.Receiver;

public class StatsProducer implements MessageListener {
	String clientID="StatsProducer";

	public static void main(String args[]) {

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
			e.printStackTrace();
			System.out.println("JMS CONNECTION FAIL");
		}
		return ended;
	}

	public void onMessage(Message msg) {
		TextMessage tmsg = (TextMessage) msg;
		try {
			System.out.println(tmsg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
