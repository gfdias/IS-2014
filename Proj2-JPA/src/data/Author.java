package data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Author
 *
 */

@Entity
public class Author implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String name;
 

 @OneToMany(mappedBy="author")
 private List<News> news;

 
 public Author() {
  super();
 }

 public Author(String name) {
  super();
  this.name = name;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

public List<News> getNews() {
  return news;
 }

 public void setNews(List<News> news) {
  this.news = news;
 }


 public static long getSerialversionuid() {
  return serialVersionUID;
 }
   
}
