package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.Client;

@Remote
public interface AdminRemote {
	public List<Client> getUsers();
	public void setUser(String userID, String username, String email,String password);
	public void removeUser(String userID);

}
