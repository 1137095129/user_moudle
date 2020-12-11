package com.thhy.project.usermodule.util.checkrepeat.handler;

import com.thhy.project.usermodule.util.checkrepeat.AbstractCheckUserInfo;
import com.thhy.project.usermodule.util.exception.UsernameRepeatException;
import com.thhy.project.usermodule.vo.AbstractUserUnicodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("CheckRepeatUsername")
public class CheckRepeatUsername<T extends AbstractUserUnicodeInfo> extends AbstractCheckUserInfo<T> {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public boolean checkInfo(T t) throws Exception {
		Integer userId;
		if (StringUtils.hasText(t.getUsername()) && (userId = sysUserMapper.findUserIdByUsername(t.getUsername())) != null) {
			if (t.getUserId() == null) {
				throw new UsernameRepeatException();
			}
			if (userId.equals(t.getUserId())) {
				t.setUsername(null);
			} else {
				throw new UsernameRepeatException();
			}
		}
		return false;
	}
}
