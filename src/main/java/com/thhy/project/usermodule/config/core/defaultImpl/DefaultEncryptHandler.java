package com.thhy.project.usermodule.config.core.defaultImpl;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.config.OverallSituationEncryptConfig;
import com.thhy.project.usermodule.config.UserLogConfig;
import com.thhy.project.usermodule.config.core.EncryptHandler;
import com.thhy.project.usermodule.util.encryption.BaseEncryptionUtil;
import com.thhy.project.usermodule.util.encryption.RSAEncrypt;
import com.thhy.project.usermodule.util.prefix.RedisUserEncryptPrefix;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 登录时的密钥默认处理
 */
public class DefaultEncryptHandler implements EncryptHandler {
	private RedisTemplate<String,Object> redisTemplate;
	private UserLogConfig userLogConfig;
	private OverallSituationEncryptConfig encryptConfig;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setUserLogConfig(UserLogConfig userLogConfig) {
		this.userLogConfig = userLogConfig;
	}

	public void setEncryptConfig(OverallSituationEncryptConfig encryptConfig) {
		this.encryptConfig = encryptConfig;
	}

	private final RSAEncrypt encrypt = new RSAEncrypt();

	@Override
	public String handle(DefaultUser user) throws NoSuchAlgorithmException {
		//获取密钥对
		KeyPair keyPair = encrypt.getKeyPair(1024);
		//获取私钥
		PrivateKey privateKey = keyPair.getPrivate();
		//获取公钥
		PublicKey publicKey = keyPair.getPublic();
		String privateKeyBaseStr = BaseEncryptionUtil.base64EncodeToString(privateKey.getEncoded());
		String publicKeyBaseStr = BaseEncryptionUtil.base64EncodeToString(publicKey.getEncoded());
		String key = RedisUserEncryptPrefix.getKey(encryptConfig.getRedisKeyPrefix(), user);
		//存储密钥
		redisTemplate.opsForValue().set(key,privateKeyBaseStr,userLogConfig.getTokenRetentionTime(),userLogConfig.getTimeUnit());
		return publicKeyBaseStr;
	}
}
