package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Client
 *
 */

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private int logged;

	public Client() {
		super();
	}

	public Client(String username, String email, String password, int logged) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.logged = logged;
	}

	public String getClientname() {
		return username;
	}

	public void setClientname(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLogged() {
		return logged;
	}

	public void setlogged(int logged) {
		this.logged = logged;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
