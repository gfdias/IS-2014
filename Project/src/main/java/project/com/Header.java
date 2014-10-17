package project.com;

import javax.jms.Topic;

import project.com.schema.Topictype;

public class Header {
	String url;
	String name;
	Topictype news;
	
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
	public void setNews(Topictype topic){
		this.news=topic;
	}
	
	public Topictype getNews(){
		return this.news;
	}
}
