
package nc.vo.pub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import nc.bs.dao.DAOException;
import nc.uap.ws.lang.Exception;


/**
 * <p>Java class for BusinessException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessException">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.uap.nc/lang}Exception">
 *       &lt;sequence>
 *         &lt;element name="errorCodeString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessException", propOrder = {
    "errorCodeString",
    "hint"
})
@XmlSeeAlso({
    DAOException.class
})
public class BusinessException
    extends Exception
{

    @XmlElementRef(name = "errorCodeString", type = JAXBElement.class)
    protected JAXBElement<String> errorCodeString;
    @XmlElementRef(name = "hint", type = JAXBElement.class)
    protected JAXBElement<String> hint;

    /**
     * Gets the value of the errorCodeString property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorCodeString() {
        return errorCodeString;
    }

    /**
     * Sets the value of the errorCodeString property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorCodeString(JAXBElement<String> value) {
        this.errorCodeString = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the hint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHint() {
        return hint;
    }

    /**
     * Sets the value of the hint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHint(JAXBElement<String> value) {
        this.hint = ((JAXBElement<String> ) value);
    }

}
