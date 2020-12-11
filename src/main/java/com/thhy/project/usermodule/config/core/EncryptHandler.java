package com.thhy.project.usermodule.config.core;

import com.thhy.project.usermodule.config.DefaultUser;
import org.springframework.security.core.userdetails.User;

import java.security.NoSuchAlgorithmException;

/**
 * 密钥对处理
 */
public interface EncryptHandler {
	/**
	 * 根据用户信息生成密钥对，并返回公钥信息
	 * @param user
	 * @return
	 */
	String handle(DefaultUser user) throws NoSuchAlgorithmException;
}
