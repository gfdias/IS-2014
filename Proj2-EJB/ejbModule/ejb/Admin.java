package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Client;

@Stateless
public class Admin implements AdminRemote {
	@PersistenceContext(name = "ISNEWS")
	EntityManager em;
	
    public Admin() {
    }
   
	public List<Client> getUsers() {
        Query q = em.createQuery("from Client");

		@SuppressWarnings("unchecked")
		List<Client> clients = q.getResultList();
		return clients;
	}
	public void setUser(String userID, String username, String email,String password){
		int userInt=Integer.valueOf(userID);

		@SuppressWarnings("unchecked")
		List<Client> clients=em.createQuery("from Client e where id= :userid" ).setParameter("userid", userInt).setMaxResults(1).getResultList();
		if (clients.size()>0){
			Client aux=clients.get(0);
			aux.setEmail(email);
			aux.setPassword(password);
			aux.setUsername(username);
		}
	}
	public void removeUser(String userID){
		System.out.println("deleting user "+userID);
		int userInt=Integer.valueOf(userID);

		@SuppressWarnings("unchecked")
		List<Client> clients=em.createQuery("from Client e where e.id= :userid" ).setParameter("userid", userInt).getResultList();
		if (clients.size()>0){
			Client aux=clients.get(0);
			em.remove(aux);
			System.out.println("User delete");
		}else{
			System.out.println("User not found");
		}
	}

}
