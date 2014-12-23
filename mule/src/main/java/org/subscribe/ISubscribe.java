package org.subscribe;

import javax.jws.WebService;

@WebService
public interface ISubscribe{
 
  Info subscribeUser(String email, int type, String region);
  
}