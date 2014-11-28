
package nc.bs.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import nc.vo.pub.BusinessException;


/**
 * <p>Java class for DAOException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DAOException">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pub.vo.nc/BusinessException}BusinessException">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DAOException")
public class DAOException
    extends BusinessException
{


}
