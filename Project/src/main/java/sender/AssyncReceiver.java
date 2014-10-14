package sender;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import project.com.TopicNews;

public class AssyncReceiver implements MessageListener {
	private ConnectionFactory cf;
	private Connection c;
	private Session s;
	private Destination d;
	private MessageConsumer mc;

	public AssyncReceiver() throws NamingException, JMSException {
		InitialContext init = new InitialContext();
		this.cf = (ConnectionFactory) init.lookup("jms/RemoteConnectionFactory");
		this.d = (Destination) init.lookup("jms/queue/News");
		this.c = (Connection) this.cf.createConnection("joao", "passwd");
		this.c.setClientID("HTML_SUMMARY_7"
				+ "");
		this.c.start();
		this.s = this.c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		
		
		/*
        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection();
        topic = (Topic) iniCtx.lookup("topic/testTopic");
        session = conn.createTopicSession(false,
                                          TopicSession.AUTO_ACKNOWLEDGE);*/

		mc =s.createDurableSubscriber(this.d,"asda");
		mc.setMessageListener(this);
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

	private void close() throws JMSException {
		this.c.stop();
		this.s.close();
		this.c.close();

	}

	/**
	 * @param args
	 * @throws JMSException
	 * @throws NamingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NamingException,
		JMSException, IOException {
		AssyncReceiver r = new AssyncReceiver();
		System.in.read();
		r.close();
	}

}
