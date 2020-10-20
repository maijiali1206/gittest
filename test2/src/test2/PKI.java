package test2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author Administrator
 *
 */
public class PKI {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("username", "mjl");
		String en = encode(obj.toString());
		JSONObject enObj = JSONObject.parseObject(en);
		if (verify(enObj.getBytes("encodedText"), enObj.getBytes("signature"))) {
			String result = decode(enObj.getBytes("encodedText"), enObj.getBytes("signature"));
			System.out.println(result);
		}
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String encode(String data) throws IOException {
		KeyStore keyStore = getKeyStore("654321", "D:\\client.p12");
		PrivateKey privateKey = getPrivateKey(keyStore, "clientkey", "654321");
		X509Certificate certificate = getCertificateByKeystore(keyStore, "clientkey");
		byte[] encodedText = encode(data.getBytes(), privateKey);
		byte[] signature = sign(certificate, privateKey, data.getBytes());
		JSONObject obj = new JSONObject();
		obj.put("encodedText", encodedText);
		obj.put("signature", signature);
		return obj.toString();
	}

	/**
	 * 
	 * @param encodedText
	 * @param signature
	 * @return
	 * @throws IOException
	 */
	public static String decode(byte[] encodedText, byte[] signature) throws IOException {
		X509Certificate receivedCertificate = getCertificateByCertPath("D:\\client.cer", "X.509");
		PublicKey publicKey = getPublicKey(receivedCertificate);
		byte[] decodedText = decode(encodedText, publicKey);
		return new String(decodedText);
	}

	/**
	 * 
	 * @param encodedText
	 * @param signature
	 * @return
	 * @throws IOException
	 */
	public static boolean verify(byte[] encodedText, byte[] signature) throws IOException {
		X509Certificate receivedCertificate = getCertificateByCertPath("D:\\client.cer", "X.509");
		PublicKey publicKey = getPublicKey(receivedCertificate);
		byte[] decodedText = decode(encodedText, publicKey);
		return verify(receivedCertificate, decodedText, signature);
	}

	/**
	 * 
	 * @throws IOException
	 */
	public static KeyStore getKeyStore(String storepass, String keystorePath) throws IOException {
		InputStream inputStream = null;
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			inputStream = new FileInputStream(keystorePath);
			keyStore.load(inputStream, storepass.toCharArray());
			return keyStore;
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param keyStore
	 * @param alias
	 * @param password
	 * @return
	 */
	public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String password) {
		try {
			return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param certificate
	 * @return
	 */
	public static PublicKey getPublicKey(Certificate certificate) {
		return certificate.getPublicKey();
	}

	/**
	 * 
	 * @param keyStore
	 * @param alias
	 * @return
	 */
	public static X509Certificate getCertificateByKeystore(KeyStore keyStore, String alias) {
		try {
			return (X509Certificate) keyStore.getCertificate(alias);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param path
	 * @param certType
	 * @return
	 * @throws IOException
	 */
	public static X509Certificate getCertificateByCertPath(String path, String certType) throws IOException {
		InputStream inputStream = null;
		try {
			CertificateFactory factory = CertificateFactory.getInstance(certType);
			inputStream = new FileInputStream(path);
			Certificate certificate = factory.generateCertificate(inputStream);

			return (X509Certificate) certificate;
		} catch (CertificateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return null;

	}

	/**
	 * 
	 * @param certificate
	 * @param privateKey
	 * @param plainText
	 * @return
	 */
	public static byte[] sign(X509Certificate certificate, PrivateKey privateKey, byte[] plainText) {
		try {
			Signature signature = Signature.getInstance(certificate.getSigAlgName());
			signature.initSign(privateKey);
			signature.update(plainText);
			return signature.sign();
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param certificate
	 * @param decodedText
	 * @param receivedignature
	 * @return
	 */
	public static boolean verify(X509Certificate certificate, byte[] decodedText, final byte[] receivedignature) {
		try {
			Signature signature = Signature.getInstance(certificate.getSigAlgName());
			signature.initVerify(certificate);
			signature.update(decodedText);
			return signature.verify(receivedignature);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param plainText
	 * @param privateKey
	 * @return
	 */
	public static byte[] encode(byte[] plainText, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return cipher.doFinal(plainText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param encodedText
	 * @param publicKey
	 * @return
	 */
	public static byte[] decode(byte[] encodedText, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(encodedText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
