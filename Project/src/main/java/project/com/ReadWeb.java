package project.com;

import java.util.ArrayList;

import javax.xml.bind.JAXBElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import project.com.schema.Topictype;


public class ReadWeb {
	
	@SuppressWarnings("serial")
	ArrayList<String> ignore = new ArrayList<String>() {{
	    add("video");
	    add("tech");
	    add("ireport");
	    add("travel");
	}};	
	

	public ArrayList<Header> getFromWeb(String webSite) {
		System.out.println("Start Read Web");

		ArrayList<Header> headers=new ArrayList<Header>();
		try {
			
			Document doc = Jsoup.connect(webSite).get();
			Elements newsHeadlines = doc.select("#intl-menu a");

			for (int i = 1; i < newsHeadlines.size(); i++) {
				Element header = newsHeadlines.get(i);
				
		        String getInfo=header.attr(("href").toString());   
		        if	(getInfo.equals("/video/") || getInfo.equals("/SHOWBIZ/")) continue;
		        String headerName = header.text();
		        if (ignore.contains(headerName.toLowerCase())) continue;
		        System.out.println("Getting news of " + headerName);
		        String url = webSite + getInfo;
		        System.out.println("url " + url);
		        
		        Header aux = new Header(url, headerName);
		        headers.add(aux);
		        
		   

		        System.out.println("Done....................................................");
			}
	       
		} catch (Exception e) {
			System.out.println("CANNOT GET HEADERS");
		}
		return headers;
	}
}
