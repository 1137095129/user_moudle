package com.thhy.project.usermodule.util.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAEncrypt extends AbstractEncrypt {

	public KeyPair getKeyPair(int keySize) throws NoSuchAlgorithmException {
		return super.getKeyPair("RSA", keySize);
	}

	/**
	 * 获取公钥的模数
	 *
	 * @param publicKey
	 * @return
	 */
	public BigInteger getModuleByRSAPublicKey(RSAPublicKey publicKey) {
		return publicKey.getModulus();
	}

	/**
	 * 获取公钥的指数
	 *
	 * @param publicKey
	 * @return
	 */
	public BigInteger getExponentByRSAPublicKey(RSAPublicKey publicKey) {
		return publicKey.getPublicExponent();
	}

	@Override
	public String sign(byte[] data, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		PrivateKey rsaPrivateKeyByEncodeKey = getRSAPrivateKeyByEncodeKey(privateKey);
		Signature signature = Signature.getInstance("RSA");
		signature.initSign(rsaPrivateKeyByEncodeKey);
		signature.update(data);
		return BaseEncryptionUtil.base64EncodeToString(signature.sign());
	}

	@Override
	public boolean verify(byte[] data, String publicKey, String sign) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		PublicKey rsaPublicKeyByEncodeKey = getRSAPublicKeyByEncodeKey(publicKey);
		Signature signature = Signature.getInstance("RSA");
		signature.initVerify(rsaPublicKeyByEncodeKey);
		signature.update(data);
		return signature.verify(BaseEncryptionUtil.base64Decode(sign));
	}

	@Override
	public byte[] encrypt(String s, String key, int keySize) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
		return encrypt(s, getRSAPrivateKeyByEncodeKey(key), keySize, false);
	}

	/**
	 * RSA私钥解密算法
	 *
	 * @param source  需要解密的密文，未经过编码
	 * @param key     解密私钥
	 * @param keySize 私钥
	 * @param fromBcd 该密文是否经过BCD转码
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public byte[] encrypt(String source, Key key, int keySize, boolean fromBcd) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		int key_len = keySize / 8;
		byte[] bytes = source.getBytes();
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
		StringBuilder ming = new StringBuilder();
		byte[][] arrays = fromBcd ? splitArray(bcd, key_len) : splitArray(bytes, key_len);
		for (byte[] arr : arrays) {
			ming.append(new String(cipher.doFinal(arr)));
		}
		return ming.toString().getBytes();
	}

	@Override
	public byte[] decrypt(String s, String key, int keySize) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
		return decrypt(s, getRSAPrivateKeyByEncodeKey(key), keySize, false);
	}

	/**
	 * RSA私钥加密算法
	 *
	 * @param source  需要加密的明文
	 * @param key     加密私钥
	 * @param keySize 加密位数
	 * @param toBcd   是否转码成bcd码
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public byte[] decrypt(String source, Key key, int keySize, boolean toBcd) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		int MaxBlockSize = keySize / 8;
		String[] datas = splitString(source, MaxBlockSize - 11);
		StringBuilder mi = new StringBuilder();
		for (String s : datas) {
			if (toBcd) {
				mi.append(bcd2Str(cipher.doFinal(s.getBytes())));
			} else {
				mi.append(new String(cipher.doFinal(s.getBytes()), StandardCharsets.UTF_8));
			}
		}
		return mi.toString().getBytes();
	}

	/**
	 * 通过被Base64编码方式编码过后的私钥字符串获取私钥
	 *
	 * @param encodeKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private PrivateKey getRSAPrivateKeyByEncodeKey(String encodeKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] bytes = BaseEncryptionUtil.base64Decode(encodeKey);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
	}

	/**
	 * 通过被Base64编码方式编码过后的公钥字符串获取公钥
	 *
	 * @param encodeKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private PublicKey getRSAPublicKeyByEncodeKey(String encodeKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] bytes = BaseEncryptionUtil.base64Decode(encodeKey);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(pkcs8EncodedKeySpec);
	}

	/**
	 * 切割字符串
	 *
	 * @param string
	 * @param len
	 * @return
	 */
	private String[] splitString(String string, int len) {
		int x = string.length() / len;
		int y = string.length() % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		String[] strings = new String[x + z];
		String str = "";
		for (int i = 0; i < x + z; i++) {
			if (i == x + z - 1 && y != 0) {
				str = string.substring(i * len, i * len + y);
			} else {
				str = string.substring(i * len, i * len + len);
			}
			strings[i] = str;
		}
		return strings;
	}

	private String bcd2Str(byte[] bytes) {
		char[] temp = new char[bytes.length * 2];
		char val;


		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');


			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	private byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	private byte asc_to_bcd(byte asc) {
		byte bcd;


		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	private byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}
}
