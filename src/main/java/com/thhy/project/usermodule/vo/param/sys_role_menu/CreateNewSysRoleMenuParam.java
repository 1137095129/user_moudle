package com.thhy.project.usermodule.vo.param.sys_role_menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateNewSysRoleMenuParam implements Serializable {
	private static final long serialVersionUID = 8972415568751242738L;
	private Integer roleId;
	private List<Integer> menuIds;
}
