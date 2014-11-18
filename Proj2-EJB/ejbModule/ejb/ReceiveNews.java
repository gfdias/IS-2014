package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: ReceiveNews
 */
@MessageDriven(
		   name = "news",
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType",
						propertyValue = "javax.jms.Topic"),
				@ActivationConfigProperty( propertyName = "destination", 
                         propertyValue ="/topic/news"),
                @ActivationConfigProperty(propertyName = "bean",
                         propertyValue = "joao"),
                @ActivationConfigProperty(propertyName = "subscriptionName",
                         propertyValue = "passwd")
		})
public class ReceiveNews implements MessageListener {

    /**
     * Default constructor. 
     */
    public ReceiveNews() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	System.out.println("\n\n\n\n\nRECEBIIII");
    	System.out.println(message);

    }

}
