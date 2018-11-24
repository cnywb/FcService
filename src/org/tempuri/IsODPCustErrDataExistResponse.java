
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IsODPCustErrDataExistResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isODPCustErrDataExistResult"
})
@XmlRootElement(name = "IsODPCustErrDataExistResponse")
public class IsODPCustErrDataExistResponse {

    @XmlElement(name = "IsODPCustErrDataExistResult")
    protected int isODPCustErrDataExistResult;

    /**
     * Gets the value of the isODPCustErrDataExistResult property.
     * 
     */
    public int getIsODPCustErrDataExistResult() {
        return isODPCustErrDataExistResult;
    }

    /**
     * Sets the value of the isODPCustErrDataExistResult property.
     * 
     */
    public void setIsODPCustErrDataExistResult(int value) {
        this.isODPCustErrDataExistResult = value;
    }

}
