
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
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

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");
    private final static QName _Int_QNAME = new QName("http://tempuri.org/", "int");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnODPCustErrDataResponse }
     * 
     */
    public ReturnODPCustErrDataResponse createReturnODPCustErrDataResponse() {
        return new ReturnODPCustErrDataResponse();
    }

    /**
     * Create an instance of {@link IsWebCustErrDataExist }
     * 
     */
    public IsWebCustErrDataExist createIsWebCustErrDataExist() {
        return new IsWebCustErrDataExist();
    }

    /**
     * Create an instance of {@link IsWebCustCompDataExistResponse }
     * 
     */
    public IsWebCustCompDataExistResponse createIsWebCustCompDataExistResponse() {
        return new IsWebCustCompDataExistResponse();
    }

    /**
     * Create an instance of {@link IsWebCustCompDataExist }
     * 
     */
    public IsWebCustCompDataExist createIsWebCustCompDataExist() {
        return new IsWebCustCompDataExist();
    }

    /**
     * Create an instance of {@link ReturnWebCustCompDataResponse }
     * 
     */
    public ReturnWebCustCompDataResponse createReturnWebCustCompDataResponse() {
        return new ReturnWebCustCompDataResponse();
    }

    /**
     * Create an instance of {@link ReturnWebCustErrData }
     * 
     */
    public ReturnWebCustErrData createReturnWebCustErrData() {
        return new ReturnWebCustErrData();
    }

    /**
     * Create an instance of {@link IsODPCustCompDataExistResponse }
     * 
     */
    public IsODPCustCompDataExistResponse createIsODPCustCompDataExistResponse() {
        return new IsODPCustCompDataExistResponse();
    }

    /**
     * Create an instance of {@link ReturnWebCustErrDataResponse }
     * 
     */
    public ReturnWebCustErrDataResponse createReturnWebCustErrDataResponse() {
        return new ReturnWebCustErrDataResponse();
    }

    /**
     * Create an instance of {@link ReturnODPCustErrData }
     * 
     */
    public ReturnODPCustErrData createReturnODPCustErrData() {
        return new ReturnODPCustErrData();
    }

    /**
     * Create an instance of {@link IsWebCustErrDataExistResponse }
     * 
     */
    public IsWebCustErrDataExistResponse createIsWebCustErrDataExistResponse() {
        return new IsWebCustErrDataExistResponse();
    }

    /**
     * Create an instance of {@link IsODPCustCompDataExist }
     * 
     */
    public IsODPCustCompDataExist createIsODPCustCompDataExist() {
        return new IsODPCustCompDataExist();
    }

    /**
     * Create an instance of {@link IsODPCustErrDataExistResponse }
     * 
     */
    public IsODPCustErrDataExistResponse createIsODPCustErrDataExistResponse() {
        return new IsODPCustErrDataExistResponse();
    }

    /**
     * Create an instance of {@link IsODPCustErrDataExist }
     * 
     */
    public IsODPCustErrDataExist createIsODPCustErrDataExist() {
        return new IsODPCustErrDataExist();
    }

    /**
     * Create an instance of {@link ReturnODPCustCompData }
     * 
     */
    public ReturnODPCustCompData createReturnODPCustCompData() {
        return new ReturnODPCustCompData();
    }

    /**
     * Create an instance of {@link ReturnODPCustCompDataResponse }
     * 
     */
    public ReturnODPCustCompDataResponse createReturnODPCustCompDataResponse() {
        return new ReturnODPCustCompDataResponse();
    }

    /**
     * Create an instance of {@link ReturnWebCustCompData }
     * 
     */
    public ReturnWebCustCompData createReturnWebCustCompData() {
        return new ReturnWebCustCompData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

}
