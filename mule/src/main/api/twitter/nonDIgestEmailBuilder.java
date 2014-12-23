package twitter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.component.*;
import org.mule.util.CaseInsensitiveHashMap;

public class nonDIgestEmailBuilder implements Callable{


	public static void main(String[] args) {
	}

	public Object onCall(MuleEventContext eventContext) throws Exception {
		String body="";
		String to="";
		body+=eventContext.getMessage().getOutboundProperty("content");
		LinkedList<CaseInsensitiveHashMap>  a= eventContext.getMessage().getOutboundProperty("toAddresses"); 
		
		//TO......
		for (int i = 0; i < a.size(); i++) {
			CaseInsensitiveHashMap hash=a.get(i);
			if(i==0){
				to= hash.get("email").toString();
			}else{
				to += ", " + hash.get("email").toString();
			}
		}
		eventContext.getMessage().setOutboundProperty("toAddresses", to);

		//content set layout
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
	    
	    Template t = ve.getTemplate( "twitter/email.vm" );
	    VelocityContext context = new VelocityContext();
        context.put("title",eventContext.getMessage().getOutboundProperty("title"));
        context.put("highlights",eventContext.getMessage().getOutboundProperty("highlights"));
        context.put("content",eventContext.getMessage().getOutboundProperty("content"));
        context.put("images",eventContext.getMessage().getOutboundProperty("images"));

        StringWriter writer = new StringWriter();
	    t.merge( context, writer );	   
		eventContext.getMessage().setPayload(writer.toString());
		return eventContext.getMessage();
	}

}
