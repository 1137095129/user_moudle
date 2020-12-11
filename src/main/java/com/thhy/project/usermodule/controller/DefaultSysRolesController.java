package com.thhy.project.usermodule.controller;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.service.SysRolesService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sysrole.CreateNewSysRoleParam;
import com.thhy.project.usermodule.vo.param.sysrole.DeleteRoleParam;
import com.thhy.project.usermodule.vo.param.sysrole.RolesListParam;
import com.thhy.project.usermodule.vo.param.sysrole.UpdateRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sysRoles")
public class DefaultSysRolesController {

	@Autowired
	@Qualifier("DefaultSysRolesServiceImpl")
	private SysRolesService sysRolesService;

	@PostMapping("/findAllSysRolesList")
	public ResponseMap findAllSysRolesList(){
		return sysRolesService.findAllRolesInfo();
	}

	@PostMapping("/selectByInfo")
	public ResponseMap findSysRolesListByParam(@RequestBody RolesListParam param){
		return sysRolesService.findSysRolesListByParam(param);
	}

	@PostMapping("/createNewSysRole")
	public ResponseMap createNewSysRole(@RequestBody @Validated CreateNewSysRoleParam param, HttpServletRequest request){
		DefaultUser user = (DefaultUser) request.getAttribute("user");
		param.setCreateBy(user.getUserId());
		param.setUpdateBy(user.getUserId());
		return sysRolesService.createNewRole(param);
	}

	@PostMapping("/deleteSysRoles")
	public ResponseMap deleteRoleInfo(@RequestBody@Validated DeleteRoleParam param){
		return sysRolesService.deleteRoleInfo(param.getRoleId());
	}

	@PostMapping("/updateSysRole")
	public ResponseMap updateRoleInfo(@RequestBody@Validated UpdateRoleParam param,HttpServletRequest request){
		DefaultUser user = (DefaultUser) request.getAttribute("user");
		param.setUpdateBy(user.getUserId());
		return sysRolesService.updateRoleInfo(param);
	}

}
