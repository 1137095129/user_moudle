package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DefaultIconInfo implements Serializable {
	private static final long serialVersionUID = -7322070879404619077L;
	private Integer iconId;
	private String iconName;
	private String iconPath;
}
