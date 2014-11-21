package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Column( nullable = false)
	private String username;
	@NotNull
	@Column( nullable = false)
	private String email;
	@NotNull
	@Column( nullable = false)
	private String password;
	@NotNull
	@Column( nullable = false)
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
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
