package test2;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
 //webservice测试
public class Test2 {
	public static String getRes(String xmlString, String callFunctionName) {
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			///services/*
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(
					"http://localhost:8080/star/services/GetAddressWebServiceImpl?wsdl");
			options.setTo(targetEPR);

			// 指定IncaInsiderService方法的参数值
			Object[] parmas = new Object[] { xmlString.toString() ,xmlString.toString() };
			// 指定IncaInsiderService方法返回值的数据类型的Class对象
			Class<?>[] classes = new Class[] { String.class };
			// 指定要调用的IncaInsiderService方法及WSDL文件的命名空间
			QName opAddEntry = new QName("http://webservice.modules.qn.org/", callFunctionName);
			// 调用IncaInsiderService方法并输出该方法的返回值
			Object[] str = client.invokeBlocking(opAddEntry, parmas, classes);
			return str[0].toString();
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// xmlString的格式
		Map<String, String> param = new HashMap<String, String>();
		param.put("BiaoDuanUniqueGuid", "24070");
		param.put("ProjectUniqueGuid", "28581");
		param.put("BiaoDuanName", "修缮工程");
		param.put("YuDingGuid", "123456");
		param.put("ZiShenDate", "2020-08-20 9:30:00");
		param.put("ZiShenRoomName", "公共资源交易中心");
		param.put("KaiBiaoDate", "");
		param.put("KaiBiaoRoomName", "公共资源交易中心开标一室");
		param.put("PingBiaoDate", "2020-08-20 9:30:00");
		param.put("PingBiaoRoomName", "公共资源交易中心");
		param.put("YuYueType", "2");
		param.put("PeojectName", "竞争性谈判02");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<" + "root" + ">");
		sb.append("<" + "body" + ">");
		sb.append("<" + "changdiinfo" + ">");
		for (String key : param.keySet()) {
			sb.append("<" + key + ">" + param.get(key) + "</" + key + ">");
		}
		sb.append("</" + "changdiinfo" + ">");
		sb.append("</" + "body" + ">");
		sb.append("</" + "root" + ">");
		String xmlString = sb.toString();
		String result = Test2.getRes(xmlString, "jcjyProjectInfoInvokeResponse");
		System.out.println(result);
		Document doc = null;
		// 读取并解析XML文档
		try {
			doc = DocumentHelper.parseText(result);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
//		// 将字符串转为XML
//		Element rootElt = doc.getRootElement(); // 获取根节点
//		String name = rootElt.elementTextTrim("name");
//		String Sex = rootElt.elementTextTrim("Sex");
//		String Mobile = rootElt.elementTextTrim("Mobile");
//		String age = rootElt.elementTextTrim("age");
//		String tel = rootElt.elementTextTrim("tel");
//		String addr = rootElt.elementTextTrim("addr");
//
//		System.out.println("name=" + name);
//		System.out.println("Sex=" + Sex);
//		System.out.println("Mobile=" + Mobile);
//		System.out.println("age=" + age);
//		System.out.println("tel=" + tel);
//		System.out.println("addr=" + addr);

//		String test = Test2.getRes("hi,test.", "test");
//		System.out.println(test);
	}
}
