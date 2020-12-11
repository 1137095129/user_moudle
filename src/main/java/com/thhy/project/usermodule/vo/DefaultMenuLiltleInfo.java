package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DefaultMenuLiltleInfo implements Serializable {
	private static final long serialVersionUID = 5029820508089658033L;
	private Integer menuId;
	private String menuName;
}
