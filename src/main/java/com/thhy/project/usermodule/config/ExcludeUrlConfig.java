package com.thhy.project.usermodule.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "exclude-config")
public class ExcludeUrlConfig implements Serializable {
	private static final long serialVersionUID = 5725886485303322980L;

	private boolean canReset;

	private ExcludeStrategyEnum excludeStrategyEnum;

	public boolean getCanReset() {
		return canReset;
	}

	public void setCanReset(boolean canReset) {
		this.canReset = canReset;
	}

	public ExcludeStrategyEnum getExcludeStrategyEnum() {
		return excludeStrategyEnum;
	}

	public void setExcludeStrategyEnum(ExcludeStrategyEnum excludeStrategyEnum) {
		this.excludeStrategyEnum = excludeStrategyEnum;
	}
}
