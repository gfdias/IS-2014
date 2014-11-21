package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.News;

@Remote
public interface NewsHandlerRemote {
	public List<News> getRecentNews ();
	public List<News> getNewsByTopic(String topic);
}
