package ejb;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Client;

/**
 * Session Bean implementation class Login
 */
@Stateful
@Remote(LoginRemote.class)
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 0)
public class Login implements LoginRemote {

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
