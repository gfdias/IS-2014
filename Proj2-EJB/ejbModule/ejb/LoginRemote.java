package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.News;

@Remote
public interface LoginRemote {

	public String getUsername();
	public boolean getLogged();
	public void login(String username, String password);
	public boolean verify(String username, String password);
}
