package com.thhy.project.usermodule.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class SpringRedisLockRegistryUtil {
	@Autowired
	private RedisLockRegistry redisLockRegistry;

	public Lock obtainLock(String lockKey) {
		if (lockKey != null)
			return redisLockRegistry.obtain(lockKey);
		return null;
	}

	public void lock(Lock lock) {
		if (lock != null) {
			lock.tryLock();
		}
	}

	public void unlock(Lock lock) {
		if (lock != null) {
			lock.unlock();
		}
	}
}
