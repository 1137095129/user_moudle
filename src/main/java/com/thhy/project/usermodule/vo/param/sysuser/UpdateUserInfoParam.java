package com.thhy.project.usermodule.vo.param.sysuser;

import com.thhy.project.usermodule.vo.AbstractUserUnicodeInfo;

import java.io.Serializable;

public class UpdateUserInfoParam extends AbstractUserUnicodeInfo implements Serializable {
	private static final long serialVersionUID = 1356179565895964228L;
	private Integer sex;
	private String realName;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
