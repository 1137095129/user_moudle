package com.thhy.project.usermodule.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "exclude")
public class ExcludeObject implements Serializable {
	private static final long serialVersionUID = 466166372121025885L;
	//不过滤的URL路径
	private String[] url;

	//URL路径的正则表达式
	private Map<String,String> matchUrl;

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	public Map<String, String> getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(Map<String, String> matchUrl) {
		this.matchUrl = matchUrl;
	}
}
