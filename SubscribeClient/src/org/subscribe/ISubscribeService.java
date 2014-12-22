package org.subscribe;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.3
 * 2014-12-21T20:53:09.782Z
 * Generated source version: 3.0.3
 * 
 */
@WebServiceClient(name = "ISubscribeService", 
                  wsdlLocation = "subscribe.wsdl",
                  targetNamespace = "http://subscribe.org/") 
public class ISubscribeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://subscribe.org/", "ISubscribeService");
    public final static QName ISubscribePort = new QName("http://subscribe.org/", "ISubscribePort");
    static {
        URL url = ISubscribeService.class.getResource("subscribe.wsdl");
        if (url == null) {
            url = ISubscribeService.class.getClassLoader().getResource("subscribe.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(ISubscribeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "subscribe.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public ISubscribeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ISubscribeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ISubscribeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ISubscribeService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ISubscribeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ISubscribeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns ISubscribe
     */
    @WebEndpoint(name = "ISubscribePort")
    public ISubscribe getISubscribePort() {
        return super.getPort(ISubscribePort, ISubscribe.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ISubscribe
     */
    @WebEndpoint(name = "ISubscribePort")
    public ISubscribe getISubscribePort(WebServiceFeature... features) {
        return super.getPort(ISubscribePort, ISubscribe.class, features);
    }

}
