package com.thhy.project.usermodule.util.checkrepeat;

public abstract class AbstractCheckUserInfo<T> implements CheckUserInfo<T> {
	@Override
	public boolean check(Object o) throws Exception {
		return checkInfo((T) o);
	}
}
