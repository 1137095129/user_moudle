package com.thhy.project.usermodule.service;

import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sys_role_menu.CreateNewSysRoleMenuParam;

public interface SysRoleMenuService {
	ResponseMap createNewSysRoleMenu(CreateNewSysRoleMenuParam param);
}
