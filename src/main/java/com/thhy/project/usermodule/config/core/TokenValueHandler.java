package com.thhy.project.usermodule.config.core;

import com.thhy.project.usermodule.config.DefaultUser;

/**
 * 用户token的value值处理
 */
public interface TokenValueHandler {
	String handleValue(DefaultUser user, String tokenKey);
}
