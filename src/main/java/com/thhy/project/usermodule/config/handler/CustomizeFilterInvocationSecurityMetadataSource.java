package com.thhy.project.usermodule.config.handler;

import com.thhy.project.usermodule.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 获取某个url的访问权限
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	//最终执行的URL排除策略
	private volatile ExcludeUrlStrategy excludeUrlStrategy;
	//是否已经设置了URL排除策略
	private final AtomicBoolean isSet = new AtomicBoolean(false);
	//是否可重置URL排除策略，建议不可重置
	private final AtomicBoolean canReset = new AtomicBoolean(false);
	//是否已经进行了初始化
	private final AtomicBoolean hasInit = new AtomicBoolean(false);

	@Autowired
	private DefaultExcludeUrlStrategy strategy;

	@Autowired
	private ExcludeUrlConfig excludeUrlConfig;

	private static final Logger log = LoggerFactory.getLogger(CustomizeFilterInvocationSecurityMetadataSource.class);


	public void init() {
		if (hasInit.compareAndSet(false, true)) {
			canReset.set(excludeUrlConfig.getCanReset());
			if (excludeUrlConfig.getExcludeStrategyEnum() == null) {
				excludeUrlConfig.setExcludeStrategyEnum(ExcludeStrategyEnum.DEFAULT);
			}
			switch (excludeUrlConfig.getExcludeStrategyEnum()) {
				case DEFAULT:
					setExcludeUrlStrategy(strategy.defaultStrategy());
					break;
				case DEFAULT_DATABASE:
					setExcludeUrlStrategy(strategy.databaseDefaultStrategy());
					break;
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		if (!hasInit.get()) {
			init();
		}

		String requestUrl = null;
		if ((requestUrl = excludeUrlStrategy.excludeUrl((FilterInvocation) o)) == null && checkRequestUserInfo((FilterInvocation) o)) {
			return null;
		}
		return SecurityConfig.createList(requestUrl);
	}

	public boolean checkRequestUserInfo(FilterInvocation invocation) {
		DefaultUser user = (DefaultUser) invocation.getHttpRequest().getAttribute("user");
		return user != null;
	}

	public void setExcludeUrlStrategy(ExcludeUrlStrategy excludeUrlStrategy) {
		if (canReset.get()) {
			this.excludeUrlStrategy = excludeUrlStrategy;
			return;
		}
		if (!canReset.get() && !isSet.get() && isSet.compareAndSet(false, true)) {
			this.excludeUrlStrategy = excludeUrlStrategy;
		}
	}

	public ExcludeUrlStrategy getExcludeUrlStrategy() {
		return excludeUrlStrategy;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
