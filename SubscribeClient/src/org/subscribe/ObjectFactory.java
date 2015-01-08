
package org.subscribe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.subscribe package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsubscribeUser_QNAME = new QName("http://subscribe.org/", "unsubscribeUser");
    private final static QName _UnsubscribeUserResponse_QNAME = new QName("http://subscribe.org/", "unsubscribeUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.subscribe
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnsubscribeUser }
     * 
     */
    public UnsubscribeUser createUnsubscribeUser() {
        return new UnsubscribeUser();
    }

    /**
     * Create an instance of {@link UnsubscribeUserResponse }
     * 
     */
    public UnsubscribeUserResponse createUnsubscribeUserResponse() {
        return new UnsubscribeUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://subscribe.org/", name = "unsubscribeUser")
    public JAXBElement<UnsubscribeUser> createUnsubscribeUser(UnsubscribeUser value) {
        return new JAXBElement<UnsubscribeUser>(_UnsubscribeUser_QNAME, UnsubscribeUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://subscribe.org/", name = "unsubscribeUserResponse")
    public JAXBElement<UnsubscribeUserResponse> createUnsubscribeUserResponse(UnsubscribeUserResponse value) {
        return new JAXBElement<UnsubscribeUserResponse>(_UnsubscribeUserResponse_QNAME, UnsubscribeUserResponse.class, null, value);
    }

}
