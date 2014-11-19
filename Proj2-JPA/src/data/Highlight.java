package data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Highlight
 *
 */

@Entity
public class Highlight implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String content;
 
 @ManyToOne
 private News news;


 
 public Highlight() {
  super();
 }

 public Highlight(String content, News news) {
  super();
  this.content = content;
  this.news = news;
 }

 public String getContent() {
  return content;
 }

 public void setContent(String url) {
  this.content = url;
 }

 public News getNews() {
  return news;
 }

 public void setNews(News news) {
  this.news = news;
 }


 public static long getSerialversionuid() {
  return serialVersionUID;
 }
 public String toString() {
	 return content;
 }
}
