package ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import data.Author;
import data.News;
import data.Photo;

/**
 * Session Bean implementation class newsHandler
 */
@Stateless
public class NewsHandler implements NewsHandlerRemote {

	@PersistenceContext(name = "ISNEWS")
	EntityManager em;
	private final String NO_IMAGE_URL="http://www.userlogos.org/files/logos/articed/CNN.jpg";
	public NewsHandler() {

	}

	public List<News> getRecentNews() {
		System.out.println("GET RECENT NEWS");
		Date start = new Date(System.currentTimeMillis()
				- (12 * 60 * 60 * 1000));
		Date end = new Date();
		@SuppressWarnings("unchecked")
		List<News> results = em
		//ORDER BY e.date DESC
				.createQuery("SELECT e from News e where e.date BETWEEN :start AND :end ORDER BY e.date DESC")
				.setParameter("start", start, TemporalType.TIMESTAMP)
				.setParameter("end", end, TemporalType.TIMESTAMP)
				.getResultList();

		System.out.println("news-> "+results.size());
		//ArrayList<News> news=new ArrayList<News> ();
		return results;
	}
	
	public List<News> getNewsById(String id){
		
		@SuppressWarnings("unchecked")
		List<News> news = em
				.createQuery(
						"from News e where e.id =:id ORDER BY e.date DESC")
				.setParameter("id", Integer.parseInt(id))
				.getResultList();

		return news;
	}

	public List<News> getNewsByTopic(String topicName) {
		
		System.out.println("TOPIC " + topicName);
		
		if(topicName.equals("MiddleEast")){
			topicName = "Middle East";
		}
		
		else if(topicName.equals("LatinAmerica")){
			topicName = "Latin America";
		}
		
		else if(topicName.equals("WorldSport")){
			topicName = "World Sport";
		}

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
	
    public List<String> getOnePhotoPerNews( List<News> newsList){
    	ArrayList<String> photosList=new ArrayList<String>();
    	for (News news : newsList) {
    		@SuppressWarnings("unchecked")
    	    List<Photo> photosForNews=em.createQuery("from Photo e where news_id = :newsID" ).setParameter("newsID", news.getId()).getResultList();		
    		if (photosForNews!=null && photosForNews.size()>0){
    			photosList.add(photosForNews.get(0).getUrl());
    		}else{
    			photosList.add(NO_IMAGE_URL);
    		}
    		
		}
    	return photosList;
    }
    
    public List<String> getTenPhotoPerNews( List<News> newsList){
    	ArrayList<String> photosList=new ArrayList<String>();
    	int i = 0;
    	for (News news : newsList) {
    		@SuppressWarnings("unchecked")
    	    List<Photo> photosForNews=em.createQuery("from Photo e where news_id = :newsID" ).setParameter("newsID", news.getId()).getResultList();		
    		if (photosForNews!=null && photosForNews.size()>0){
    			while(i < 10 && i < photosForNews.size()){
        			photosList.add(photosForNews.get(i).getUrl());
        			i++;
    			}
    		}else{
    			photosList.add(NO_IMAGE_URL);
    		}
    		
		}
    	return photosList;
    }

    
    public List<Author> getAllAuthors() {
	
		@SuppressWarnings("unchecked")
		List<Author> authors = em.createQuery("from Author e ORDER BY e.name ASC").getResultList();

		return authors;

	}
    public List<News> newsWithAuthor(String authorID){
    	int author=Integer.parseInt(authorID);
		@SuppressWarnings("unchecked")
    	 List<News> news=em.createQuery("from News e where author_id = :authorId" ).setParameter("authorId",author).getResultList();		
    	return news;
    }

    public List <News> newsWihtHighLight(String search){
    		@SuppressWarnings("unchecked")
    	    List<News> news=em.createQuery("select n from News n, Highlight h where h.news=n.id AND h.content LIKE '%"+search+"%' GROUP BY n.id ORDER BY n.id" ).getResultList();		
    		return news;
    }
    

}
