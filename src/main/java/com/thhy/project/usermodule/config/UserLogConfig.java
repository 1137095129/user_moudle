package com.thhy.project.usermodule.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "user-login")
public class UserLogConfig implements Serializable {
	private static final long serialVersionUID = 3455366848078726823L;
	//token留存时间
	private Long tokenRetentionTime;

	//留存时间单位
	private TimeUnit timeUnit;

	//JWT密钥
	private String secretKey;

	//JWT发布者
	private String jwtIssuer;

	//登录URL路径
	private String loginPath;

	public Long getTokenRetentionTime() {
		return tokenRetentionTime;
	}

	public void setTokenRetentionTime(Long tokenRetentionTime) {
		this.tokenRetentionTime = tokenRetentionTime;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getJwtIssuer() {
		return jwtIssuer;
	}

	public void setJwtIssuer(String jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}

	public String getLoginPath() {
		return loginPath;
	}

	public void setLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}
}
