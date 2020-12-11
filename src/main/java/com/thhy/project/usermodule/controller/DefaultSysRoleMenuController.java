package com.thhy.project.usermodule.controller;

import com.thhy.project.usermodule.service.SysRoleMenuService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sys_role_menu.CreateNewSysRoleMenuParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysMenuRole")
public class DefaultSysRoleMenuController {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@PostMapping("/createNewSysMenuRole")
	public ResponseMap createNewSysMenuRole(@RequestBody @Validated CreateNewSysRoleMenuParam param){
		return sysRoleMenuService.createNewSysRoleMenu(param);
	}
}
