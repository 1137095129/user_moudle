package com.thhy.project.usermodule.config.handler;

import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.config.OverallSituationEncryptConfig;
import com.thhy.project.usermodule.config.core.defaultImpl.AbstractTokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenFilter implements Filter {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private OverallSituationEncryptConfig encryptConfig;

	@Autowired
	private AbstractTokenHandler abstractTokenHandler;

	private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		try {
			DefaultUser user;
			if (encryptConfig.isShouldEncrypt()) {
				user = abstractTokenHandler.getUserInfoByTokenWithEncryptToken((HttpServletRequest) servletRequest, sysUserMapper::findUserInfoByUserName);
			} else {
				user = abstractTokenHandler.getUserInfoByTokenWithOutEncryptToken((HttpServletRequest) servletRequest, sysUserMapper::findUserInfoByUserName);
			}

			if (user != null) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				servletRequest.setAttribute("user", user);
			}

//			//获取token对
//			String token = request.getHeader("Authorization");
//			String userInfo = request.getHeader("UserInfo");
//
//			if (token == null || userInfo == null) {
//				return;
//			}
//			String userStr = (String) redisTemplate.opsForValue().get(userInfo);
//			Boolean refresh;
//			if (token.equals(userStr) &&
//					(refresh = (redisTemplate.expire(userInfo,
//							userLogConfig.getTokenRetentionTime(),
//							userLogConfig.getTimeUnit()))) != null
//					&& refresh) {
//
//				Algorithm algorithm = Algorithm.HMAC256(userLogConfig.getSecretKey());
//				JWTVerifier verifier = JWT.require(algorithm).build();
//				DecodedJWT decodedJWT = verifier.verify(token);
//
//				String[] authorities = decodedJWT.getClaim("authorities").asArray(String.class);
//				String username = decodedJWT.getClaim("username").as(String.class);
//				Integer userId = decodedJWT.getClaim("userId").as(Integer.class);
//
//				DefaultUserInfo defaultUserInfo = sysUserMapper.findUserInfoByUserName(username);
//
//				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//				for (String authority : authorities) {
//					grantedAuthorities.add(() -> authority);
//				}
//
//				DefaultUser user = new DefaultUser(username, defaultUserInfo.getPassword(), grantedAuthorities, userId);
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//				request.setAttribute("user", user);
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
}
