package sender;

import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Receiver
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    String clientID;
    TopicSubscriber recv =null;
    
    public Receiver(String clientID) throws JMSException, NamingException{
		this.clientID=clientID;
		InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("jms/RemoteConnectionFactory");

        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("joao", "passwd");
        conn.setClientID(clientID);
        topic = (Topic) iniCtx.lookup("jms/topic/news");

        session = conn.createTopicSession(false,TopicSession.AUTO_ACKNOWLEDGE);
        
		recv = session.createDurableSubscriber(topic, clientID);
        conn.start();
	}
    
    public TopicSubscriber getRecv(){
    	return recv;
    }
    
    public void stop() 
        throws JMSException
    {
        conn.stop();
        session.close();
        conn.close();
    }
    
}