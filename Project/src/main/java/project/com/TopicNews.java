package project.com;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopicNews {

	private String topicUrl;
	private ArrayList<String> newsUrls;


	public TopicNews(String url) {
		this.topicUrl = url;
		this.newsUrls = new ArrayList<String>();
	}

	public static void main(String args[]){
		
		TopicNews topic = new TopicNews("http://edition.cnn.com/US/");
		
		topic.fetchLatestNews(topic.topicUrl);

	}
	public void fetchLatestNews(String url){

		try {
			Document doc = Jsoup.connect(url).get();
			Elements newsHeadlines = doc.select("#cnn_mtt1rgtarea");
			Document parsed = Jsoup.parse(newsHeadlines.toString());
			Element link = parsed.select("a").first();
			String linkText = link.attr("href");

		} catch (Exception e) {
			System.out.println("CANNOT GET HEADERS");
		}

	}

}
