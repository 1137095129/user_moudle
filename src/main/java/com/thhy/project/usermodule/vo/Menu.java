package com.thhy.project.usermodule.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
	private static final long serialVersionUID = -3654561385657567240L;
	private Integer menuId;
	private String name;
	private String title;
	private String path;
}
