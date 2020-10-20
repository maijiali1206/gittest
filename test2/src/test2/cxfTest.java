package test2;
import java.util.Date;
import java.util.Iterator;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class cxfTest {
	public static String getRes(String xmlString, String callFunctionName) {
		//长沙银行访问原理：我们转换后的地址为10.97.1.101，访问云中心10.97.1.180:8341->政采贷10.32.42.1:8341
		// 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //https://10.97.1.180:8341/ContractLoanCXF/services/ECB?wsdl
        //https://10.32.42.1:8341/ContractLoanCXF/services/ECB?wsdl
        //Client client = dcf.createClient("http://localhost:8088/ContractLoan/services/TestWebService?wsdl");
        //Client client = dcf.createClient("http://localhost:8088/ContractLoanCXF/services/ECB?wsdl");
        //Client client = dcf.createClient("https://127.0.0.1:8081/ContractLoan/services/ECB?wsdl");
        //Client client = dcf.createClient("https://127.0.0.1:8088/ContractLoanCXF/services/ECB?wsdl");
        Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
        //Client client = dcf.createClient("https://localhost/ContractLoan/services/ECB?wsdl");
        //Client client = dcf.createClient("http://localhost:8061/ContractLoan/services/ECB?wsdl");
        //Client client = dcf.createClient("http://chinacreator-beijing.c2cloud.cn/pecb/services/ECB?wsdl");
        //Client client = dcf.createClient("http://192.168.2.213:8090/ContractLoanCXF/services/ECB?wsdl");
        //Client client = dcf.createClient("http://111.8.120.245:8090/ContractLoanCXF/services/ECB?wsdl");
        //Client client = dcf.createClient("https://111.8.120.245:8090/ContractLoanCXF/services/ECB?wsdl");
        
        // 设置超时单位为毫秒
        HTTPConduit conduit = (HTTPConduit)client.getConduit();  
        HTTPClientPolicy policy = new HTTPClientPolicy();  
        policy.setConnectionTimeout(120000);
        policy.setAllowChunking(false);
        policy.setReceiveTimeout(120000);
        conduit.setClient(policy);

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(callFunctionName, xmlString);
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
		return objects[0].toString();
    		   
	}
	
	
	public static void main(String[] args) {
		String xmlString = null;
		//根据银行代码和日期获取融资合同支付情况
		//xmlString = getContractPayInfo();
		
		//根据合同编号获取合同信息
		xmlString = getPrcmContractNo();
		
		//报送合同融资的还款情况
		//xmlString = setRzRepayInfo();
				
		//查询修改账号是否成功
		//xmlString = queryAccountModifyStatus();
		
		//获取当天所有成交结果信息
		//xmlString = getPrcmResultAll();
		
		//获取当天所有采购合同信息
		//xmlString = getContractResultAll();
		
		//根据供应商名称和统一社会信息代码获取其成交信息
		//xmlString = getPrcmSupplierResultAll();
		
		//根据供应商名称和统一社会信息代码获取其合同信息
		//xmlString = getContractSupplierResultAll();
				
				

		//本地
		/*System.setProperty("javax.net.ssl.keyStore", "E:\\zxwork\\bank\\keys\\client1.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		System.setProperty("javax.net.ssl.trustStore", "E:\\zxwork\\bank\\keys\\tomcat.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");*/
		
		//公网测试环境  长沙银行
		/*System.setProperty("javax.net.ssl.keyStore", "E:\\zxwork\\bank\\keys1\\client.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		System.setProperty("javax.net.ssl.trustStore", "E:\\zxwork\\bank\\keys1\\tomcat.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");*/
		
		//公网测试环境  华融湘江
		System.setProperty("javax.net.ssl.keyStore", "E:\\zxwork\\bank\\keys1\\client_hrxj.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "654321");
		System.setProperty("javax.net.ssl.trustStore", "E:\\zxwork\\bank\\keys1\\tomcat.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		
		String test = getRes(xmlString, "busiCall");
		getData(test);
	}
	
	private static String getContractPayInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETCONTRACTPAYINFO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETCONTRACTPAYINFO_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2020-01-01]]></F>");
		sb.append("<F n=\"BANK_CODE\"><![CDATA[020021]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String getPrcmContractNo(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACTNO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETPRCMCONTRACTNO_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911045143]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String setRzRepayInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_SETRZREPAYINFO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_SETRZREPAYINFO_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911045143]]></F>");
		sb.append("<F n=\"RZ_AMT\"><![CDATA[1]]></F>");
		sb.append("<F n=\"RZ_R_AMT\"><![CDATA[0]]></F>");
		sb.append("<F n=\"LAST_PAY_DATE\"><![CDATA[2018-01-20 18:00:00]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String queryAccountModifyStatus(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_QUERYACCOUNTMODIFYSTATUS]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_QUERYACCOUNTMODIFYSTATUS_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[湖南佳富华智慧电气科技有限公司]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[92430702MA4M27063C]]></F>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911045143]]></F>");
		sb.append("<F n=\"BANK_CODE\"><![CDATA[020021]]></F>");
		sb.append("<F n=\"PAYEE_BANK\"><![CDATA[中国工商银行股份有限公司常德武陵北路支行]]></F>");
		sb.append("<F n=\"PAYEE\"><![CDATA[中国工商银行股份有限公司常德武陵北路支行]]></F>");
		/*sb.append("<F n=\"PAYEE_ACCOUNT\"><![CDATA[190807500900014629]]></F>");*/
		sb.append("<F n=\"PAYEE_ACCOUNT\"><![CDATA[190807500900014629]]></F>");
		sb.append("<F n=\"RZ_DATE\"><![CDATA[2020-01-01 11:00:00]]></F>");
		sb.append("<F n=\"RZ_NED_DATE\"><![CDATA[2022-10-24 12:00:00]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String getPrcmResultAll(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMRESULTALL]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETPRCMRESULTALL_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2020-01-01]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"rows\"><![CDATA[10]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	
	private static String getContractResultAll(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACTALL]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETPRCMCONTRACTALL_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2020-01-01]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"rows\"><![CDATA[10]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String getPrcmSupplierResultAll(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMRESULT]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETPRCMRESULT_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[武陵区国兴信息工程经营部]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[92430702MA4M27063C]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	
	private static String getContractSupplierResultAll(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[123456]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[hryh01]]></F>");
		sb.append("<F n=\"CHANNEL_CODE\"><![CDATA[FBM]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACT]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[I_CIM_GETPRCMCONTRACT_").append(new Date().getTime()).append("]]></F>");
		sb.append("<F n=\"ACTIVITY_CODE\"><![CDATA[0]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[武陵区国兴信息工程经营部]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[92430702MA4M27063C]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");
		
		return sb.toString();
		
	}
	

	/**
	 * 
	 * @param xmlString
	 */
	@SuppressWarnings("unchecked")
	public static void getData(String xmlString) {
		if (xmlString == null) {
			System.out.println(xmlString);
		} else {
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlString);
				Element rootElt = doc.getRootElement();
				Element eleH = rootElt.element("H");
				Element eleB = rootElt.element("B");
				if (eleH != null) {
					Iterator<Element> headIt = eleH.elementIterator();
					System.out.println("----------------------Head-------------------------");
					while (headIt.hasNext()) {
						Element element = (Element) headIt.next();
						System.out.println(element.attributeValue("n") + "=" + element.getText());
					}
				}
				if (eleB != null) {
					Element eleS = eleB.element("S");
					if (eleS != null) {
						Iterator<Element> bodyIt = eleS.elementIterator();
						System.out.println("----------------------Single Data-------------------------");
						while (bodyIt.hasNext()) {
							Element element = (Element) bodyIt.next();
							System.out.println(element.attributeValue("n") + "=" + element.getText());
						}
					}
					Element eleM = eleB.element("M");
					if (eleM != null) {
						int i = 0;
						Iterator<Element> bodyIt = eleM.elementIterator();
						System.out.println("----------------------Multi Data-------------------------");
						while (bodyIt.hasNext()) {
							System.out.println("------------------------------------------------(" + (++i) + ")");
							Element element = (Element) bodyIt.next();
							Iterator<Element> elements = element.elementIterator();
							while (elements.hasNext()) {
								Element e = (Element) elements.next();
								System.out.println(e.attributeValue("n") + "=" + e.getText());
							}

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
