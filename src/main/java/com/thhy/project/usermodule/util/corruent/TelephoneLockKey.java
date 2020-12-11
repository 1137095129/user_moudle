package com.thhy.project.usermodule.util.corruent;

public class TelephoneLockKey extends AbstractLockKey {
	public TelephoneLockKey() {
		super("TELEPHONE");
	}
	public static LockKey getLockKey(){
		return new TelephoneLockKey();
	}
}
