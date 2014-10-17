package project.com;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

	
	public Topictype fetchLatestNews(){
				
		try {
			Document doc = Jsoup.connect(this.topicUrl).get();
			Elements latest = doc.select("#cnn_mtt1rgtarea a");

			for (int i = 0; i < latest.size(); i++) {
				Element article =  latest.get(i);			
				String info = article.attr(("href").toString());
				if(!info.substring(1, 6).equals("video")){
					System.out.println("NEWS! " + info);

					this.newsUrls.add(info);
				}
			}

			TopicNews newTopic = new TopicNews(this.topicString, this.newsUrls);
			System.out.println("topic created");
			
			return buildNews(newTopic);
			
		} catch (Exception e) {
			System.out.println("CANNOT FETCH NEWS");
		}
		return null;
	}

	
	public Topictype buildNews (TopicNews newTopic) throws ParseException{
		
		ObjectFactory topicObject = new ObjectFactory();

		Topictype topic = topicObject.createTopictype();

		topic.setTopicname(Topicnametype.fromValue(newTopic.topicString));
		
		
	
		Calendar rightNow = Calendar.getInstance();

		// offset to add since we're not UTC
		long offset = rightNow.get(Calendar.ZONE_OFFSET) +
		    rightNow.get(Calendar.DST_OFFSET);
		long sinceMidnight = (rightNow.getTimeInMillis() + offset) %
		    (24 * 60 * 60 * 1000);

		String topicId = String.valueOf(sinceMidnight);	
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
				
				
				Elements imageContainer = doc.select("img");
				List<String> images = new ArrayList<String>();
								
				for (int j = 0; j < imageContainer.size(); j++) {
					
					Element img = imageContainer.get(j);
					String imgUrl = img.absUrl("src");
					if(imgUrl.contains("http://i2.cdn.turner.com/cnn/dam/assets/")){
						images.add(imgUrl);
					}
				}


				for (int j = 0; j < highContainer.size(); j++) {
					Element highLight = highContainer.get(j);
					highLights.add(highLight.text());
				}
				
				Elements content = newsContainer.select("p");
				
				String text ="";
				
				for (int j = 0; j < content.size(); j++) {
					String p = content.get(j).text();
					
					text+=p;
				}
				
				Calendar rightNow2 = Calendar.getInstance();

				// offset to add since we're not UTC
				long offset2 = rightNow2.get(Calendar.ZONE_OFFSET) +
				    rightNow2.get(Calendar.DST_OFFSET);
				long sinceMidnight2 = (rightNow2.getTimeInMillis() + offset) %
				    (24 * 60 * 60 * 1000);
				
				if(!highLights.isEmpty()){
					Newstype news = topicObject.createNewstype();
					news.setNewsid(String.valueOf(sinceMidnight2));
					news.setAuthor(author);
					news.setContent(text);
					news.setHighLights(highLights);
					news.setDate(date);
					news.setTitle(title);
					news.setUrl(url);
					news.setImages(images);
					
					newsList.add(news);
				}

			} catch (IOException e) {
				System.out.println("Deu bode: "  + newTopic.newsUrls.get(i));
			}
		}

		topic.setNewsList(newsList);

		System.out.println("topic created->done");
		return  topic;

		
		
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
