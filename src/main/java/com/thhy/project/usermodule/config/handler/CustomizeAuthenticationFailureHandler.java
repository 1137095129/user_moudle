package com.thhy.project.usermodule.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thhy.project.usermodule.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败处理逻辑
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResponseMap.error("用户名或密码错误，请重新登录！")));
	}
}
