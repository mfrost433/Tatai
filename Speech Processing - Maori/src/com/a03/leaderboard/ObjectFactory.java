
package com.a03.leaderboard;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.leaderboard package. 
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

    private final static QName _GetLeaderBoardInfo_QNAME = new QName("http://leaderboard.a03.com/", "getLeaderBoardInfo");
    private final static QName _GetLeaderBoardInfoResponse_QNAME = new QName("http://leaderboard.a03.com/", "getLeaderBoardInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.leaderboard
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLeaderBoardInfo }
     * 
     */
    public GetLeaderBoardInfo createGetLeaderBoardInfo() {
        return new GetLeaderBoardInfo();
    }

    /**
     * Create an instance of {@link GetLeaderBoardInfoResponse }
     * 
     */
    public GetLeaderBoardInfoResponse createGetLeaderBoardInfoResponse() {
        return new GetLeaderBoardInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLeaderBoardInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leaderboard.a03.com/", name = "getLeaderBoardInfo")
    public JAXBElement<GetLeaderBoardInfo> createGetLeaderBoardInfo(GetLeaderBoardInfo value) {
        return new JAXBElement<GetLeaderBoardInfo>(_GetLeaderBoardInfo_QNAME, GetLeaderBoardInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLeaderBoardInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://leaderboard.a03.com/", name = "getLeaderBoardInfoResponse")
    public JAXBElement<GetLeaderBoardInfoResponse> createGetLeaderBoardInfoResponse(GetLeaderBoardInfoResponse value) {
        return new JAXBElement<GetLeaderBoardInfoResponse>(_GetLeaderBoardInfoResponse_QNAME, GetLeaderBoardInfoResponse.class, null, value);
    }

}
