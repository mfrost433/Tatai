
package com.a03.gamehistory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.gamehistory package. 
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

    private final static QName _GetGameHistoryResponse_QNAME = new QName("http://gamehistory.a03.com/", "getGameHistoryResponse");
    private final static QName _GetGameHistory_QNAME = new QName("http://gamehistory.a03.com/", "getGameHistory");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.gamehistory
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetGameHistoryResponse }
     * 
     */
    public GetGameHistoryResponse createGetGameHistoryResponse() {
        return new GetGameHistoryResponse();
    }

    /**
     * Create an instance of {@link GetGameHistory }
     * 
     */
    public GetGameHistory createGetGameHistory() {
        return new GetGameHistory();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gamehistory.a03.com/", name = "getGameHistoryResponse")
    public JAXBElement<GetGameHistoryResponse> createGetGameHistoryResponse(GetGameHistoryResponse value) {
        return new JAXBElement<GetGameHistoryResponse>(_GetGameHistoryResponse_QNAME, GetGameHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gamehistory.a03.com/", name = "getGameHistory")
    public JAXBElement<GetGameHistory> createGetGameHistory(GetGameHistory value) {
        return new JAXBElement<GetGameHistory>(_GetGameHistory_QNAME, GetGameHistory.class, null, value);
    }

}
