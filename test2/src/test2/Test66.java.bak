package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Test66 {

	public static void main(String[] args) throws Exception {
//		System.out.println(isInRange("111.8.120.245", "111.8.120.245/26"));
		readFile();
	}
	
	 public static boolean isInRange(String ip, String cidr) {
	        String[] ips = ip.split("\\.");
	        int ipAddr = (Integer.parseInt(ips[0]) << 24)
	                | (Integer.parseInt(ips[1]) << 16)
	                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
	        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
	        int mask = 0xFFFFFFFF << (32 - type);
	        String cidrIp = cidr.replaceAll("/.*", "");
	        String[] cidrIps = cidrIp.split("\\.");
	        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
	                | (Integer.parseInt(cidrIps[1]) << 16)
	                | (Integer.parseInt(cidrIps[2]) << 8)
	                | Integer.parseInt(cidrIps[3]);
	 
	        return (ipAddr & mask) == (cidrIpAddr & mask);
	    }
	 
	 
	 public static void readFile() throws Exception {
		 File f = new File("d:/total_info.txt");
		 FileInputStream fis = new FileInputStream(f);
		 BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 String line = null;
		 while((line=br.readLine())!=null) {
			 System.out.println(line);
		 }
		 br.close();
		 fis.close();
	 }


}
