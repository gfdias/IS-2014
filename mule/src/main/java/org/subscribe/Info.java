package org.subscribe;

public class Info {
	
	private String email;
	private int type;
	private String region;
	
	
public Info(){
		
		
	}
	
	public Info(String email, int type, String region){
		
		this.email = email;
		this.type = type;
		this.region = region;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	

}
