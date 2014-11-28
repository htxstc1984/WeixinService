
package nc.vo.pub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nc.vo.pub package. 
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

    private final static QName _BusinessException_QNAME = new QName("http://pub.vo.nc/BusinessException", "BusinessException");
    private final static QName _BusinessExceptionErrorCodeString_QNAME = new QName("", "errorCodeString");
    private final static QName _BusinessExceptionHint_QNAME = new QName("", "hint");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nc.vo.pub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessException }
     * 
     */
    public BusinessException createBusinessException() {
        return new BusinessException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pub.vo.nc/BusinessException", name = "BusinessException")
    public JAXBElement<BusinessException> createBusinessException(BusinessException value) {
        return new JAXBElement<BusinessException>(_BusinessException_QNAME, BusinessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "errorCodeString", scope = BusinessException.class)
    public JAXBElement<String> createBusinessExceptionErrorCodeString(String value) {
        return new JAXBElement<String>(_BusinessExceptionErrorCodeString_QNAME, String.class, BusinessException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "hint", scope = BusinessException.class)
    public JAXBElement<String> createBusinessExceptionHint(String value) {
        return new JAXBElement<String>(_BusinessExceptionHint_QNAME, String.class, BusinessException.class, value);
    }

}
