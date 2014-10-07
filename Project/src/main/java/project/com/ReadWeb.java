package project.com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ReadWeb {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start Read Web");
		ReadWeb a=new ReadWeb();
		
		a.getFromWeb("http://cnn.com/");
		System.out.println("End Read Web");
	}
	
	public void getFromWeb(String webSite) {
		try {
			Document doc = Jsoup.connect(webSite).get();
			Elements newsHeadlines = doc.select("#intl-menu a");

			for (int i = 0; i < newsHeadlines.size(); i++) {
				System.out.println("/t header"+newsHeadlines.toString());
				Element header=  newsHeadlines.get(i);
		        System.out.println(header.tagName("href"));        
			}
	       
		} catch (Exception e) {
			System.out.println("CANNOT GET HEADERS");
		}
	}
	
}
