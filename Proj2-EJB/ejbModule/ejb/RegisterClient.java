package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Client;

/**
 * Session Bean implementation class RegisterClient
 */

@Stateless
public class RegisterClient implements RegisterClientRemote {
	@PersistenceContext(name = "ISNEWS")
	EntityManager em;

	public RegisterClient() {

	}

	public void register(String username, String email, String password) {
		
		Client newClient = new Client(username, email, password, 0);
		
		em.persist(newClient);
		
		
	}

	public boolean verify(String username, String password) {
		
		boolean exists = false;
		Query q = em.createQuery("from Client u where u.username = :username and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		Client retrieved = (Client) q.getSingleResult();
		if(retrieved != null){
			exists = true;
		}
		
		return exists;
	}
}
