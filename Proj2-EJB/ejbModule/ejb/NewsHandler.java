package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import data.News;

/**
 * Session Bean implementation class newsHandler
 */
@Stateless
public class NewsHandler implements NewsHandlerRemote {

	@PersistenceContext(name = "ISNEWS")
	EntityManager em;

	public NewsHandler() {

	}

	public List<News> getRecentNews() {
		System.out.println("GET RECENT NEWS");
		Date start = new Date(System.currentTimeMillis()
				- (12 * 60 * 60 * 1000));
		Date end = new Date();
		@SuppressWarnings("unchecked")
		List<News> news = em
				.createQuery(
						"from News e where e.date BETWEEN :start AND :end  ORDER BY e.date DESC")
				.setParameter("start", start, TemporalType.TIMESTAMP)
				.setParameter("end", end, TemporalType.TIMESTAMP)
				.getResultList();

		return news;
	}

	public List<News> getNewsByTopic(String topicName) {

		@SuppressWarnings("unchecked")
	    List<News> news=em.createQuery("Select e from News e, Topic t where e.topic= t.id AND t.name =:topicName ORDER BY e.date DESC" ).setParameter("topicName", topicName).getResultList();		
		System.out.println("show news->"+news.size());
		return news;

	}

	// [12h 24h 48h all]
	public List<News> getRecentNews(String selectedDate) {
		if (selectedDate.equals("All")) {
			return getAllNews();
		}

		int select = Integer.valueOf(selectedDate);
		Date start = new Date(System.currentTimeMillis()
				- (select * 60 * 60 * 1000));
		Date end = new Date();
		@SuppressWarnings("unchecked")
		List<News> news = em
				.createQuery(
						"from News e where e.date BETWEEN :start AND :end  ORDER BY e.date DESC")
				.setParameter("start", start, TemporalType.TIMESTAMP)
				.setParameter("end", end, TemporalType.TIMESTAMP)
				.getResultList();

		return news;

	}

	public List<News> getAllNews() {
		@SuppressWarnings("unchecked")
		List<News> news = em.createQuery("from News e ORDER BY e.date DESC").getResultList();
		return news;
	}

}
