package project.com;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import project.com.schema.Topictype;

public class ImportExportXml {
    

	public JAXBElement<Topictype> importReport (String xmlName){
		JAXBElement<Topictype> report=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(JAXBElement.class);
			Unmarshaller u = jc.createUnmarshaller();

			File f = new File(xmlName);
			report = (JAXBElement<Topictype>) u.unmarshal(f);
			System.out.println("all imported");

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return report;
	}
    
	public boolean exportReport(JAXBElement<Topictype> report){
		boolean exported=false;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Topictype.class);
			 Marshaller m = context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 m.marshal(report, System.out);
			 System.out.println("EXPORT DONE");
			 exported=true;
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		return exported;
	}
}
