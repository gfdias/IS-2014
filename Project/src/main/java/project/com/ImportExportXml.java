package project.com;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import project.com.schema.Topictype;

public class ImportExportXml {
	
	public JAXBElement<Topictype> importTopic (String xmlName){
		JAXBElement<Topictype> topic=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXBElement.class);
			Unmarshaller u = jc.createUnmarshaller();

			File f = new File(xmlName);
			topic = (JAXBElement<Topictype>) u.unmarshal(f);
			System.out.println("all imported");

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return topic;
	}
	

	
	public String getXMLString(JAXBElement<Topictype> report){
		JAXBContext context;
		StringWriter sw=new StringWriter();
		
		try {
			context = JAXBContext.newInstance(Topictype.class);
			 Marshaller m = context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			 m.marshal(report, sw);
			 System.out.println("EXPORT DONE");
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}
    
	public boolean exportReport(JAXBElement<Topictype> report,String name){
		boolean exported=false;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Topictype.class);
			 Marshaller m = context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 File f = new File("Save/"+name+".xml");
			 m.marshal(report, f);
			 System.out.println("EXPORT DONE");
			 exported=true;
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		return exported;
	}
}
