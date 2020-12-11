package com.thhy.project.usermodule.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thhy.project.usermodule.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问无权限资源逻辑
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		e.printStackTrace();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResponseMap.toLogError()));
	}
}
