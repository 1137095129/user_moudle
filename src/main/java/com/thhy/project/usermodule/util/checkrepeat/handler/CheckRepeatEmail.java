package com.thhy.project.usermodule.util.checkrepeat.handler;

import com.thhy.project.usermodule.util.checkrepeat.AbstractCheckUserInfo;
import com.thhy.project.usermodule.util.exception.EmailRepeatException;
import com.thhy.project.usermodule.vo.AbstractUserUnicodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("CheckRepeatEmail")
public class CheckRepeatEmail<T extends AbstractUserUnicodeInfo> extends AbstractCheckUserInfo<T> {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public boolean checkInfo(T t) throws Exception {
		Integer userId;
		if (StringUtils.hasText(t.getEmail()) && (userId = sysUserMapper.findUserIdByEmail(t.getEmail())) != null) {
			//添加
			if (t.getUserId() == null) {
				throw new EmailRepeatException();
			}
			//修改
			if (userId.equals(t.getUserId())) {
				t.setEmail(null);
			} else {
				throw new EmailRepeatException();
			}
		}
		return false;
	}
}
