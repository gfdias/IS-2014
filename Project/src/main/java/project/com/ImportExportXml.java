package project.com;

import java.io.File;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ImportExportXml {
    
	public static void main(String [] Args) {
		ImportExportXml aux= new ImportExportXml();
		Report report=aux.importReport("report.xml");
		aux.printReportData(report);
		aux.exportReport(report);
		
	}


	public Report importReport (String xmlName){
		Report report=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Report.class);
			Unmarshaller u = jc.createUnmarshaller();

			File f = new File(xmlName);
			report = (Report) u.unmarshal(f);
			System.out.println("all imported");

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return report;
	}
    
	public boolean exportReport(Report report){
		boolean exported=false;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Report.class);
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
	
	public void printReportData(Report report){
		System.out.println("---------------------------------------------");
		for (Iterator<MetricData> metricData = report.getMetricData().iterator(); metricData.hasNext();) {
			MetricData type = (MetricData) metricData.next();
			System.out.println(type.getMetricName());
		}
		System.out.println("---------------------------------------------");
	}
}
