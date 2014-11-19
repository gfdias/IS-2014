package ejb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import data.News;
import data.Topic;

@Stateless
@LocalBean
public class SendEmail {
	@Resource(mappedName = "java:jboss/mail/Student")
	Session SendEmail;
	@PersistenceContext(name = "ISNEWS")
	EntityManager em;

	public SendEmail() {
	}

	@Schedule(hour="1",minute="0",second="0")
	public void automaticTimer() {
		
		System.out.println("\033[1;32m Sending emails");
        System.out.print("\033[0m");
        String content=getContent();
        
        Query q = em.createQuery("Select email from Client");

		@SuppressWarnings("unchecked")
		List<String> emails = q.getResultList();
		       
		
		for (String email : emails) {
			System.out.println("\033[1;32m Sending to"+email);
	        System.out.print("\033[0m");
	        sendEmail (email,"no_reply@isnews.com", "Daily Digest", content);

		}
		
		//sendEmail ("goncalodiasgm@gmail.com","do_not_reply@apple.com", "Banned Account", "APRENDE A PROGRAMAR MAS É");

    }
	
	private List<News>  getNewsForTopic(int topicId){	 
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY,0);
		 cal.set(Calendar.MINUTE,0);
		 cal.set(Calendar.SECOND,0);
		 cal.set(Calendar.MILLISECOND,0);
		 cal.set(Calendar.DATE,cal.get(Calendar.DATE)-1);
		 
		 Date start = cal.getTime();
		 cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY,23);
		 cal.set(Calendar.MINUTE,59);
		 cal.set(Calendar.SECOND,59);
		 cal.set(Calendar.DATE,cal.get(Calendar.DATE)-1);
		 Date end = cal.getTime();
		 
		@SuppressWarnings("unchecked")
	    List<News> news=em.createQuery("from News e " + "where topic_id= :topicID AND e.date between :start AND :end ORDER BY e.date ASC" ).setParameter("topicID", topicId).setParameter("start", start, TemporalType.TIMESTAMP).setParameter("end", end, TemporalType.TIMESTAMP).getResultList();
       
		return news;
	}
	
	private String getContent(){
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
	    
	    
	    Template t = ve.getTemplate( "templates/helloworld.vm" );
	    VelocityContext context = new VelocityContext();
	    
	
		@SuppressWarnings("unchecked")
	    List<Topic> topics=em.createQuery("from Topic").getResultList();

        ArrayList <HashMap<String, Object>> list = new ArrayList <HashMap<String, Object>>();
		HashMap<String, Object> map;

	    for (Topic topic : topics) {
	    	map = new HashMap <String, Object>();
	    	map.put("topicName", topic.getName());
	    	map.put("news",getNewsForTopic(topic.getID()));
	    	list.add(map);
	    }
	    
        context.put("newsWithTopic", list);
        
        StringWriter writer = new StringWriter();
	    t.merge( context, writer );	   
	    return writer.toString(); 
	}
	@Asynchronous
	public void sendEmail(String to, String from, String subject, String content) {

		System.out.println("\033[1;32m  Sending Email from " + from + " to " + to + " : "
				+ subject+"\033[0m");
		try {
			Message message = new MimeMessage(SendEmail);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			//message.setText(content);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);
		    System.out.println("\033[1;32m start date mail was sent \033[0m");


		} catch (MessagingException e) {
			System.out.println("Error while sending email : " + e.getMessage());
		}
	}

}
