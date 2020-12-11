package com.thhy.project.usermodule.vo.param.sysmenu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FindListByRoleParam implements Serializable {
	private static final long serialVersionUID = 528149433148682669L;
	@NotNull
	private Integer roleId;
}
