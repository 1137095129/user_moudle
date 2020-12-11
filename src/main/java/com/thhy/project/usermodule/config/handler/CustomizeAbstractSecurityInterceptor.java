package com.thhy.project.usermodule.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限拦截器
 * 权限认证的统一入口
 */
@Deprecated
//@Component
public class CustomizeAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

//	@Autowired
//	private AccessDecisionManager decisionManager;

	@Autowired
	private FilterInvocationSecurityMetadataSource metadataSource;

	@Autowired
	public void setMyAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		super.setAccessDecisionManager(accessDecisionManager);
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.metadataSource;
	}


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		FilterInvocation invocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
		invoke(invocation);
	}

	private void invoke(FilterInvocation invocation) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(invocation);

		try {
			invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
			super.afterInvocation(token, null);
		} finally {
			super.finallyInvocation(token);
		}
	}
}
