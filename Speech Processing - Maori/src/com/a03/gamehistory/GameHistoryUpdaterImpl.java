
package com.a03.gamehistory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GameHistoryUpdaterImpl", targetNamespace = "http://gamehistory.a03.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GameHistoryUpdaterImpl {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateHistory", targetNamespace = "http://gamehistory.a03.com/", className = "com.a03.gamehistory.UpdateHistory")
    @ResponseWrapper(localName = "updateHistoryResponse", targetNamespace = "http://gamehistory.a03.com/", className = "com.a03.gamehistory.UpdateHistoryResponse")
    @Action(input = "http://gamehistory.a03.com/GameHistoryUpdaterImpl/updateHistoryRequest", output = "http://gamehistory.a03.com/GameHistoryUpdaterImpl/updateHistoryResponse")
    public void updateHistory(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
