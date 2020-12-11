package com.thhy.project.usermodule.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 访问决策管理器
 * 在此判断用户是否拥有对某个url的访问权限
 */
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {

	private static final Logger log = LoggerFactory.getLogger(CustomizeAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
		//当前用户所拥有的权限
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (authorities == null || authorities.size() == 0) {
			throw new AccessDeniedException("没有相应权限！");
		}
		for (ConfigAttribute next : collection) {
			for (GrantedAuthority authority : authorities) {
				if (next.getAttribute().equals(authority.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限认证失败！");
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
