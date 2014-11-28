
package nc.bs.dao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nc.bs.dao package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DAOException_QNAME = new QName("http://dao.bs.nc/DAOException", "DAOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nc.bs.dao
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DAOException }
     * 
     */
    public DAOException createDAOException() {
        return new DAOException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DAOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.bs.nc/DAOException", name = "DAOException")
    public JAXBElement<DAOException> createDAOException(DAOException value) {
        return new JAXBElement<DAOException>(_DAOException_QNAME, DAOException.class, null, value);
    }

}
