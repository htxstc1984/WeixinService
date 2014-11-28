
package cn.com.webxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getStockImageByteByCodeResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getStockImageByteByCodeResult"
})
@XmlRootElement(name = "getStockImageByteByCodeResponse")
public class GetStockImageByteByCodeResponse {

    protected byte[] getStockImageByteByCodeResult;

    /**
     * Gets the value of the getStockImageByteByCodeResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetStockImageByteByCodeResult() {
        return getStockImageByteByCodeResult;
    }

    /**
     * Sets the value of the getStockImageByteByCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetStockImageByteByCodeResult(byte[] value) {
        this.getStockImageByteByCodeResult = ((byte[]) value);
    }

}
