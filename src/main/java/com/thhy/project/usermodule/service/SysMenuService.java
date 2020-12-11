package com.thhy.project.usermodule.service;

import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.param.sysmenu.CreateParam;
import com.thhy.project.usermodule.vo.param.sysmenu.UpdateParam;

public interface SysMenuService {

	ResponseMap findLV1MenuInfo();

	ResponseMap findMenuInfo();

	ResponseMap deleteMenuInfo(Integer menuId);

	ResponseMap updateMenuInfo(UpdateParam param);

	ResponseMap createNewMenuInfo(CreateParam param);

	ResponseMap findAllIconList();

	ResponseMap findMenuListByRoleId(Integer roleId);
}
