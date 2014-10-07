package project.com;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class ReadWeb {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadWeb a=new ReadWeb();
		a.getFromWeb("");
	}
	
	public void getFromWeb(String web){
		Document doc = Jsoup.connect("http://cnn.com/").get();
		Elements newsHeadlines = doc.select("#mp-itn b a");
		System.out.println(newsHeadlines.toString());

		
		
	}
	
}
