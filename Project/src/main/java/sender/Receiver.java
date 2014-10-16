package sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Receiver implements MessageListener
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    String clientID;

    public Receiver(String clientID) {
		this.clientID=clientID;
	}
    
    public void setupPubSub()
        throws JMSException, NamingException
    {
        InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("jms/RemoteConnectionFactory");

        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("joao", "passwd");
        conn.setClientID(clientID);
        topic = (Topic) iniCtx.lookup("jms/topic/news");

        session = conn.createTopicSession(false,TopicSession.AUTO_ACKNOWLEDGE);
        
		TopicSubscriber recv = session.createDurableSubscriber(topic, "joao");
		recv.setMessageListener(this);
        conn.start();
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

    
    
    public void stop() 
        throws JMSException
    {
        conn.stop();
        session.close();
        conn.close();
    }
    
    public static void main(String args[]) 
        throws Exception
    {
    	
        System.out.println("Begin DurableTopicRecvClient, now=" + 
                           System.currentTimeMillis());
        Receiver client = new Receiver("joao_1");
        client.setupPubSub();
    	System.in.read();		
        client.stop();
        System.out.println("End DurableTopicRecvClient");
        System.exit(0);
    }
    
}