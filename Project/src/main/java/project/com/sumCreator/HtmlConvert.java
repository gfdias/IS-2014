package project.com.sumCreator;

import javax.xml.transform.*;
import java.net.*;
import java.io.*;

public class HtmlConvert {
public static void main(String[] args) {
	
	
  try {

    TransformerFactory tFactory = TransformerFactory.newInstance();

    Transformer transformer =
      tFactory.newTransformer
         (new javax.xml.transform.stream.StreamSource
            ("template.xsl"));

    transformer.transform
      (new javax.xml.transform.stream.StreamSource
            ("U.S..xml"),
       new javax.xml.transform.stream.StreamResult
            ( new FileOutputStream("us.html")));
    }
  catch (Exception e) {
    e.printStackTrace( );
    }
  }
}