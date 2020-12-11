package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DefaultUserInfo implements Serializable {
	private static final long serialVersionUID = 239859184047767915L;
	private Integer userId;
	private String username;
	private String email;
	private String password;
	private String telphone;
}
