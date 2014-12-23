package twitter;

import java.util.ArrayList;

public class News {
	public String content;
	public String title;
	public String photos;
	public ArrayList<String> highlights;
	
	public News(String content, String title, String photos,
			ArrayList<String> highlights) {
		super();
		this.content = content;
		this.title = title;
		this.photos = photos;
		this.highlights = highlights;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public ArrayList<String> getHighlights() {
		return highlights;
	}
	public void setHighlights(ArrayList<String> highlights) {
		this.highlights = highlights;
	}

	
	


}
