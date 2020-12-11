package com.thhy.project.usermodule.service;

import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sys_role_user.SysRoleUserCreateParam;

public interface SysRoleUserService {
	ResponseMap createNewRoleUser(SysRoleUserCreateParam param);
}
