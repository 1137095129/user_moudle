package com.thhy.project.usermodule.util.corruent;

public class EmailLockKey extends AbstractLockKey {
	public EmailLockKey() {
		super("EMAIL");
	}

	public static LockKey getLockKey(){
		return new EmailLockKey();
	}
}
