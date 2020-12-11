package com.thhy.project.usermodule.vo.param.sysuser;

import com.thhy.project.usermodule.vo.AbstractUserUnicodeInfo;

import java.io.Serializable;

public class NewUserInfo extends AbstractUserUnicodeInfo implements Serializable {
	private static final long serialVersionUID = 6017586107110252911L;
	private String password;
	private String realName;
	private String photoUrl;
	private Integer sex;
	private String role;
	private Integer createUser;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
}
