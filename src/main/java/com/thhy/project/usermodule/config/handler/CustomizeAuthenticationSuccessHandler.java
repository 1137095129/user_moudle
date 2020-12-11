package com.thhy.project.usermodule.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.config.OverallSituationEncryptConfig;
import com.thhy.project.usermodule.config.core.EncryptHandler;
import com.thhy.project.usermodule.config.core.defaultImpl.AbstractTokenHandler;
import com.thhy.project.usermodule.service.SysUserService;
import com.thhy.project.usermodule.util.ResponseMap;
import com.thhy.project.usermodule.util.SysMenuUtil;
import com.thhy.project.usermodule.vo.DefaultButtonInfo;
import com.thhy.project.usermodule.vo.DefaultLogInResult;
import com.thhy.project.usermodule.vo.DefaultMenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;


/**
 * 登陆成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private OverallSituationEncryptConfig overallSituationEncryptConfig;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EncryptHandler encryptHandler;

	@Autowired
	private AbstractTokenHandler tokenHandler;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		DefaultUser user = (DefaultUser) authentication.getPrincipal();
		//通过用户信息生成token的key值
		String username = tokenHandler.handleKey(user);
		//生成token的value值
		String token = tokenHandler.handleValue(user, username);
		//获取按钮权限
		List<DefaultButtonInfo> lastButtonList = sysUserService.getLastButtonListByUserId(user.getUserId());
		//获取菜单权限
		List<DefaultMenuInfo> lastMenuList = sysUserService.getLastMenuListByUserId(user.getUserId());
		//构建菜单树形列表
		List<DefaultMenuInfo> routeList = SysMenuUtil.getSysRouteList(lastMenuList, 0);
		//构建返回值对象
		DefaultLogInResult result = new DefaultLogInResult();
		result.setToken(token);
		result.setUserInfo(username);
		result.setButtonInfos(lastButtonList);
		result.setMenuInfos(routeList);
		//当需要密钥处理时
		if (overallSituationEncryptConfig.isShouldEncrypt()) {
			try {
				result.setPublicKey(encryptHandler.handle(user));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResponseMap.ok(result)));
	}
}
