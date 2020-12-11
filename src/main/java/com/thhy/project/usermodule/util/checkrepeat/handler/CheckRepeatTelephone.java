package com.thhy.project.usermodule.util.checkrepeat.handler;

import com.thhy.project.usermodule.util.checkrepeat.AbstractCheckUserInfo;
import com.thhy.project.usermodule.util.exception.TelephoneRepeatException;
import com.thhy.project.usermodule.vo.AbstractUserUnicodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("CheckRepeatTelephone")
public class CheckRepeatTelephone<T extends AbstractUserUnicodeInfo> extends AbstractCheckUserInfo<T> {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public boolean checkInfo(T t) throws Exception {
		Integer userId;
		if (StringUtils.hasText(t.getTelphone()) && (userId = sysUserMapper.findUserIdByTelephone(t.getTelphone())) != null) {
			if (t.getUserId() == null) {
				throw new TelephoneRepeatException();
			}
			if (userId.equals(t.getUserId())) {
				t.setTelphone(null);
			} else {
				throw new TelephoneRepeatException();
			}
		}
		return false;
	}
}
