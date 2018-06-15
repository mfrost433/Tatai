
package com.a03.newlevel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.newlevel package. 
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

    private final static QName _New1To99Response_QNAME = new QName("http://newlevel.a03.com/", "new1to99Response");
    private final static QName _NewGame1To99_QNAME = new QName("http://newlevel.a03.com/", "newGame1to99");
    private final static QName _NewGame1To99Response_QNAME = new QName("http://newlevel.a03.com/", "newGame1to99Response");
    private final static QName _New1To99_QNAME = new QName("http://newlevel.a03.com/", "new1to99");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.newlevel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link New1To99Response }
     * 
     */
    public New1To99Response createNew1To99Response() {
        return new New1To99Response();
    }

    /**
     * Create an instance of {@link NewGame1To99 }
     * 
     */
    public NewGame1To99 createNewGame1To99() {
        return new NewGame1To99();
    }

    /**
     * Create an instance of {@link New1To99 }
     * 
     */
    public New1To99 createNew1To99() {
        return new New1To99();
    }

    /**
     * Create an instance of {@link NewGame1To99Response }
     * 
     */
    public NewGame1To99Response createNewGame1To99Response() {
        return new NewGame1To99Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link New1To99Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://newlevel.a03.com/", name = "new1to99Response")
    public JAXBElement<New1To99Response> createNew1To99Response(New1To99Response value) {
        return new JAXBElement<New1To99Response>(_New1To99Response_QNAME, New1To99Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewGame1To99 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://newlevel.a03.com/", name = "newGame1to99")
    public JAXBElement<NewGame1To99> createNewGame1To99(NewGame1To99 value) {
        return new JAXBElement<NewGame1To99>(_NewGame1To99_QNAME, NewGame1To99 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewGame1To99Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://newlevel.a03.com/", name = "newGame1to99Response")
    public JAXBElement<NewGame1To99Response> createNewGame1To99Response(NewGame1To99Response value) {
        return new JAXBElement<NewGame1To99Response>(_NewGame1To99Response_QNAME, NewGame1To99Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link New1To99 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://newlevel.a03.com/", name = "new1to99")
    public JAXBElement<New1To99> createNew1To99(New1To99 value) {
        return new JAXBElement<New1To99>(_New1To99_QNAME, New1To99 .class, null, value);
    }

}
