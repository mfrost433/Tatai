
package com.a03.multiplayer.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a03.multiplayer package. 
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

    private final static QName _AlreadyChallengedResponse_QNAME = new QName("http://multiplayer.a03.com/", "alreadyChallengedResponse");
    private final static QName _NewHardMultiplayerGame_QNAME = new QName("http://multiplayer.a03.com/", "newHardMultiplayerGame");
    private final static QName _NewHardMultiplayerGameResponse_QNAME = new QName("http://multiplayer.a03.com/", "newHardMultiplayerGameResponse");
    private final static QName _AlreadyChallenged_QNAME = new QName("http://multiplayer.a03.com/", "alreadyChallenged");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.multiplayer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AlreadyChallengedResponse }
     * 
     */
    public AlreadyChallengedResponse createAlreadyChallengedResponse() {
        return new AlreadyChallengedResponse();
    }

    /**
     * Create an instance of {@link NewHardMultiplayerGame }
     * 
     */
    public NewHardMultiplayerGame createNewHardMultiplayerGame() {
        return new NewHardMultiplayerGame();
    }

    /**
     * Create an instance of {@link NewHardMultiplayerGameResponse }
     * 
     */
    public NewHardMultiplayerGameResponse createNewHardMultiplayerGameResponse() {
        return new NewHardMultiplayerGameResponse();
    }

    /**
     * Create an instance of {@link AlreadyChallenged }
     * 
     */
    public AlreadyChallenged createAlreadyChallenged() {
        return new AlreadyChallenged();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyChallengedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "alreadyChallengedResponse")
    public JAXBElement<AlreadyChallengedResponse> createAlreadyChallengedResponse(AlreadyChallengedResponse value) {
        return new JAXBElement<AlreadyChallengedResponse>(_AlreadyChallengedResponse_QNAME, AlreadyChallengedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewHardMultiplayerGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "newHardMultiplayerGame")
    public JAXBElement<NewHardMultiplayerGame> createNewHardMultiplayerGame(NewHardMultiplayerGame value) {
        return new JAXBElement<NewHardMultiplayerGame>(_NewHardMultiplayerGame_QNAME, NewHardMultiplayerGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewHardMultiplayerGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "newHardMultiplayerGameResponse")
    public JAXBElement<NewHardMultiplayerGameResponse> createNewHardMultiplayerGameResponse(NewHardMultiplayerGameResponse value) {
        return new JAXBElement<NewHardMultiplayerGameResponse>(_NewHardMultiplayerGameResponse_QNAME, NewHardMultiplayerGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyChallenged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "alreadyChallenged")
    public JAXBElement<AlreadyChallenged> createAlreadyChallenged(AlreadyChallenged value) {
        return new JAXBElement<AlreadyChallenged>(_AlreadyChallenged_QNAME, AlreadyChallenged.class, null, value);
    }

}
