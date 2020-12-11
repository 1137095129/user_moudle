package com.thhy.project.usermodule.util.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public abstract class AbstractEncrypt implements EncryptionApi {

	/**
	 * 获取密钥对
	 * @param algorithm 加密算法
	 * @param keySize
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair getKeyPair(String algorithm,int keySize) throws NoSuchAlgorithmException {
		//构建一个安全的随机数源
		SecureRandom random = new SecureRandom();
		//根据传入的算法名，开始构建KeyPairGenerator对象
		KeyPairGenerator instance = KeyPairGenerator.getInstance(algorithm);
		instance.initialize(keySize,random);
		return instance.generateKeyPair();
	}

	/**
	 * 生成密钥签名
	 * @return
	 */
	public abstract String sign(byte[] data,String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;

	/**
	 * 验证密钥是否正确
	 * @param data
	 * @param publicKey
	 * @param sign
	 * @return
	 */
	public abstract boolean verify(byte[] data,String publicKey,String sign) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;

	@Override
	public byte[] encrypt(String s, String key,int keySize) throws Exception {
		return new byte[0];
	}

	@Override
	public byte[] decrypt(String s, String key,int keySize) throws Exception {
		return new byte[0];
	}
}
