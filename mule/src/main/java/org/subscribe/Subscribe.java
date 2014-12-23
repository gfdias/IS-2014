package org.subscribe;

public class Subscribe implements ISubscribe {

	public Info subscribeUser(String email, int type, String region) {
		
		Info info = new Info(email, type, region);
		return info;
	}
}
