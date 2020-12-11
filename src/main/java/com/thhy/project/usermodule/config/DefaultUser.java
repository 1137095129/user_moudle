package com.thhy.project.usermodule.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class DefaultUser extends User {
	private static final long serialVersionUID = 8982436565950906476L;

	private Integer userId;

	private String encryptKey;

	public DefaultUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer userId) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public DefaultUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer userId, String encryptKey) {
		super(username, password, authorities);
		this.userId = userId;
		this.encryptKey = encryptKey;
	}

	public DefaultUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Integer userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
}
