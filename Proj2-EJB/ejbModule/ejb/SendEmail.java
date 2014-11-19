package ejb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Schedule(hour="1",minute="*",second="*")
	public void automaticTimer() {
		
	/*	System.out.println("\033[1;32m Sending emails");
        System.out.print("\033[0m");
        String content=getContent();
        
        Query q = em.createQuery("Select email from Client");

		@SuppressWarnings("unchecked")
		List<String> emails = q.getResultList();
		       
		for (String email : emails) {*/
			//System.out.println("\033[1;32m Sending to"+email);
	       // System.out.print("\033[0m");
	        
		//}
		
		//sendEmail ("goncalodiasgm@gmail.com","do_not_reply@apple.com", "Banned Account", "APRENDE A PROGRAMAR MAS É");
     //  sendEmail ("jsaguiar@me.com","gay@apple.com", "Daily Digest", content);

    }
	
	private ArrayList<Map<String, String>>  getNewsForTopic(int topicId){	 
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
		 
		System.out.println("\033[1;32m start date "+start.toString());
	    System.out.print("\033[0m");
	    System.out.println("\033[1;32m start date "+end.toString()+"\033[0m");
		@SuppressWarnings("unchecked")
	    List<News> news=em.createQuery("from News e " + "where e.date between :start AND :end").setParameter("start", start, TemporalType.DATE).setParameter("end", end, TemporalType.DATE).getResultList();
       
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
		for (News one : news) {
	       map = new HashMap<String, String>();
	       map.put("title", one.getTitle());
	       map.put("content", one.getContent());
	       list.add( map );
		}
	    
		return list;
	}
	
	private String getContent(){
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
	    
	    
	    Template t = ve.getTemplate( "templates/helloworld.vm" );
	    VelocityContext context = new VelocityContext();
	    
	    
        
        context.put("newsForTopic", getNewsForTopic(1));
        
        StringWriter writer = new StringWriter();
	    t.merge( context, writer );
	    System.out.println( writer.toString() );  
	   
	    return writer.toString(); 
	}
	@Asynchronous
	public void sendEmail(String to, String from, String subject, String content) {

		System.out.println("Sending Email from " + from + " to " + to + " : "
				+ subject);
		try {
			Message message = new MimeMessage(SendEmail);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			//message.setText(content);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);

			System.out.println("Email was sent");

		} catch (MessagingException e) {
			System.out.println("Error while sending email : " + e.getMessage());
		}
	}

}
