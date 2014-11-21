package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.News;
import data.Photo;

@Remote
public interface NewsHandlerRemote {
	public List<News> getRecentNews ();
	public List<News> getNewsByTopic(String topic);
    public List<News> getRecentNews (String selectedDate);
    public List<String> getOnePhotoPerNews(List<News> newsList);
    public List<String> getTenPhotoPerNews(List<News> newsList);
    public List<News> getNewsById(String id);
}
