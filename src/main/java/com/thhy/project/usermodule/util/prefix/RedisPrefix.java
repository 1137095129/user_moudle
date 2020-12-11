package com.thhy.project.usermodule.util.prefix;

public interface RedisPrefix extends Prefix {
	String getKey(String prefix);
}
