
package com.a03.recieveaudio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.recieveaudio package. 
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

    private final static QName _PlayAudioResponse_QNAME = new QName("http://recieveaudio.a03.com/", "playAudioResponse");
    private final static QName _PlayAudio_QNAME = new QName("http://recieveaudio.a03.com/", "playAudio");
    private final static QName _PlayAudioArg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.recieveaudio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlayAudio }
     * 
     */
    public PlayAudio createPlayAudio() {
        return new PlayAudio();
    }

    /**
     * Create an instance of {@link PlayAudioResponse }
     * 
     */
    public PlayAudioResponse createPlayAudioResponse() {
        return new PlayAudioResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayAudioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://recieveaudio.a03.com/", name = "playAudioResponse")
    public JAXBElement<PlayAudioResponse> createPlayAudioResponse(PlayAudioResponse value) {
        return new JAXBElement<PlayAudioResponse>(_PlayAudioResponse_QNAME, PlayAudioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayAudio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://recieveaudio.a03.com/", name = "playAudio")
    public JAXBElement<PlayAudio> createPlayAudio(PlayAudio value) {
        return new JAXBElement<PlayAudio>(_PlayAudio_QNAME, PlayAudio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = PlayAudio.class)
    public JAXBElement<byte[]> createPlayAudioArg0(byte[] value) {
        return new JAXBElement<byte[]>(_PlayAudioArg0_QNAME, byte[].class, PlayAudio.class, ((byte[]) value));
    }

}
