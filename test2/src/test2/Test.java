package test2;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Test {
	public static Object getRes(String xmlString, String callFunctionName) {
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			///services/*
			// ָ������WebService��URL
			EndpointReference targetEPR = new EndpointReference(
					"http://localhost:8081/springmvc/services/SimpleService?wsdl");
			options.setTo(targetEPR);

			// ָ��IncaInsiderService�����Ĳ���ֵ
			Object[] parmas = new Object[] { xmlString.toString() };
			// ָ��IncaInsiderService��������ֵ���������͵�Class����
			Class<?>[] classes = new Class[] { String.class };
			// ָ��Ҫ���õ�IncaInsiderService������WSDL�ļ��������ռ�
			QName opAddEntry = new QName("http://webservice.com.springmvc", callFunctionName);
			// ����IncaInsiderService����������÷����ķ���ֵ
			Object[] str = client.invokeBlocking(opAddEntry, parmas, classes);
			return str[0];
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
//          Object test= Test.getRes("aaa", "test");
//          System.out.println("(client print) :"+test);
		
		Object obj = null;
		String str = (String)obj;
		System.out.println(str);
	}
}
