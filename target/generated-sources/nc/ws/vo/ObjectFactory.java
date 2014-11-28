
package nc.ws.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nc.ws.vo package. 
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

    private final static QName _PsnInfoVO_QNAME = new QName("http://vo.ws.nc/PsnInfoVO", "PsnInfoVO");
    private final static QName _PhoneVO_QNAME = new QName("http://vo.ws.nc/PhoneVO", "PhoneVO");
    private final static QName _DepartmentVO_QNAME = new QName("http://vo.ws.nc/DepartmentVO", "DepartmentVO");
    private final static QName _PsnInfoVOSex_QNAME = new QName("", "sex");
    private final static QName _PsnInfoVOUnitname_QNAME = new QName("", "unitname");
    private final static QName _PsnInfoVOEmail_QNAME = new QName("", "email");
    private final static QName _PsnInfoVOPsnname_QNAME = new QName("", "psnname");
    private final static QName _PsnInfoVOResultCode_QNAME = new QName("", "resultCode");
    private final static QName _PsnInfoVODeptname_QNAME = new QName("", "deptname");
    private final static QName _PsnInfoVOPsncode_QNAME = new QName("", "psncode");
    private final static QName _PsnInfoVOMobile_QNAME = new QName("", "mobile");
    private final static QName _DepartmentVODeptcode_QNAME = new QName("", "deptcode");
    private final static QName _PhoneVOSort_QNAME = new QName("", "sort");
    private final static QName _PhoneVOSubdept_QNAME = new QName("", "subdept");
    private final static QName _PhoneVOName_QNAME = new QName("", "name");
    private final static QName _PhoneVOPostId_QNAME = new QName("", "post_id");
    private final static QName _PhoneVOPhone1_QNAME = new QName("", "phone1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nc.ws.vo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PsnInfoVO }
     * 
     */
    public PsnInfoVO createPsnInfoVO() {
        return new PsnInfoVO();
    }

    /**
     * Create an instance of {@link PhoneVO }
     * 
     */
    public PhoneVO createPhoneVO() {
        return new PhoneVO();
    }

    /**
     * Create an instance of {@link DepartmentVO }
     * 
     */
    public DepartmentVO createDepartmentVO() {
        return new DepartmentVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PsnInfoVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.ws.nc/PsnInfoVO", name = "PsnInfoVO")
    public JAXBElement<PsnInfoVO> createPsnInfoVO(PsnInfoVO value) {
        return new JAXBElement<PsnInfoVO>(_PsnInfoVO_QNAME, PsnInfoVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.ws.nc/PhoneVO", name = "PhoneVO")
    public JAXBElement<PhoneVO> createPhoneVO(PhoneVO value) {
        return new JAXBElement<PhoneVO>(_PhoneVO_QNAME, PhoneVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.ws.nc/DepartmentVO", name = "DepartmentVO")
    public JAXBElement<DepartmentVO> createDepartmentVO(DepartmentVO value) {
        return new JAXBElement<DepartmentVO>(_DepartmentVO_QNAME, DepartmentVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sex", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOSex(String value) {
        return new JAXBElement<String>(_PsnInfoVOSex_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "unitname", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOUnitname(String value) {
        return new JAXBElement<String>(_PsnInfoVOUnitname_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "email", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOEmail(String value) {
        return new JAXBElement<String>(_PsnInfoVOEmail_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "psnname", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOPsnname(String value) {
        return new JAXBElement<String>(_PsnInfoVOPsnname_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "resultCode", scope = PsnInfoVO.class)
    public JAXBElement<Integer> createPsnInfoVOResultCode(Integer value) {
        return new JAXBElement<Integer>(_PsnInfoVOResultCode_QNAME, Integer.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deptname", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVODeptname(String value) {
        return new JAXBElement<String>(_PsnInfoVODeptname_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "psncode", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOPsncode(String value) {
        return new JAXBElement<String>(_PsnInfoVOPsncode_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mobile", scope = PsnInfoVO.class)
    public JAXBElement<String> createPsnInfoVOMobile(String value) {
        return new JAXBElement<String>(_PsnInfoVOMobile_QNAME, String.class, PsnInfoVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deptcode", scope = DepartmentVO.class)
    public JAXBElement<String> createDepartmentVODeptcode(String value) {
        return new JAXBElement<String>(_DepartmentVODeptcode_QNAME, String.class, DepartmentVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deptname", scope = DepartmentVO.class)
    public JAXBElement<String> createDepartmentVODeptname(String value) {
        return new JAXBElement<String>(_PsnInfoVODeptname_QNAME, String.class, DepartmentVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sort", scope = PhoneVO.class)
    public JAXBElement<Integer> createPhoneVOSort(Integer value) {
        return new JAXBElement<Integer>(_PhoneVOSort_QNAME, Integer.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "email", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVOEmail(String value) {
        return new JAXBElement<String>(_PsnInfoVOEmail_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deptcode", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVODeptcode(String value) {
        return new JAXBElement<String>(_DepartmentVODeptcode_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subdept", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVOSubdept(String value) {
        return new JAXBElement<String>(_PhoneVOSubdept_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVOName(String value) {
        return new JAXBElement<String>(_PhoneVOName_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "post_id", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVOPostId(String value) {
        return new JAXBElement<String>(_PhoneVOPostId_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deptname", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVODeptname(String value) {
        return new JAXBElement<String>(_PsnInfoVODeptname_QNAME, String.class, PhoneVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "phone1", scope = PhoneVO.class)
    public JAXBElement<String> createPhoneVOPhone1(String value) {
        return new JAXBElement<String>(_PhoneVOPhone1_QNAME, String.class, PhoneVO.class, value);
    }

}
