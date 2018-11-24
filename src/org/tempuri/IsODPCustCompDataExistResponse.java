
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
 *         &lt;element name="IsODPCustCompDataExistResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "isODPCustCompDataExistResult"
})
@XmlRootElement(name = "IsODPCustCompDataExistResponse")
public class IsODPCustCompDataExistResponse {

    @XmlElement(name = "IsODPCustCompDataExistResult")
    protected int isODPCustCompDataExistResult;

    /**
     * Gets the value of the isODPCustCompDataExistResult property.
     * 
     */
    public int getIsODPCustCompDataExistResult() {
        return isODPCustCompDataExistResult;
    }

    /**
     * Sets the value of the isODPCustCompDataExistResult property.
     * 
     */
    public void setIsODPCustCompDataExistResult(int value) {
        this.isODPCustCompDataExistResult = value;
    }

}
