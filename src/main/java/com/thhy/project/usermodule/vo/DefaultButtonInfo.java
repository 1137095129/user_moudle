package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DefaultButtonInfo implements Serializable {
	private static final long serialVersionUID = 2161400549964925740L;
	private Integer buttonId;
	private String buttonName;
	private String buttonType;
}
