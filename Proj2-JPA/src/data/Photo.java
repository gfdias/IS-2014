package data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Photo
 *
 */

@Entity
public class Photo implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String url;
 
 @ManyToOne
 private News news;


 
 public Photo() {
  super();
 }

 public Photo(String url, News news) {
  super();
  this.url = url;
  this.news = news;
 }


 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
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
   
}
