
package com.a03.multiplayer;

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

    private final static QName _ResumeGameResponse_QNAME = new QName("http://multiplayer.a03.com/", "resumeGameResponse");
    private final static QName _NextMultiplayerLevelResponse_QNAME = new QName("http://multiplayer.a03.com/", "nextMultiplayerLevelResponse");
    private final static QName _NextMultiplayerLevel_QNAME = new QName("http://multiplayer.a03.com/", "nextMultiplayerLevel");
    private final static QName _ResumeGame_QNAME = new QName("http://multiplayer.a03.com/", "resumeGame");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a03.multiplayer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NextMultiplayerLevelResponse }
     * 
     */
    public NextMultiplayerLevelResponse createNextMultiplayerLevelResponse() {
        return new NextMultiplayerLevelResponse();
    }

    /**
     * Create an instance of {@link NextMultiplayerLevel }
     * 
     */
    public NextMultiplayerLevel createNextMultiplayerLevel() {
        return new NextMultiplayerLevel();
    }

    /**
     * Create an instance of {@link ResumeGame }
     * 
     */
    public ResumeGame createResumeGame() {
        return new ResumeGame();
    }

    /**
     * Create an instance of {@link ResumeGameResponse }
     * 
     */
    public ResumeGameResponse createResumeGameResponse() {
        return new ResumeGameResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResumeGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "resumeGameResponse")
    public JAXBElement<ResumeGameResponse> createResumeGameResponse(ResumeGameResponse value) {
        return new JAXBElement<ResumeGameResponse>(_ResumeGameResponse_QNAME, ResumeGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NextMultiplayerLevelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "nextMultiplayerLevelResponse")
    public JAXBElement<NextMultiplayerLevelResponse> createNextMultiplayerLevelResponse(NextMultiplayerLevelResponse value) {
        return new JAXBElement<NextMultiplayerLevelResponse>(_NextMultiplayerLevelResponse_QNAME, NextMultiplayerLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NextMultiplayerLevel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "nextMultiplayerLevel")
    public JAXBElement<NextMultiplayerLevel> createNextMultiplayerLevel(NextMultiplayerLevel value) {
        return new JAXBElement<NextMultiplayerLevel>(_NextMultiplayerLevel_QNAME, NextMultiplayerLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResumeGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://multiplayer.a03.com/", name = "resumeGame")
    public JAXBElement<ResumeGame> createResumeGame(ResumeGame value) {
        return new JAXBElement<ResumeGame>(_ResumeGame_QNAME, ResumeGame.class, null, value);
    }

}
