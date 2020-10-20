package test2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionTest {

	public static void main(String[] args) {
		try{
		test();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void test() {
		String s = "2018-05-a";
		int i = 5/0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			try {
				df.parse(s);
			} catch (ParseException e1) {
				System.out.println("2222");
			}
		}catch(Exception e) {
			System.out.println("11111111111");
		}
	}

}
