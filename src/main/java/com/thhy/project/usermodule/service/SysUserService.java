package com.thhy.project.usermodule.service;

import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.vo.DefaultButtonInfo;
import com.thhy.project.usermodule.vo.DefaultMenuInfo;
import com.thhy.project.usermodule.vo.DefaultUserListInfo;
import com.thhy.project.usermodule.vo.param.sysuser.FindListInfoParam;
import com.thhy.project.usermodule.vo.param.sysuser.NewUserInfo;
import com.thhy.project.usermodule.vo.param.sysuser.UpdateUserInfoParam;

import java.util.List;

public interface SysUserService {

	/**
	 * 获取指定用户的最终可请求的URL路径
	 * @param userId
	 * @return
	 */
	List<String> getLastUrlListByUserId(Integer userId);

	/**
	 * 获取指定用户的最终可访问的菜单信息
	 * @param userId
	 * @return
	 */
	List<DefaultMenuInfo> getLastMenuListByUserId(Integer userId);

	/**
	 * 获取指定用户的最终可点击的按钮列表
	 * @param userId
	 * @return
	 */
	List<DefaultButtonInfo> getLastButtonListByUserId(Integer userId);

	ResponseMap findUserListInfo(FindListInfoParam param);

	ResponseMap findUserInfoById(Integer userId);

	ResponseMap updateUserInfo(UpdateUserInfoParam param);

	ResponseMap deleteUserInfo(Integer userId);

	ResponseMap createNewUser(NewUserInfo newUserInfo);

}
