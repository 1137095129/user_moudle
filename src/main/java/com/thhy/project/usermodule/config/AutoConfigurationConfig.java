package com.thhy.project.usermodule.config;

import com.thhy.project.usermodule.config.core.EncryptHandler;
import com.thhy.project.usermodule.config.core.TokenHandler;
import com.thhy.project.usermodule.config.core.defaultImpl.*;
import com.thhy.project.usermodule.util.encryption.AbstractEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自动装配类设置，
 * 当当前上下文没有指定类的Bean时，
 * 会自动加载该类的Bean
 */
@Configuration
public class AutoConfigurationConfig {

	@Autowired
	private UserLogConfig userLogConfig;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private OverallSituationEncryptConfig encryptConfig;

	@ConditionalOnMissingBean(AbstractTokenHandler.class)
	@Bean
	public TokenHandler tokenHandler(){
		AbstractTokenHandler tokenHandler = new DefaultTokenHandler();
		tokenHandler.setEncoder(passwordEncoder);
		tokenHandler.setRedisTemplate(redisTemplate);
		tokenHandler.setUserLogConfig(userLogConfig);
		return tokenHandler;
	}

	@ConditionalOnMissingBean(AbstractEncrypt.class)
	@Bean
	public EncryptHandler abstractEncrypt(){
		DefaultEncryptHandler encryptHandler = new DefaultEncryptHandler();
		encryptHandler.setRedisTemplate(redisTemplate);
		encryptHandler.setUserLogConfig(userLogConfig);
		encryptHandler.setEncryptConfig(encryptConfig);
		return encryptHandler;
	}
}
