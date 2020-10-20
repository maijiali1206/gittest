package test2;

import java.util.Iterator;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
 
public class TestLoan {
	// 获取当天所有成交结果信息
	public static void cx1() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMRESULTALL]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2019-10-9]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 获取当天所有采购合同信息
	public static void cx2() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACTALL]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2019-10-09]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 根据供应商名称和统一社会信息代码获取其成交信息
	public static void cx3() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMRESULT]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[朝晖物业服务有限责任公司]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430726554916459J]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 根据供应商名称和统一社会信息代码获取其合同信息
	public static void cx4() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACT]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[朝晖物业服务有限责任公司]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430726554916459J]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 查询修改账号是否成功
	public static void cxModify() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_QUERYACCOUNTMODIFYSTATUS]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[鼎城区蓝光科技经营部]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430726554916459J]]></F>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201910084557]]></F>");
//		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[朝晖物业服务有限责任公司]]></F>");
//		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430726554916459J]]></F>");
//		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911306003]]></F>");
		sb.append("<F n=\"BANK_CODE\"><![CDATA[csyh]]></F>");
		sb.append("<F n=\"PAYEE_BANK\"><![CDATA[长沙银行]]></F>");
		sb.append("<F n=\"PAYEE\"><![CDATA[长沙银行]]></F>");
		sb.append("<F n=\"PAYEE_ACCOUNT\"><![CDATA[755940911610601]]></F>");
		sb.append("<F n=\"RZ_DATE\"><![CDATA[2020-07-30 08:11:22]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 根据合同编号查询合同信息
	public static void cx5() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETPRCMCONTRACTNO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911306003]]></F>");
//			sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911306003]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	// 根据银行代码和日期获取融资合同支付情况
	public static void cx6() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_GETCONTRACTPAYINFO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"BANK_CODE\"><![CDATA[csyh]]></F>");
		sb.append("<F n=\"SEARCH_DATE\"><![CDATA[2020-05-27]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	public static void rz() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_SETPRCMRZINFO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[北京博宬文化遗产保护中心有限公司]]></F>");
		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430726554916459J]]></F>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201910084561]]></F>");
//		sb.append("<F n=\"SUPPER_NAME\"><![CDATA[常德市宏泰汽车服务有限公司]]></F>");
//		sb.append("<F n=\"SUPPER_CERT_CODE\"><![CDATA[91430702MA4PGC4Y0B]]></F>");
//		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201911275850]]></F>");
		sb.append("<F n=\"RZ_AMT\"><![CDATA[600000]]></F>");
		sb.append("<F n=\"BANK_CODE\"><![CDATA[csyh]]></F>");
		sb.append("<F n=\"BASE_RATES\"><![CDATA[0.38]]></F>");
		sb.append("<F n=\"LOAN_RATES\"><![CDATA[0.42]]></F>");
		sb.append("<F n=\"PAYEE_BANK\"><![CDATA[长沙银行]]></F>");
		sb.append("<F n=\"PAYEE\"><![CDATA[长沙银行]]></F>");
		sb.append("<F n=\"PAYEE_ACCOUNT\"><![CDATA[755940911610601]]></F>");
		sb.append("<F n=\"RZ_DATE\"><![CDATA[2020-07-30 08:11:22]]></F>");
		sb.append("<F n=\"RZ_NED_DATE\"><![CDATA[2021-07-25 11:11:22]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	public static void pay() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<R>");
		sb.append("<H>");
		sb.append("<F n=\"CHANNEL_PWD\"><![CDATA[654321]]></F>");
		sb.append("<F n=\"CHANNEL_ACC\"><![CDATA[csyh01]]></F>");
		sb.append("<F n=\"BUSI_CODE\"><![CDATA[I_CIM_SETRZREPAYINFO]]></F>");
		sb.append("<F n=\"VOUCH_FLOW_NO\"><![CDATA[20200413000000000078]]></F>");
		sb.append("</H>");
		sb.append("<B><S>");
		sb.append("<F n=\"rows\"><![CDATA[50]]></F>");
		sb.append("<F n=\"page\"><![CDATA[1]]></F>");
		sb.append("<F n=\"CONTRACT_NO\"><![CDATA[CDGP-201910084557]]></F>");
		sb.append("<F n=\"RZ_AMT\"><![CDATA[600000]]></F>");
		sb.append("<F n=\"RZ_R_AMT\"><![CDATA[0]]></F>");
		sb.append("<F n=\"LAST_PAY_DATE\"><![CDATA[2021-09-10 12:18:22]]></F>");
		sb.append("</S></B>");
		sb.append("</R>");

		// 创建动态客户端
		long startTime = System.currentTimeMillis(); // 获取开始时间
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1:8080/ContractLoanCXF/services/ECB?wsdl");
		Object[] objects = new Object[0];
		try {
			objects = client.invoke("busiCall", sb.toString());
			System.out.println("返回数据:" + objects[0]);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
			getData(objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
//		cx1();// 获取当天所有成交结果信息
		
//		cx2();// 获取当天所有采购合同信息
//		
//		cx3();// 根据供应商名称和统一社会信息代码获取其成交信息
//		
//		cx4();// 根据供应商名称和统一社会信息代码获取其合同信息
//		
//		cx5();// 根据合同编号查询合同信息
//		
//		cx6();// 根据银行代码和日期获取融资合同支付情况
//		
//		cxModify();// 查询修改账号是否成功
//		
	rz();// 融资
//		
//		pay();// 还款
		
		
	}

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
