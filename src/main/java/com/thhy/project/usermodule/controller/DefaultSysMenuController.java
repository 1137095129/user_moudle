package com.thhy.project.usermodule.controller;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.service.SysMenuService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sysmenu.CreateParam;
import com.thhy.project.usermodule.vo.param.sysmenu.DeleteParam;
import com.thhy.project.usermodule.vo.param.sysmenu.FindListByRoleParam;
import com.thhy.project.usermodule.vo.param.sysmenu.UpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sysMenus")
public class DefaultSysMenuController {

	@Autowired
	@Qualifier("DefaultSysMenuServiceImpl")
	private SysMenuService sysMenuService;

	/**
	 * 查询所有的1级菜单
	 *
	 * @return
	 */
	@PostMapping("/findLV1MenuInfo")
	public ResponseMap findLV1MenuInfo() {
		return sysMenuService.findLV1MenuInfo();
	}

	/**
	 * 查询菜单信息
	 *
	 * @return
	 */
	@PostMapping("/selectByInfo")
	public ResponseMap selectByInfo() {
		return sysMenuService.findMenuInfo();
	}

	/**
	 * 删除菜单信息
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/deleteSysMenus")
	public ResponseMap deleteMenuInfo(@RequestBody @Validated DeleteParam param) {
		return sysMenuService.deleteMenuInfo(param.getMenuId());
	}

	/**
	 * 更新菜单信息
	 * @param param
	 * @return
	 */
	@PostMapping("/updateSysMenu")
	public ResponseMap updateMenuInfo(@RequestBody @Validated UpdateParam param,HttpServletRequest request) {
		DefaultUser user = (DefaultUser) request.getAttribute("user");
		param.setUpdateUser(user.getUserId());
		return sysMenuService.updateMenuInfo(param);
	}

	/**
	 * 添加菜单信息
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping("/createNewSysMenu")
	public ResponseMap createMenuInfo(@RequestBody @Validated CreateParam param, HttpServletRequest request){
		DefaultUser user = (DefaultUser) request.getAttribute("user");
		param.setCreateUser(user.getUserId());
		param.setUpdateUser(user.getUserId());
		return sysMenuService.createNewMenuInfo(param);
	}

	/**
	 * 查询所有的图标信息
	 * @return
	 */
	@PostMapping("/findAllIconInfo")
	public ResponseMap findAllIconList(){
		return sysMenuService.findAllIconList();
	}

	@PostMapping("/findMenusListByRoleId")
	public ResponseMap findMenusListByRoleId(@RequestBody @Validated FindListByRoleParam param){
		return sysMenuService.findMenuListByRoleId(param.getRoleId());
	}
}
