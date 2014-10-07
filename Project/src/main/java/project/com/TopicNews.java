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
			Elements newsHeadlines = doc.select("#cnn_mtt1rgtarea");
			Document parsed = Jsoup.parse(newsHeadlines.toString());
			Element link = parsed.select("a").first();
			String linkText = link.attr("href");

		} catch (Exception e) {
			System.out.println("CANNOT FETCH NEWS");
		}

	}
	
	public static void main(String args[]){
		
		TopicNews topic = new TopicNews("http://edition.cnn.com/US/","us");
		
		topic.fetchLatestNews();

	}

}
