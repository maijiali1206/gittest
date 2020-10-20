package test2;

import org.qn.commons.security.Digests;
import org.qn.commons.utils.Encodes;

public class HexTest {
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	public static void main(String[] args) {
		entryptPassword("1");
//		decodePassword("1");

	}

	public static void entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		System.out.println(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
		}
		


		/**
		 * 解密
		 */

		public static void decodePassword(String plainPassword) {
		 byte[] salt =Encodes.decodeHex("440695017fe5dc6a9f48e9b87327a741e4e6c3047747f6435ec8cdc6");
		 byte[] hashPassword = Digests.sha1("440695017fe5dc6a9f48e9b87327a741e4e6c3047747f6435ec8cdc6".getBytes(), salt, HASH_INTERATIONS);
		 String ss = Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
		 System.out.println(ss);
		 
//		 String password ="440695017fe5dc6a9f48e9b87327a741e4e6c3047747f6435ec8cdc6";
//		 String plain = Encodes.unescapeHtml(plainPassword);
//		byte[] salt = Encodes.decodeHex(password.substring(0,16));
//		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, 1024);
//		System.out.println(password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword)));
		
		
		
		}
}
