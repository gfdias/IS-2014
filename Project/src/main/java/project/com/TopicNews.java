package project.com;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import project.com.schema.Newstype;
import project.com.schema.ObjectFactory;
import project.com.schema.Topicnametype;
import project.com.schema.Topictype;

public class TopicNews {

	private String topicUrl;
	private String topicString;

	private ArrayList<String> newsUrls;


	public TopicNews(String url,String topicString) {
		this.topicUrl = url;
		this.topicString=topicString;
		this.newsUrls = new ArrayList<String>();
	}
	
	public TopicNews(String type,ArrayList<String> newsList) {
		this.topicString=type;
		this.newsUrls = newsList;
	}

	
	public void fetchLatestNews(){
				
		try {
			Document doc = Jsoup.connect(this.topicUrl).get();
			Elements latest = doc.select("#cnn_mtt1rgtarea a");
			
			for (int i = 0; i < latest.size(); i++) {
				Element article =  latest.get(i);			
		        String info = article.attr(("href").toString());
		        System.out.println("NEWS! " + info);
		        this.newsUrls.add(info);
			}
			
						
			TopicNews newTopic = new TopicNews(this.topicString, this.newsUrls);
			
			buildNews(newTopic);
			
		} catch (Exception e) {
			System.out.println("CANNOT FETCH NEWS");
		}
	}
	
	public void buildNews (TopicNews newTopic) throws ParseException{
		
		ObjectFactory topicObject = new ObjectFactory();
		
		Topictype topic = topicObject.createTopictype();
		
		topic.setTopicname(Topicnametype.fromValue(newTopic.topicString));
		
		String topicId = new Timestamp(new java.util.Date().getTime()).toString();	
		topic.setTopicid(topicId);
		
		List<Newstype> newsList = new ArrayList<Newstype>();
			
		for (int i = 0; i < newTopic.newsUrls.size(); i++) {
			
			try {
				Document doc = Jsoup.connect("http://edition.cnn.com" + newTopic.newsUrls.get(i)).timeout(0).get();
				Elements newsContainer = doc.select("#cnnContentContainer");
				String title = newsContainer.select("h1").text();
				String author = newsContainer.select(".cnnByline").text();
				XMLGregorianCalendar date = parseDate(newsContainer.select(".cnn_strytmstmp").text());
				String url = newTopic.newsUrls.get(i);
				
				Elements highContainer = doc.select(".cnn_strylctcntr li");
				List<String> highLights = new ArrayList<String>();
				
				for (int j = 0; j < highContainer.size(); j++) {
					Element highLight = highContainer.get(j);
					highLights.add(highLight.text());
				}
				
				
				String content = newsContainer.select("p").text();
				
				Newstype news = topicObject.createNewstype();
				news.setNewsid(new Timestamp(new java.util.Date().getTime()).toString());
				news.setAuthor(author);
				news.setContent(content);
				news.setHighLights(highLights);
				news.setDate(date);
				news.setTitle(title);
				news.setUrl(url);
				
				newsList.add(news);
				
			} catch (IOException e) {
				System.out.println("Deu bode: "  + newTopic.newsUrls.get(i));
			}
		}
		
		topic.setNewsList(newsList);
		
		JAXBElement<Topictype> topicExport =  topicObject.createTopic(topic);
		
		ImportExportXml newExport = new ImportExportXml();
		newExport.exportReport(topicExport);
		
		
	}
	
	public XMLGregorianCalendar parseDate(String date) throws ParseException{
		

	    String re1="((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?))";	// Month 1
	    String re2=".*?";	
	    String re3="((?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";
	    String re4=".*?";	

	    String re5="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";
	    String re6=".*?";	

	    String re7="(\\d+)";

	    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(date);
	    if (m.find())
	    {
	    	
	        String month=m.group(1);
	        String day=m.group(2);
	        String year=m.group(3);
	        String time=m.group(4);
	        

	        String a=day+" "+month+ " "+year+" "+time;
	        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HHmm");
	        Date as=df.parse(a);
	          
	       
            XMLGregorianCalendar date2 = null;

            GregorianCalendar c = new GregorianCalendar();
            c.setTimeInMillis(as.getTime());
            try {
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
            System.out.println(date2);
            return date2;
	    
	    }	    
		return null;
	}
}
