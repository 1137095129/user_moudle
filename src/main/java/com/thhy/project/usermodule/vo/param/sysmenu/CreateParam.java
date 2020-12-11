package com.thhy.project.usermodule.vo.param.sysmenu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateParam implements Serializable {
	private static final long serialVersionUID = 2370765279765021229L;
	private Integer createUser;
	private Integer updateUser;
	private String path;
	private String name;
	private Integer hidden;
	private String redirect;
	@NotNull
	private Integer parentId;
	private String title;
	private String icon;
	private String component;
	private Integer level;
	private Integer isDefault;
}
