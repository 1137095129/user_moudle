package com.thhy.project.usermodule.util.prefix;

import cn.hutool.crypto.digest.MD5;
import com.thhy.project.usermodule.config.DefaultUser;

public class RedisUserEncryptPrefix implements RedisPrefix {

	private final static RedisUserEncryptPrefix PREFIX = new RedisUserEncryptPrefix();

	public static String getKey(String prefix, DefaultUser user) {
		return MD5.create().digestHex16(PREFIX.getKey(prefix) + "_" + user.getUserId());
	}

	@Override
	public String getKey(String prefix) {
		return getPrefix() + prefix;
	}

	@Override
	public String getPrefix() {
		return "REDIS_ENCRYPT_TOKEN" + "_";
	}
}
