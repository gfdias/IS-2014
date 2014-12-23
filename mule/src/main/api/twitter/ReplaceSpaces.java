package twitter;

public class ReplaceSpaces {
    
	public static String ReplaceSpacesFromPayload(String text)
    {
		text=text.replace("%20", " ");
		if(text.length()>=140){
			text= text.substring(0, 140);	
		}
        return text;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
