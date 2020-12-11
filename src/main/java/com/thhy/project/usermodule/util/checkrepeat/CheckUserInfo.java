package com.thhy.project.usermodule.util.checkrepeat;

public interface CheckUserInfo<T> extends Check {
	boolean checkInfo(T t) throws Exception;
}
