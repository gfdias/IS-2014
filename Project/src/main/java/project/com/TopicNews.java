package project.com;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopicNews {

	private String topicUrl;
	private String topicString;

	private ArrayList<String> newsUrls;


	public TopicNews(String url,String topicString) {
		this.topicUrl = url;
		this.topicString=topicString;
		this.newsUrls = new ArrayList<String>();
	}

	
	public void fetchLatestNews(){
		
		try {
			Document doc = Jsoup.connect(this.topicUrl).get();
			Elements newsUrls = doc.select("#cnn_mtt1rgtarea a");
			
			for (int i = 0; i < newsUrls.size(); i++) {
				Element article =  newsUrls.get(i);			
		        String info = article.attr(("href").toString());   	
		        this.newsUrls.add(info);
		        
			}
			
		} catch (Exception e) {
			System.out.println("CANNOT FETCH NEWS");
		}
	}	
}
