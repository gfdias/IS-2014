package sender;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender {
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    
    public void setupPubSub()
        throws JMSException, NamingException
    {
        InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("jms/RemoteConnectionFactory");
        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("joao","passwd");
        
        topic = (Topic) iniCtx.lookup("jms/topic/news");
        session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        conn.start();
    }
    
    public void sendAsync(String text) throws JMSException, NamingException
    {
        System.out.println("Begin Send");
        // Setup the pub/sub connection, session
        setupPubSub();
        // Send a text msg
        TopicPublisher send = session.createPublisher(topic);
        TextMessage tm = session.createTextMessage(text);
        send.publish(tm);
        send.close();
        System.out.println("send DONE");

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
        System.out.println("Begin TopicSendClient, now=" + 
		                   System.currentTimeMillis());
        Sender client = new Sender();
	    client.sendAsync("A text msg, now=i thing is working");
        client.stop();
        System.out.println("End TopicSendClient");
        System.exit(0);
    }
    
}