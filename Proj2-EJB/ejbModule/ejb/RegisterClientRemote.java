package ejb;

import javax.ejb.Remote;

@Remote
public interface RegisterClientRemote {
	
	public void register(String username, String email, String password);
	public boolean verify(String username, String password);

}
