
package com.a03.recievemulti;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.recievemulti package. 
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

    private final static QName _RecieveAudioResponse_QNAME = new QName("http://recievemulti.a03.com/", "recieveAudioResponse");
    private final static QName _RecieveAudio_QNAME = new QName("http://recievemulti.a03.com/", "recieveAudio");
    private final static QName _RecieveAudioArg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.recievemulti
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecieveAudio }
     * 
     */
    public RecieveAudio createRecieveAudio() {
        return new RecieveAudio();
    }

    /**
     * Create an instance of {@link RecieveAudioResponse }
     * 
     */
    public RecieveAudioResponse createRecieveAudioResponse() {
        return new RecieveAudioResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecieveAudioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://recievemulti.a03.com/", name = "recieveAudioResponse")
    public JAXBElement<RecieveAudioResponse> createRecieveAudioResponse(RecieveAudioResponse value) {
        return new JAXBElement<RecieveAudioResponse>(_RecieveAudioResponse_QNAME, RecieveAudioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecieveAudio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://recievemulti.a03.com/", name = "recieveAudio")
    public JAXBElement<RecieveAudio> createRecieveAudio(RecieveAudio value) {
        return new JAXBElement<RecieveAudio>(_RecieveAudio_QNAME, RecieveAudio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = RecieveAudio.class)
    public JAXBElement<byte[]> createRecieveAudioArg0(byte[] value) {
        return new JAXBElement<byte[]>(_RecieveAudioArg0_QNAME, byte[].class, RecieveAudio.class, ((byte[]) value));
    }

}
