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
    
    
    public List<News> getRecentNews (){
    	System.out.println("GET RECENT NEWS");
		 Date start = new Date(System.currentTimeMillis() - (12 * 60 * 60 * 1000));
		 Date end =new Date();
		 System.out.println(start +"\n"+end);
		@SuppressWarnings("unchecked")
	    List<News> news = em.createQuery("from News e where e.date BETWEEN :start AND :end  ORDER BY e.date ASC" )
	    .setParameter("start", start, TemporalType.TIMESTAMP)
	    .setParameter("end", end, TemporalType.TIMESTAMP)
	    .getResultList();
      
		for (News news2 : news) {
			System.out.println(news2.getTitle());
		}
		return news;
    	
    }
    
    

}
