package com.thhy.project.usermodule.util.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密API
 */
public interface EncryptionApi {

	/**
	 * 加密
	 *
	 * @param s   要加密内容
	 * @param key 加密密钥
	 * @return
	 */
	byte[] encrypt(String s, String key,int keySize) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, Exception;

	/**
	 * 解密
	 *
	 * @param s   要解密的内容
	 * @param key 解密密钥
	 * @return
	 */
	byte[] decrypt(String s, String key,int keySize) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, Exception;

}
