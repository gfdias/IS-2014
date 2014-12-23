package org.subscribe;

import javax.jws.WebService;

@WebService
public interface IUnsubscribe{
 
  String unsubscribeUser(String email);
  
}