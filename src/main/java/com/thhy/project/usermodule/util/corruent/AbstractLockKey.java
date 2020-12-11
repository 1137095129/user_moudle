package com.thhy.project.usermodule.util.corruent;

import org.springframework.util.StringUtils;

public abstract class AbstractLockKey implements LockKey {

	private final String prefix;

	public AbstractLockKey(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String getKey(Object o) {
		if (o == null|| !StringUtils.hasText(o.toString())) {
			return null;
		}
		return getPrefix() + "__" + o.toString();
	}


}
