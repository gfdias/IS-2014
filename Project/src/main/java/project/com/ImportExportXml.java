package project.com;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import project.com.schema.Topictype;

public class ImportExportXml {
	
	public Topictype importTopic (String xmlName){
		Topictype topic=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Topictype.class);
			Unmarshaller u = jc.createUnmarshaller();

			File f = new File(xmlName);
			topic = (Topictype) u.unmarshal(f);

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return topic;
	}
	
	public Topictype stringToTopic (String xml){
		Topictype topic=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Topictype.class);
			Unmarshaller u = jc.createUnmarshaller();
			StringReader reader = new StringReader(xml);

			topic = (Topictype) u.unmarshal(reader);

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return topic;
	}

	public String getXMLString(Topictype report){
		JAXBContext context;
		StringWriter sw=new StringWriter();
		
		try {
			context = JAXBContext.newInstance(Topictype.class);
			 Marshaller m = context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			 m.marshal(report, sw);
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}

	
	
	public boolean exportReport(Topictype report,String name){
		boolean exported=false;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Topictype.class);
			 Marshaller m = context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 File f = new File(name+".xml");
			 if(report==null)  return false;
			 m.marshal(report, f);
			 exported=true;
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		return exported;
	}
}
