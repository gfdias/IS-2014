package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Client;


@Stateful(mappedName = "ejb/userBean")
public class Login implements LoginRemote,Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "ISNEWS")
	EntityManager em;

	private String username;
	private boolean logged = false;

	public Login() {

	}

	public String getUsername() {

		return this.username;
	}

	public boolean getLogged() {
		
		System.out.println("USERNAME " +  this.username);
		Query q = em.createQuery("from Client e where username = :username");
		q.setParameter("username", this.username);
		Client c = (Client) q.getResultList().get(0);
		
		if(c.getLogged() == 0)
			this.logged = false;
		else{
			this.logged = true;
		}
		return this.logged;
	}
	
	public void teste(){
		System.out.println("TESTE");
	}

	public void login(String username, String password) {

		Query q = em
				.createQuery("UPDATE Client c SET c.logged=1 WHERE c.username = :username");
		q.setParameter("username", username);
		q.executeUpdate();
		this.username = username;
		this.logged = true;
	}
	
	@Remove
	public void logout(String username) {
		
		System.out.println("LOGOUT");
		Query q = em
				.createQuery("UPDATE Client c SET c.logged=0 WHERE c.username = :username");
		q.setParameter("username", username);
		q.executeUpdate();
		
		
	}

	public boolean verify(String username, String password) {

		boolean exists = false;
		Query q = em
				.createQuery("from Client u where u.username = :username and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);

		@SuppressWarnings("unchecked")
		List<Client> clients = q.getResultList();

		if (!clients.isEmpty()) {
			exists = true;
		}

		return exists;
	}

}
