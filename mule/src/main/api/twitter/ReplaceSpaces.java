package twitter;

public class ReplaceSpaces {
    
	public static String ReplaceSpacesFromPayload(String text)
    {
		
		if(text.length()>=140){
			text= text.substring(0, 140);	
		}
		System.out.println("twitter post->"+text);
		text="wtf"+ text.substring(0, 20);
        return text.replace("%20", " ");
        
    }


}
