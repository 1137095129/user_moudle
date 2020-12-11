package com.thhy.project.usermodule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "overall-situation-encrypt")
public class OverallSituationEncryptConfig {
	//是否需要加密
	private boolean shouldEncrypt;

	//默认RSA加密的位数
	private int intSize;

	//密钥存储在redis中的前缀
	private String redisKeyPrefix;

	public boolean isShouldEncrypt() {
		return shouldEncrypt;
	}

	public void setShouldEncrypt(boolean shouldEncrypt) {
		this.shouldEncrypt = shouldEncrypt;
	}

	public int getIntSize() {
		return intSize;
	}

	public void setIntSize(int intSize) {
		this.intSize = intSize;
	}

	public String getRedisKeyPrefix() {
		return redisKeyPrefix;
	}

	public void setRedisKeyPrefix(String redisKeyPrefix) {
		this.redisKeyPrefix = redisKeyPrefix;
	}
}
