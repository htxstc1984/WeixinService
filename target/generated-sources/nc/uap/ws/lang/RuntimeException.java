
package nc.uap.ws.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RuntimeException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RuntimeException">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.uap.nc/lang}Exception">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RuntimeException")
@XmlSeeAlso({
    IllegalArgumentException.class,
    NullPointerException.class
})
public class RuntimeException
    extends Exception
{


}
