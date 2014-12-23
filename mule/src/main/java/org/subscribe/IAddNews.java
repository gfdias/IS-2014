package org.subscribe;

import javax.jws.WebService;

@WebService
public interface IAddNews{
 
  String addNews(String news);
  
}