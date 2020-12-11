package com.thhy.project.usermodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultExcludeUrlStrategy {

	@Autowired
	private ExcludeObject excludeObject;

	@Autowired
	private SysUrlMapper sysUrlMapper;

	public ExcludeUrlStrategy defaultStrategy() {
		return invocation -> {
			String requestURI = invocation.getHttpRequest().getRequestURI();
			if (excludeObject.getUrl() != null && excludeObject.getUrl().length > 0) {
				for (String url : excludeObject.getUrl()) {
					if (requestURI.matches(url)) {
						return null;
					}
				}
			}

			if (excludeObject.getMatchUrl() != null) {
				for (Map.Entry<String, String> entry : excludeObject.getMatchUrl().entrySet()) {
					if (requestURI.matches(entry.getValue())) {
						requestURI = "/" + entry.getKey();
						break;
					}
				}
			}
			return requestURI;
		};
	}

	public ExcludeUrlStrategy databaseDefaultStrategy() {
		return invocation -> {
			String requestURI = invocation.getHttpRequest().getRequestURI();
			if (excludeObject.getMatchUrl() != null) {
				for (Map.Entry<String, String> entry : excludeObject.getMatchUrl().entrySet()) {
					if (requestURI.matches(entry.getValue())) {
						requestURI = "/" + entry.getKey();
						break;
					}
				}
			}
			Integer url = sysUrlMapper.findUrlByUrlPath(requestURI);
			return url == null ? null : requestURI;
		};
	}

}
