package project.com;

public class Header {
	String url;
	String name;
	
	public Header(String url,String name){
		this.url=url;
		this.name=name;
	}
	
	public String getUrl(){
		return this.url;
	}
	public String getName(){
		return this.name;
	}
}
