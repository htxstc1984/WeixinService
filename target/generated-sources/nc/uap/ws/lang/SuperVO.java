
package nc.uap.ws.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import nc.ws.vo.DepartmentVO;
import nc.ws.vo.PhoneVO;
import nc.ws.vo.PsnInfoVO;


/**
 * <p>Java class for SuperVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SuperVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dirty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="primaryKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuperVO", propOrder = {
    "status",
    "dirty",
    "primaryKey"
})
@XmlSeeAlso({
    PhoneVO.class,
    PsnInfoVO.class,
    DepartmentVO.class
})
public abstract class SuperVO {

    protected int status;
    protected boolean dirty;
    @XmlElement(required = true)
    protected String primaryKey;

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the dirty property.
     * 
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Sets the value of the dirty property.
     * 
     */
    public void setDirty(boolean value) {
        this.dirty = value;
    }

    /**
     * Gets the value of the primaryKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the value of the primaryKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryKey(String value) {
        this.primaryKey = value;
    }

}
