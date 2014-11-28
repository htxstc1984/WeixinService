
package nc.ws.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import nc.uap.ws.lang.SuperVO;


/**
 * <p>Java class for DepartmentVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepartmentVO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.uap.nc/lang}SuperVO">
 *       &lt;sequence>
 *         &lt;element name="deptcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DepartmentVO", propOrder = {
    "deptcode",
    "deptname"
})
public class DepartmentVO
    extends SuperVO
{

    @XmlElementRef(name = "deptcode", type = JAXBElement.class)
    protected JAXBElement<String> deptcode;
    @XmlElementRef(name = "deptname", type = JAXBElement.class)
    protected JAXBElement<String> deptname;

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
