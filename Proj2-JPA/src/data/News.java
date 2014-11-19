package data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: News
 *
 */

@Entity
public class News implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String title;
 private String url;
 @Temporal(TemporalType.DATE)
 private Date date;

 @Column(columnDefinition = "TEXT")
 private String content;
 @ManyToOne
 private Author author;
 @ManyToOne
 private Topic topic;

 @OneToMany(mappedBy="news")
 private List<Highlight> highlights;
 @OneToMany(mappedBy="news")
 private List<Photo> photos;

 
 public News() {
  super();
 }

 public News(String title, String url, Date date, String content, Author author, Topic topic) {
  super();
  this.title = title;
  this.url = url;
  this.date = date;
  this.content = content;
  this.author = author;
  this.topic = topic;

 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public Date getDate() {
  return date;
 }

 public void setDate(Date date) {
  this.date = date;
 }

 public String getContent() {
  return content;
 }

 public void setContent(String content) {
  this.content = content;
 }

 public Author getAuthor() {
  return author;
 }

 public void setAuthor(Author author) {
  this.author = author;
 }

 public Topic getTopic() {
  return topic;
 }

 public void setTopic(Topic topic) {
  this.topic = topic;
 }

 public static long getSerialversionuid() {
  return serialVersionUID;
 }

 public List<Highlight> getHighlights() {
  return highlights;
 }

 public void setHighlights(List<Highlight> highlights) {
  this.highlights = highlights;
 }

 public List<Photo> getPhotos() {
  return photos;
 }

 public void setPhotos(List<Photo> photos) {
  this.photos = photos;
 }
   
}
