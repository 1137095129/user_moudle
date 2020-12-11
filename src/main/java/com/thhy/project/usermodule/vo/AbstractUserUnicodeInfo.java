package com.thhy.project.usermodule.vo;

import java.io.Serializable;

public abstract class AbstractUserUnicodeInfo implements Serializable {
	private static final long serialVersionUID = -7916860526522752859L;
	private Integer userId;
	private String username;
	private String email;
	private String telphone;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
}
