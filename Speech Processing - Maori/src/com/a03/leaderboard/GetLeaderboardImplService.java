
package com.a03.leaderboard;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GetLeaderboardImplService", targetNamespace = "http://leaderboard.a03.com/", wsdlLocation = "http://webapp-171011083620.azurewebsites.net:80/Audio/leaderboard?wsdl")
public class GetLeaderboardImplService
    extends Service
{

    private final static URL GETLEADERBOARDIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException GETLEADERBOARDIMPLSERVICE_EXCEPTION;
    private final static QName GETLEADERBOARDIMPLSERVICE_QNAME = new QName("http://leaderboard.a03.com/", "GetLeaderboardImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://webapp-171011083620.azurewebsites.net:80/Audio/leaderboard?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GETLEADERBOARDIMPLSERVICE_WSDL_LOCATION = url;
        GETLEADERBOARDIMPLSERVICE_EXCEPTION = e;
    }

    public GetLeaderboardImplService() {
        super(__getWsdlLocation(), GETLEADERBOARDIMPLSERVICE_QNAME);
    }

    public GetLeaderboardImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), GETLEADERBOARDIMPLSERVICE_QNAME, features);
    }

    public GetLeaderboardImplService(URL wsdlLocation) {
        super(wsdlLocation, GETLEADERBOARDIMPLSERVICE_QNAME);
    }

    public GetLeaderboardImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETLEADERBOARDIMPLSERVICE_QNAME, features);
    }

    public GetLeaderboardImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetLeaderboardImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GetLeaderboardImpl
     */
    @WebEndpoint(name = "GetLeaderboardImplPort")
    public GetLeaderboardImpl getGetLeaderboardImplPort() {
        return super.getPort(new QName("http://leaderboard.a03.com/", "GetLeaderboardImplPort"), GetLeaderboardImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetLeaderboardImpl
     */
    @WebEndpoint(name = "GetLeaderboardImplPort")
    public GetLeaderboardImpl getGetLeaderboardImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://leaderboard.a03.com/", "GetLeaderboardImplPort"), GetLeaderboardImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GETLEADERBOARDIMPLSERVICE_EXCEPTION!= null) {
            throw GETLEADERBOARDIMPLSERVICE_EXCEPTION;
        }
        return GETLEADERBOARDIMPLSERVICE_WSDL_LOCATION;
    }

}
