package com.thhy.project.usermodule.controller;

import com.thhy.project.usermodule.service.SysRoleUserService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sys_role_user.SysRoleUserCreateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysUserRole")
public class DefaultSysRoleUserController {

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@PostMapping("/createNewSysUserRole")
	public ResponseMap createNewSysUserRole(@RequestBody @Validated SysRoleUserCreateParam param){
		return sysRoleUserService.createNewRoleUser(param);
	}

}
