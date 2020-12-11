package com.thhy.project.usermodule.vo.param.sysmenu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateParam implements Serializable {
	private static final long serialVersionUID = 7172955985084273231L;
	@NotNull
	private Integer menuId;
	private String component;
	private Integer hidden;
	private String icon;
	private Integer level;
	private Integer parentId;
	private String name;
	private String path;
	private String redirect;
	private String title;
	private Integer updateUser;
}
