
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
 *         &lt;element name="IsWebCustCompDataExistResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "isWebCustCompDataExistResult"
})
@XmlRootElement(name = "IsWebCustCompDataExistResponse")
public class IsWebCustCompDataExistResponse {

    @XmlElement(name = "IsWebCustCompDataExistResult")
    protected int isWebCustCompDataExistResult;

    /**
     * Gets the value of the isWebCustCompDataExistResult property.
     * 
     */
    public int getIsWebCustCompDataExistResult() {
        return isWebCustCompDataExistResult;
    }

    /**
     * Sets the value of the isWebCustCompDataExistResult property.
     * 
     */
    public void setIsWebCustCompDataExistResult(int value) {
        this.isWebCustCompDataExistResult = value;
    }

}
