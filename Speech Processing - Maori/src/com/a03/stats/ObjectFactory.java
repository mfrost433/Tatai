
package com.a03.stats;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.a03.game.Stats;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.stats package. 
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

    private final static QName _GetStats_QNAME = new QName("http://stats.a03.com/", "getStats");
    private final static QName _GetStatsResponse_QNAME = new QName("http://stats.a03.com/", "getStatsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.stats
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStats }
     * 
     */
    public GetStats createGetStats() {
        return new GetStats();
    }

    /**
     * Create an instance of {@link GetStatsResponse }
     * 
     */
    public GetStatsResponse createGetStatsResponse() {
        return new GetStatsResponse();
    }

    /**
     * Create an instance of {@link Stats }
     * 
     */
    public Stats createStats() {
        return new Stats();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStats }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStats }{@code >}
     */
    @XmlElementDecl(namespace = "http://stats.a03.com/", name = "getStats")
    public JAXBElement<GetStats> createGetStats(GetStats value) {
        return new JAXBElement<GetStats>(_GetStats_QNAME, GetStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStatsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://stats.a03.com/", name = "getStatsResponse")
    public JAXBElement<GetStatsResponse> createGetStatsResponse(GetStatsResponse value) {
        return new JAXBElement<GetStatsResponse>(_GetStatsResponse_QNAME, GetStatsResponse.class, null, value);
    }

}
