package twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.mule.api.MuleMessage;

public class dateFilter implements org.mule.api.routing.filter.Filter{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean accept(MuleMessage message){
		System.out.println("Compare");
		try {
			String dateStr=message.getOutboundProperty("date").toString();
			System.out.println(dateStr);
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateStr);
			System.out.println("parse done");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			System.out.println("compre result " +date.before(yesterday));
			return date.before(yesterday);
		} catch (ParseException e) {
			return false;
		}		
	}
}
