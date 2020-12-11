package com.thhy.project.usermodule.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thhy.project.usermodule.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限拒绝处理逻辑
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		e.printStackTrace();
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResponseMap.jurisdictionError()));
	}
}
