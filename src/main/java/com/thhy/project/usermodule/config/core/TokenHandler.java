package com.thhy.project.usermodule.config.core;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.util.core.CurrencyServiceApi;
import com.thhy.project.usermodule.vo.DefaultUserInfo;

import javax.servlet.http.HttpServletRequest;

public interface TokenHandler extends TokenKeyHandler, TokenValueHandler {

	DefaultUser getUserInfoByTokenWithEncryptToken(HttpServletRequest request, CurrencyServiceApi<String, DefaultUserInfo> getUserApi) throws Exception;

	DefaultUser getUserInfoByTokenWithOutEncryptToken(HttpServletRequest request, CurrencyServiceApi<String, DefaultUserInfo> getUserApi) throws Exception;

}
