
package nc.ws.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import nc.uap.ws.lang.SuperVO;


/**
 * <p>Java class for PsnInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PsnInfoVO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.uap.nc/lang}SuperVO">
 *       &lt;sequence>
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psnname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deptname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PsnInfoVO", namespace = "http://vo.ws.nc/PsnInfoVO", propOrder = {
    "sex",
    "unitname",
    "email",
    "psnname",
    "resultCode",
    "deptname",
    "psncode",
    "mobile"
})
public class PsnInfoVO
    extends SuperVO
{

    @XmlElementRef(name = "sex", type = JAXBElement.class)
    protected JAXBElement<String> sex;
    @XmlElementRef(name = "unitname", type = JAXBElement.class)
    protected JAXBElement<String> unitname;
    @XmlElementRef(name = "email", type = JAXBElement.class)
    protected JAXBElement<String> email;
    @XmlElementRef(name = "psnname", type = JAXBElement.class)
    protected JAXBElement<String> psnname;
    @XmlElementRef(name = "resultCode", type = JAXBElement.class)
    protected JAXBElement<Integer> resultCode;
    @XmlElementRef(name = "deptname", type = JAXBElement.class)
    protected JAXBElement<String> deptname;
    @XmlElementRef(name = "psncode", type = JAXBElement.class)
    protected JAXBElement<String> psncode;
    @XmlElementRef(name = "mobile", type = JAXBElement.class)
    protected JAXBElement<String> mobile;

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSex(JAXBElement<String> value) {
        this.sex = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the unitname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUnitname() {
        return unitname;
    }

    /**
     * Sets the value of the unitname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUnitname(JAXBElement<String> value) {
        this.unitname = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmail(JAXBElement<String> value) {
        this.email = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the psnname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPsnname() {
        return psnname;
    }

    /**
     * Sets the value of the psnname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPsnname(JAXBElement<String> value) {
        this.psnname = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setResultCode(JAXBElement<Integer> value) {
        this.resultCode = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the deptname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeptname() {
        return deptname;
    }

    /**
     * Sets the value of the deptname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeptname(JAXBElement<String> value) {
        this.deptname = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the psncode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPsncode() {
        return psncode;
    }

    /**
     * Sets the value of the psncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPsncode(JAXBElement<String> value) {
        this.psncode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMobile(JAXBElement<String> value) {
        this.mobile = ((JAXBElement<String> ) value);
    }

}
