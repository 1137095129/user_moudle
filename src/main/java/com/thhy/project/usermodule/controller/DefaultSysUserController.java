package com.thhy.project.usermodule.controller;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.service.SysUserService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sysuser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sysUser")
public class DefaultSysUserController {

	@Autowired
	private SysUserService sysUserService;


	@PostMapping("/selectByInfo")
	public ResponseMap selectByInfo(@RequestBody @Validated FindListInfoParam param) {
		return sysUserService.findUserListInfo(param);
	}

	@PostMapping("/findById")
	public ResponseMap findById(@RequestBody @Validated FindByIdParam param) {
		return sysUserService.findUserInfoById(param.getUserId());
	}

	@PostMapping("/updateSysUser")
	public ResponseMap updateUserInfo(@RequestBody @Validated UpdateUserInfoParam param) {
		return sysUserService.updateUserInfo(param);
	}

	@PostMapping("/deleteSysUsers")
	public ResponseMap deleteSysUsers(@RequestBody @Validated DeleteUserInfoParam param) {
		return sysUserService.deleteUserInfo(param.getUserId());
	}

	@PostMapping("/createNewSysUser")
	public ResponseMap createNewUser(@RequestBody NewUserInfo newUserInfo, HttpServletRequest request){
		DefaultUser user = (DefaultUser) request.getAttribute("user");
		newUserInfo.setCreateUser(user.getUserId());
		return sysUserService.createNewUser(newUserInfo);
	}

}
