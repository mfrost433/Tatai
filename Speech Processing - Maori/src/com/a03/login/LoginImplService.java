
package com.a03.login;

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
@WebServiceClient(name = "LoginImplService", targetNamespace = "http://login.a03.com/", wsdlLocation = "http://webapp-171011083620.azurewebsites.net:80/Audio/login?wsdl")
public class LoginImplService
    extends Service
{

    private final static URL LOGINIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException LOGINIMPLSERVICE_EXCEPTION;
    private final static QName LOGINIMPLSERVICE_QNAME = new QName("http://login.a03.com/", "LoginImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://webapp-171011083620.azurewebsites.net:80/Audio/login?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LOGINIMPLSERVICE_WSDL_LOCATION = url;
        LOGINIMPLSERVICE_EXCEPTION = e;
    }

    public LoginImplService() {
        super(__getWsdlLocation(), LOGINIMPLSERVICE_QNAME);
    }

    public LoginImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LOGINIMPLSERVICE_QNAME, features);
    }

    public LoginImplService(URL wsdlLocation) {
        super(wsdlLocation, LOGINIMPLSERVICE_QNAME);
    }

    public LoginImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LOGINIMPLSERVICE_QNAME, features);
    }

    public LoginImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LoginImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LoginImpl
     */
    @WebEndpoint(name = "LoginImplPort")
    public LoginImpl getLoginImplPort() {
        return super.getPort(new QName("http://login.a03.com/", "LoginImplPort"), LoginImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LoginImpl
     */
    @WebEndpoint(name = "LoginImplPort")
    public LoginImpl getLoginImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://login.a03.com/", "LoginImplPort"), LoginImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LOGINIMPLSERVICE_EXCEPTION!= null) {
            throw LOGINIMPLSERVICE_EXCEPTION;
        }
        return LOGINIMPLSERVICE_WSDL_LOCATION;
    }

}
