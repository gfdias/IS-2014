package ejb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.datatype.XMLGregorianCalendar;

import aux.ImportExportXml;
import aux.Newstype;
import aux.Topictype;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import data.Author;
import data.Client;
import data.News;
import data.Topic;



@MessageDriven(name = "news", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/topic/news"),

})
public class ReceiveNews implements MessageListener {
	@PersistenceContext(name = "ISNEWS")
	EntityManager em;

	public ReceiveNews() {
	}
    public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    
    
    public Topic publicFindOrCreateTopic(String name){
		Query q = em.createQuery("from Topic u where u.name = :name");
		q.setParameter("name", name);
		
		
		@SuppressWarnings("unchecked")
		List <Topic> topics = q.getResultList();
		
		Topic topic;
		if(!topics.isEmpty()){
			topic=topics.get(0);
		}else{
			topic=new Topic(name);
			em.persist(topic);
		}
		return topic;
    }

    
    public Author publicFindOrCreateAuthor(String name){
		Query q = em.createQuery("from Author u where u.name = :name");
		q.setParameter("name", name);
		
		
		@SuppressWarnings("unchecked")
		List <Author> authors = q.getResultList();
		
		Author author;
		if(!authors.isEmpty()){
			author=authors.get(0);
		}else{
			author=new Author(name);
			em.persist(author);
		}
		return author;
    }
    
    

	public void onMessage(Message message) {
		System.out.println("Message recived");
		TextMessage tmsg = (TextMessage) message;
		try {
			ImportExportXml aux = new ImportExportXml();

			String msg1 = tmsg.getText();

			//if (StatsProducer.validateAgainstXSD(msg1, "newscontent.xsd") == true) {
				Topictype a = aux.stringToTopic(msg1);
								
				Topic topic=publicFindOrCreateTopic(a.getTopicname().value());

				for (Newstype webNews : a.getNews()) {
				       Date date = toDate(webNews.getDate());
				       Author author=publicFindOrCreateAuthor(webNews.getAuthor());

				       	News news=new News(webNews.getTitle(), webNews.getUrl(), date, webNews.getContent(), author, topic);
					//	News news=new News(webNews.getTitle(), webNews.getUrl(), date, "asdsad", author, topic);
						em.persist(news);

				}
				//News news=new News(a.getTitle(), a.getUrl(), a., a.getContent(), a.getAuthor(), a.getTopic());
				//News(String title, String url, Date date, String content, Author author, Topic topic)

			//}

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
