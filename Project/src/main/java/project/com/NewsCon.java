package project.com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class NewsCon implements Serializable {
	public Date date=null;
	public String url=null;
	
	public NewsCon(Date date, String url) {
		this.date = date;
		this.url = url;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException { 
		aInputStream.defaultReadObject();
	
	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		// perform the default serialization for all non-transient, non-static
		// fields
		aOutputStream.defaultWriteObject();
	}
	
}
