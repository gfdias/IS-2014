package project.com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import project.com.schema.Topictype;

public class Header implements Serializable {
	String url;
	String name;
	Topictype news;
	ArrayList<NewsCon> newsArray;

	public Header(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public Header(String name) {
		this.name = name;
		this.newsArray = new ArrayList<NewsCon>();
	}

	public ArrayList<NewsCon> getNewsArray() {
		return newsArray;
	}

	public void setNewsArray(ArrayList<NewsCon> newsArray) {
		this.newsArray = newsArray;
	}

	public String getUrl() {
		return this.url;
	}

	public String getName() {
		return this.name;
	}

	public void setNews(Topictype topic) {
		this.news = topic;
	}

	public Topictype getNews() {
		return this.news;
	}

	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException { 
		aInputStream.defaultReadObject();
	
	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		// perform the default serialization for all non-transient, non-static
		// fields
		aOutputStream.defaultWriteObject();
	}

}
