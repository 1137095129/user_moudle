package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Roles implements Serializable {
	private static final long serialVersionUID = 84732189088198430L;
	private Integer roleId;
	private String roleName;
}
