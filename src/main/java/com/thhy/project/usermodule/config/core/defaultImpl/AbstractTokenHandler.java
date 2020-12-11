package com.thhy.project.usermodule.config.core.defaultImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.thhy.project.usermodule.config.DefaultUser;
import com.thhy.project.usermodule.config.UserLogConfig;
import com.thhy.project.usermodule.config.core.TokenHandler;
import com.thhy.project.usermodule.util.core.CurrencyServiceApi;
import com.thhy.project.usermodule.vo.DefaultUserInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 该类为生成用户token和通过token获取用户信息的默认实现类，
 * 如需重写该类方法，只需继承此类并重写对应方法，最后注入到Spring容器中即可
 */
public abstract class AbstractTokenHandler implements TokenHandler {
	private UserLogConfig userLogConfig;

	private RedisTemplate<String, Object> redisTemplate;

	private BCryptPasswordEncoder encoder;

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public void setUserLogConfig(UserLogConfig userLogConfig) {
		this.userLogConfig = userLogConfig;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public String handleKey(DefaultUser user) {
		return encoder.encode(user.getUsername());
	}

	@Override
	public String handleValue(DefaultUser user, String tokenKey) {
		String token = createTokenByUser(user);
		redisTemplate.opsForValue()
				.set(tokenKey, token,
						userLogConfig.getTokenRetentionTime(),
						userLogConfig.getTimeUnit());
		return token;
	}

	@Override
	public DefaultUser getUserInfoByTokenWithEncryptToken(HttpServletRequest request, CurrencyServiceApi<String, DefaultUserInfo> getUserApi) throws Exception {
		String tokenValue = request.getHeader("Authorization");
		String tokenKey = request.getHeader("UserInfo");
		String encryptToken = request.getHeader("EncryptToken");
		if (tokenValue == null || tokenKey == null || encryptToken == null) {
			return null;
		}
		String luaScript = "if(redis.call('expire',KEY[1],ARGV[1])=='OK') then " +
				"if(redis.call('expire',KEY[2],ARGV[2])='OK') then " +
				"return 'OK'" +
				"else " +
				"redis.call('del',KEY[1])" +
				"return 'FAIL'" +
				"end" +
				"else " +
				"redis.call('del',KEY[2])" +
				"return 'FAIL'" +
				"end";
		List<String> keys = new ArrayList<>();
		keys.add(tokenKey);
		keys.add(encryptToken);
		long seconds = userLogConfig.getTimeUnit().toSeconds(userLogConfig.getTokenRetentionTime());
		String execute = redisTemplate.execute(RedisScript.of(luaScript), keys, seconds, seconds);
		String userStr = (String) redisTemplate.opsForValue().get(tokenKey);
		String encryptStr = (String) redisTemplate.opsForValue().get(encryptToken);
		DefaultUser user = null;
		if ("OK".equals(execute) && tokenValue.equals(userStr)) {
			user = createUserByToken(tokenKey, tokenValue, getUserApi);
			user.setEncryptKey(encryptStr);
		}
		return user;
	}

	@Override
	public DefaultUser getUserInfoByTokenWithOutEncryptToken(HttpServletRequest request, CurrencyServiceApi<String, DefaultUserInfo> getUserApi) throws Exception {
		//获取token对
		String authTokenValue = request.getHeader("Authorization");
		String authTokenKey = request.getHeader("UserInfo");
		if (authTokenValue == null || authTokenKey == null) {
			return null;
		}
		String userStr = (String) redisTemplate.opsForValue().get(authTokenKey);
		Boolean refresh;
		DefaultUser user = null;
		if (authTokenValue.equals(userStr) &&
				(refresh = (redisTemplate.expire(authTokenKey,
						userLogConfig.getTokenRetentionTime(),
						userLogConfig.getTimeUnit()))) != null
				&& refresh) {
			user = createUserByToken(authTokenValue, authTokenKey, getUserApi);
		}

		return user;
	}

	/**
	 * 通过用户信息生成tokenValue
	 *
	 * @param user 用户信息
	 * @return
	 */
	protected String createTokenByUser(DefaultUser user) {
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		String[] urls = new String[authorities.size()];
		int i = 0;
		for (GrantedAuthority authority : authorities) {
			urls[i++] = authority.getAuthority();
		}
		//JWT加密
		Algorithm algorithm = Algorithm.HMAC256(userLogConfig.getSecretKey());
		return JWT.create()
				.withIssuer(userLogConfig.getJwtIssuer())
				.withSubject("userInfo")
				.withClaim("username", user.getUsername())
				.withClaim("userId", user.getUserId())
				.withArrayClaim("authorities", urls)
				.sign(algorithm);
	}

	/**
	 * 根据用户信息token对构建User对象
	 *
	 * @param tokenKey   用户token的key
	 * @param tokenValue 用户token的value
	 * @param getUserApi 通过用户名获取用户信息的方法
	 * @return
	 */
	protected DefaultUser createUserByToken(String tokenKey, String tokenValue, CurrencyServiceApi<String, DefaultUserInfo> getUserApi) {
		Algorithm algorithm = Algorithm.HMAC256(userLogConfig.getSecretKey());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(tokenValue);

		String[] authorities = decodedJWT.getClaim("authorities").asArray(String.class);
		String username = decodedJWT.getClaim("username").as(String.class);
		Integer userId = decodedJWT.getClaim("userId").as(Integer.class);

		DefaultUserInfo defaultUserInfo = getUserApi.execute(username);

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (String authority : authorities) {
			grantedAuthorities.add(() -> authority);
		}

		return new DefaultUser(username, defaultUserInfo.getPassword(), grantedAuthorities, userId);
	}
}
