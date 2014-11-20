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
    	
    	
    	Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY,0);
		 cal.set(Calendar.MINUTE,0);
		 cal.set(Calendar.SECOND,0);
		 cal.set(Calendar.MILLISECOND,0);
		 cal.set(Calendar.DATE,cal.get(Calendar.DATE)-1);
		 
		 Date start = cal.getTime();
		 cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY-12);
		 Date end = cal.getTime();
		 
		@SuppressWarnings("unchecked")
	    List<News> news = em.createQuery("from News e where e.date BETWEEN :start AND :end  ORDER BY e.date ASC" )
	    .setParameter("start", start, TemporalType.TIMESTAMP)
	    .setParameter("end", end, TemporalType.TIMESTAMP)
	    .getResultList();
      
		return news;
    	
    }
    
    

}
