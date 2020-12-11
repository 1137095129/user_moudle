package com.thhy.project.usermodule.config;

import org.springframework.security.web.FilterInvocation;

/**
 *
 */
@FunctionalInterface
public interface ExcludeUrlStrategy {
	String excludeUrl(FilterInvocation invocation);
}
