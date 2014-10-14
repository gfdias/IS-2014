package sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.CommunicationException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender {
	private ConnectionFactory cf;
	private Connection c;
	private Session s;
	private Destination d;
	private MessageProducer mp;

	public Sender() throws NamingException, JMSException {
		InitialContext init = new InitialContext();
		this.cf = (ConnectionFactory) init.lookup("jms/RemoteConnectionFactory");
		this.d = (Destination) init.lookup("jms/queue/News");
		this.c = (Connection) this.cf.createConnection("joao", "passwd");
		this.c.start();
		this.s = this.c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		this.mp = this.s.createProducer(this.d);
	}

	private void send(String string) throws JMSException {
		TextMessage tm = this.s.createTextMessage(string);
		this.mp.send(tm);
	}

	private void close() throws JMSException {
		this.c.close();
	}

	/**
	 * @param args
	 * @throws JMSException
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException, JMSException{
		Sender s;
		try {
			s = new Sender();
			s.send("Ola ca estou eu!");
			s.close();
		}catch (CommunicationException a){
			System.out.println("JMS IS DOWN");
		}
	}
}