package org.subscribeClient;
import org.subscribe.*;

public class SubscribeClient {

	public static void main(String[] args){
		
		ISubscribeService service = new ISubscribeService();
		ISubscribe subscribe =  service.getISubscribePort();
		
		System.out.println(subscribe.sayHello("Dias"));
		
	}
	
}
