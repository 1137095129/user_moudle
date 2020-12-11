package com.thhy.project.usermodule.config.core;

import com.thhy.project.usermodule.config.DefaultUser;

/**
 * 用户token的key值处理
 */
public interface TokenKeyHandler {
	String handleKey(DefaultUser user);
}
