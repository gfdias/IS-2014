package org.subscribe;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.3
 * 2014-12-23T05:30:54.862Z
 * Generated source version: 3.0.3
 * 
 */
@WebServiceClient(name = "IUnsubscribeService", 
                  wsdlLocation = "unsubscribe.wsdl",
                  targetNamespace = "http://subscribe.org/") 
public class IUnsubscribeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://subscribe.org/", "IUnsubscribeService");
    public final static QName IUnsubscribePort = new QName("http://subscribe.org/", "IUnsubscribePort");
    static {
        URL url = IUnsubscribeService.class.getResource("unsubscribe.wsdl");
        if (url == null) {
            url = IUnsubscribeService.class.getClassLoader().getResource("unsubscribe.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(IUnsubscribeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "unsubscribe.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public IUnsubscribeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IUnsubscribeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IUnsubscribeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IUnsubscribeService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IUnsubscribeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IUnsubscribeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns IUnsubscribe
     */
    @WebEndpoint(name = "IUnsubscribePort")
    public IUnsubscribe getIUnsubscribePort() {
        return super.getPort(IUnsubscribePort, IUnsubscribe.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IUnsubscribe
     */
    @WebEndpoint(name = "IUnsubscribePort")
    public IUnsubscribe getIUnsubscribePort(WebServiceFeature... features) {
        return super.getPort(IUnsubscribePort, IUnsubscribe.class, features);
    }

}
