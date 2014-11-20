package ejb;

import java.util.ArrayList;
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
import javax.persistence.TemporalType;
import javax.xml.datatype.XMLGregorianCalendar;

import aux.ImportExportXml;
import aux.Newstype;
import aux.Topictype;
import data.Author;
import data.Highlight;
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

	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public Topic publicFindOrCreateTopic(String name) {
		Query q = em.createQuery("from Topic u where u.name = :name");
		q.setParameter("name", name);

		@SuppressWarnings("unchecked")
		List<Topic> topics = q.getResultList();

		Topic topic;
		if (!topics.isEmpty()) {
			topic = topics.get(0);
		} else {
			topic = new Topic(name);
			em.persist(topic);
		}
		return topic;
	}

	public Author publicFindOrCreateAuthor(String name) {
		Query q = em.createQuery("from Author u where u.name = :name");
		q.setParameter("name", name);

		@SuppressWarnings("unchecked")
		List<Author> authors = q.getResultList();

		Author author;
		if (!authors.isEmpty()) {
			author = authors.get(0);
		} else {
			author = new Author(name);
			em.persist(author);
		}
		return author;
	}

	public Boolean newsExits(String url,int topicID) {
	   // List<News> news=em.createQuery("from News e " + "where topic_id= :topicID AND e.date between :start AND :end ORDER BY e.date ASC" ).setParameter("topicID", topicId).setParameter("start", start, TemporalType.TIMESTAMP).setParameter("end", end, TemporalType.TIMESTAMP).getResultList();
		@SuppressWarnings("unchecked")
	    List<News> news=em.createQuery("from News e " + "where topic_id= :topicID AND url=:url" ).setParameter("topicID", topicID).setParameter("url", url).getResultList();

		if (!news.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void onMessage(Message message) {
		System.out.println("\033[1;32m Message received");
        System.out.print("\033[0m");

		TextMessage tmsg = (TextMessage) message;
		try {
			ImportExportXml aux = new ImportExportXml();
			String msg1 = tmsg.getText();
			Topictype a = aux.stringToTopic(msg1);
			Topic topic = publicFindOrCreateTopic(a.getTopicname().value());
			System.out.print("\033[1;32m Adding topic"+ topic.getName()+"\033[0m");
			for (Newstype webNews : a.getNews()) {
				if (newsExits(webNews.getUrl(),topic.getID())) continue;
				System.out.print("\033[1;32m.\033[0m");

				Date date = toDate(webNews.getDate());
				Author author = publicFindOrCreateAuthor(webNews.getAuthor());

				News news = new News(webNews.getTitle(), webNews.getUrl(),
						date, webNews.getContent(), author, topic);
				
				ArrayList<Highlight> hs= new ArrayList<Highlight>();
				
				for (String eachHighlight:webNews.getHighlights()){
					Highlight hl= new Highlight(eachHighlight, news);
					em.persist(hl);
					hs.add(hl);
					
				}
				news.setHighlights(hs);
				em.persist(news);
			}
			System.out.println();
		    
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
