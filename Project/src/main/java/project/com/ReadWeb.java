package project.com;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ReadWeb {
	ArrayList<String> ignore = new ArrayList<String>() {{
	    add("video");
	    add("tech");
	    add("ireport");
	    add("travel");
	}};	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start Read Web");
		ReadWeb a=new ReadWeb();
		
		a.getFromWeb("http://edition.cnn.com");
		System.out.println("End Read Web");
	}
	
	public void getFromWeb(String webSite) {
		try {
			Document doc = Jsoup.connect(webSite).get();
			Elements newsHeadlines = doc.select("#intl-menu a");

			
			for (int i = 1; i < newsHeadlines.size(); i++) {
				Element header=  newsHeadlines.get(i);
				
		        String getInfo=header.attr(("href").toString());   
		        if	(getInfo.equals("/video/") || getInfo.equals("/SHOWBIZ/")) continue;
		        String headerName=header.text();
		        if (ignore.contains(headerName.toLowerCase())) continue;
		        System.out.println("Getting news of "+headerName);
		        String url=webSite+getInfo;
		        System.out.println("url "+url);
		        TopicNews aux=new  TopicNews(url,headerName);
		        System.out.println("Done....................................................");

		        aux.fetchLatestNews();
		        
			}
	       
		} catch (Exception e) {
			System.out.println("CANNOT GET HEADERS");
		}
	}
}
