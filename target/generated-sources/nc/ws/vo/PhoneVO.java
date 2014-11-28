
package nc.ws.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import nc.uap.ws.lang.SuperVO;


/**
 * <p>Java class for PhoneVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneVO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.uap.nc/lang}SuperVO">
 *       &lt;sequence>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subdept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="post_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneVO", namespace = "http://vo.ws.nc/PhoneVO", propOrder = {
    "sort",
    "email",
    "subdept",
    "deptcode",
    "name",
    "postId",
    "phone1",
    "deptname"
})
public class PhoneVO
    extends SuperVO
{

    @XmlElementRef(name = "sort", type = JAXBElement.class)
    protected JAXBElement<Integer> sort;
    @XmlElementRef(name = "email", type = JAXBElement.class)
    protected JAXBElement<String> email;
    @XmlElementRef(name = "subdept", type = JAXBElement.class)
    protected JAXBElement<String> subdept;
    @XmlElementRef(name = "deptcode", type = JAXBElement.class)
    protected JAXBElement<String> deptcode;
    @XmlElementRef(name = "name", type = JAXBElement.class)
    protected JAXBElement<String> name;
    @XmlElementRef(name = "post_id", type = JAXBElement.class)
    protected JAXBElement<String> postId;
    @XmlElementRef(name = "phone1", type = JAXBElement.class)
    protected JAXBElement<String> phone1;
    @XmlElementRef(name = "deptname", type = JAXBElement.class)
    protected JAXBElement<String> deptname;

    /**
     * Gets the value of the sort property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSort() {
        return sort;
    }

    /**
     * Sets the value of the sort property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSort(JAXBElement<Integer> value) {
        this.sort = ((JAXBElement<Integer> ) value);
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
     * Gets the value of the subdept property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubdept() {
        return subdept;
    }

    /**
     * Sets the value of the subdept property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubdept(JAXBElement<String> value) {
        this.subdept = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the deptcode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeptcode() {
        return deptcode;
    }

    /**
     * Sets the value of the deptcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeptcode(JAXBElement<String> value) {
        this.deptcode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the postId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPostId() {
        return postId;
    }

    /**
     * Sets the value of the postId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPostId(JAXBElement<String> value) {
        this.postId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the phone1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPhone1() {
        return phone1;
    }

    /**
     * Sets the value of the phone1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPhone1(JAXBElement<String> value) {
        this.phone1 = ((JAXBElement<String> ) value);
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

}
