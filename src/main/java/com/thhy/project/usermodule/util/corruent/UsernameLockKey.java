package com.thhy.project.usermodule.util.corruent;

public class UsernameLockKey extends AbstractLockKey {

	public UsernameLockKey() {
		super("USERNAME");
	}

	public static LockKey getLockKey(){
		return new UsernameLockKey();
	}
}
