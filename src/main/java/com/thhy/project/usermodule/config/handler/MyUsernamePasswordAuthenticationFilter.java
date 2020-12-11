package com.thhy.project.usermodule.config.handler;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thhy.project.usermodule.vo.UsernameAndPassword;
import lombok.SneakyThrows;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@SneakyThrows
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return getUser(request).getUsername();
	}

	@SneakyThrows
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return getUser(request).getPassword();
	}


	private UsernameAndPassword getUser(HttpServletRequest request) {
		UsernameAndPassword user;
		try {
			BufferedReader reader = request.getReader();
			String read = IoUtil.read(reader);
			user = objectMapper.readValue(read, UsernameAndPassword.class);
			request.setAttribute("user", user);
		} catch (Exception e) {
			user = (UsernameAndPassword) request.getAttribute("user");
		}

		return user;
	}
}
