package com.thhy.project.usermodule.vo.param.sysrole;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateNewSysRoleParam implements Serializable {
	private static final long serialVersionUID = -2054397605526012681L;
	private Integer roleId;
	private String roleName;
	private Integer createBy;
	private Integer updateBy;
	private String remarks;
}
