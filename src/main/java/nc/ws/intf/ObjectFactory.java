
package nc.ws.intf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import nc.ws.vo.PsnInfoVO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nc.ws.intf package. 
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

    private final static QName _GetPsnPhoneVOsString1_QNAME = new QName("", "string1");
    private final static QName _GetPsnPhoneVOsString_QNAME = new QName("", "string");
    private final static QName _UnbindWeixinResponseReturn_QNAME = new QName("", "return");
    private final static QName _BindWeixinString2_QNAME = new QName("", "string2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nc.ws.intf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPsnPhoneVOs }
     * 
     */
    public GetPsnPhoneVOs createGetPsnPhoneVOs() {
        return new GetPsnPhoneVOs();
    }

    /**
     * Create an instance of {@link UnbindWeixinResponse }
     * 
     */
    public UnbindWeixinResponse createUnbindWeixinResponse() {
        return new UnbindWeixinResponse();
    }

    /**
     * Create an instance of {@link GetDeptsResponse }
     * 
     */
    public GetDeptsResponse createGetDeptsResponse() {
        return new GetDeptsResponse();
    }

    /**
     * Create an instance of {@link ConfirmBindMMS }
     * 
     */
    public ConfirmBindMMS createConfirmBindMMS() {
        return new ConfirmBindMMS();
    }

    /**
     * Create an instance of {@link CheckOpenId }
     * 
     */
    public CheckOpenId createCheckOpenId() {
        return new CheckOpenId();
    }

    /**
     * Create an instance of {@link GetPhonesResponse }
     * 
     */
    public GetPhonesResponse createGetPhonesResponse() {
        return new GetPhonesResponse();
    }

    /**
     * Create an instance of {@link GetPsnPhoneVOsResponse }
     * 
     */
    public GetPsnPhoneVOsResponse createGetPsnPhoneVOsResponse() {
        return new GetPsnPhoneVOsResponse();
    }

    /**
     * Create an instance of {@link BindWeixinResponse }
     * 
     */
    public BindWeixinResponse createBindWeixinResponse() {
        return new BindWeixinResponse();
    }

    /**
     * Create an instance of {@link GetPsnInfosResponse }
     * 
     */
    public GetPsnInfosResponse createGetPsnInfosResponse() {
        return new GetPsnInfosResponse();
    }

    /**
     * Create an instance of {@link ConfirmBindMMSResponse }
     * 
     */
    public ConfirmBindMMSResponse createConfirmBindMMSResponse() {
        return new ConfirmBindMMSResponse();
    }

    /**
     * Create an instance of {@link GetPhones }
     * 
     */
    public GetPhones createGetPhones() {
        return new GetPhones();
    }

    /**
     * Create an instance of {@link BindWeixin }
     * 
     */
    public BindWeixin createBindWeixin() {
        return new BindWeixin();
    }

    /**
     * Create an instance of {@link GetPsnPhone }
     * 
     */
    public GetPsnPhone createGetPsnPhone() {
        return new GetPsnPhone();
    }

    /**
     * Create an instance of {@link CheckOpenIdResponse }
     * 
     */
    public CheckOpenIdResponse createCheckOpenIdResponse() {
        return new CheckOpenIdResponse();
    }

    /**
     * Create an instance of {@link GetDepts }
     * 
     */
    public GetDepts createGetDepts() {
        return new GetDepts();
    }

    /**
     * Create an instance of {@link GetPsnPhoneResponse }
     * 
     */
    public GetPsnPhoneResponse createGetPsnPhoneResponse() {
        return new GetPsnPhoneResponse();
    }

    /**
     * Create an instance of {@link GetPsnInfos }
     * 
     */
    public GetPsnInfos createGetPsnInfos() {
        return new GetPsnInfos();
    }

    /**
     * Create an instance of {@link ConfirmBind }
     * 
     */
    public ConfirmBind createConfirmBind() {
        return new ConfirmBind();
    }

    /**
     * Create an instance of {@link UnbindWeixin }
     * 
     */
    public UnbindWeixin createUnbindWeixin() {
        return new UnbindWeixin();
    }

    /**
     * Create an instance of {@link ConfirmBindResponse }
     * 
     */
    public ConfirmBindResponse createConfirmBindResponse() {
        return new ConfirmBindResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string1", scope = GetPsnPhoneVOs.class)
    public JAXBElement<String> createGetPsnPhoneVOsString1(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString1_QNAME, String.class, GetPsnPhoneVOs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = GetPsnPhoneVOs.class)
    public JAXBElement<String> createGetPsnPhoneVOsString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, GetPsnPhoneVOs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = UnbindWeixinResponse.class)
    public JAXBElement<Integer> createUnbindWeixinResponseReturn(Integer value) {
        return new JAXBElement<Integer>(_UnbindWeixinResponseReturn_QNAME, Integer.class, UnbindWeixinResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ConfirmBindMMSResponse.class)
    public JAXBElement<Integer> createConfirmBindMMSResponseReturn(Integer value) {
        return new JAXBElement<Integer>(_UnbindWeixinResponseReturn_QNAME, Integer.class, ConfirmBindMMSResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = GetPhones.class)
    public JAXBElement<String> createGetPhonesString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, GetPhones.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string2", scope = BindWeixin.class)
    public JAXBElement<String> createBindWeixinString2(String value) {
        return new JAXBElement<String>(_BindWeixinString2_QNAME, String.class, BindWeixin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string1", scope = BindWeixin.class)
    public JAXBElement<String> createBindWeixinString1(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString1_QNAME, String.class, BindWeixin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = BindWeixin.class)
    public JAXBElement<String> createBindWeixinString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, BindWeixin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string1", scope = GetPsnPhone.class)
    public JAXBElement<String> createGetPsnPhoneString1(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString1_QNAME, String.class, GetPsnPhone.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = GetPsnPhone.class)
    public JAXBElement<String> createGetPsnPhoneString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, GetPsnPhone.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PsnInfoVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = CheckOpenIdResponse.class)
    public JAXBElement<PsnInfoVO> createCheckOpenIdResponseReturn(PsnInfoVO value) {
        return new JAXBElement<PsnInfoVO>(_UnbindWeixinResponseReturn_QNAME, PsnInfoVO.class, CheckOpenIdResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string1", scope = ConfirmBindMMS.class)
    public JAXBElement<String> createConfirmBindMMSString1(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString1_QNAME, String.class, ConfirmBindMMS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = ConfirmBindMMS.class)
    public JAXBElement<String> createConfirmBindMMSString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, ConfirmBindMMS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = CheckOpenId.class)
    public JAXBElement<String> createCheckOpenIdString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, CheckOpenId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPsnPhoneResponse.class)
    public JAXBElement<String> createGetPsnPhoneResponseReturn(String value) {
        return new JAXBElement<String>(_UnbindWeixinResponseReturn_QNAME, String.class, GetPsnPhoneResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = GetPsnInfos.class)
    public JAXBElement<String> createGetPsnInfosString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, GetPsnInfos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = ConfirmBind.class)
    public JAXBElement<String> createConfirmBindString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, ConfirmBind.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "string", scope = UnbindWeixin.class)
    public JAXBElement<String> createUnbindWeixinString(String value) {
        return new JAXBElement<String>(_GetPsnPhoneVOsString_QNAME, String.class, UnbindWeixin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ConfirmBindResponse.class)
    public JAXBElement<Integer> createConfirmBindResponseReturn(Integer value) {
        return new JAXBElement<Integer>(_UnbindWeixinResponseReturn_QNAME, Integer.class, ConfirmBindResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPsnPhoneVOsResponse.class)
    public JAXBElement<String> createGetPsnPhoneVOsResponseReturn(String value) {
        return new JAXBElement<String>(_UnbindWeixinResponseReturn_QNAME, String.class, GetPsnPhoneVOsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = BindWeixinResponse.class)
    public JAXBElement<Integer> createBindWeixinResponseReturn(Integer value) {
        return new JAXBElement<Integer>(_UnbindWeixinResponseReturn_QNAME, Integer.class, BindWeixinResponse.class, value);
    }

}
