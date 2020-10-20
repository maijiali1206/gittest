package test2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
public class Test3 {
 
public static void main(String[] args) {
		
		
		try {
//			System.out.println("111111");
			// 本机tomcat端口默认为8081,参数是wsdl网址的一部分
			EndpointReference targetEPR = new EndpointReference(
					"http://localhost:8090/Axis2WebSercie/services/TestWebService");
			RPCServiceClient sender = new RPCServiceClient();
			Options options = sender.getOptions();
			options.setTimeOutInMilliSeconds(2 * 20000L);// 超时时间20s
			options.setTo(targetEPR);
			/**
			 * 参数: 1：在网页上执行 wsdl后xs:schema标签的targetNamespace路径
			 * <xs:schema targetNamespace="http://test.axiswevservice.com">
			 * 2：<xs:element name="test"> ======这个标签中name的值
			 * 
			 */
			QName qname = new QName("http://services.axis2.com", "sayHelloWho");
			String str = "客户端调用成功"; // 方法的入参
			Object[] param = new Object[] { str };
			Class<?>[] types = new Class[] { String.class }; // 这是针对返值类型的
			/**
			 * RPCServiceClient类的invokeBlocking方法调用了WebService中的方法。
			 * invokeBlocking方法有三个参数 第一个参数的类型是QName对象，表示要调用的方法名；
			 * 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
			 * 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。
			 * 
			 * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
			 */
			Object[] response1 = sender.invokeBlocking(qname, param, types);
			System.out.println(response1[0]);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
