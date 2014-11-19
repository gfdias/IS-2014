package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Client;


@Stateful
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

		return this.logged;
	}

	public void login(String username, String password) {

		Query q = em
				.createQuery("UPDATE Client c SET c.logged=1 WHERE c.username = :username");
		q.setParameter("username", username);
		q.executeUpdate();
		this.username = username;
		this.logged = true;

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
