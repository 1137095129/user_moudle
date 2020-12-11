package com.thhy.project.usermodule.service;

import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sysrole.CreateNewSysRoleParam;
import com.thhy.project.usermodule.vo.param.sysrole.RolesListParam;
import com.thhy.project.usermodule.vo.param.sysrole.UpdateRoleParam;

public interface SysRolesService {
	ResponseMap findAllRolesInfo();

	ResponseMap findSysRolesListByParam(RolesListParam param);

	ResponseMap createNewRole(CreateNewSysRoleParam param);

	ResponseMap deleteRoleInfo(Integer roleId);

	ResponseMap updateRoleInfo(UpdateRoleParam param);
}
