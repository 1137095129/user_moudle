package com.thhy.project.usermodule.config;

import com.thhy.project.usermodule.config.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomizeAccessDecisionManager accessDecisionManager;

	@Autowired
	private CustomizeAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomizeAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private CustomizeFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

	@Autowired
	private CustomizeLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private TokenFilter tokenFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserLogConfig userLogConfig;

	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager);
		filter.setAuthenticationFailureHandler(authenticationFailureHandler);
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(StringUtils.hasText(userLogConfig.getLoginPath()) ? userLogConfig.getLoginPath() : "/userLogin", "POST"));
		filter.setAllowSessionCreation(false);
		filter.afterPropertiesSet();
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
				.csrf()
				.disable();

		http.authorizeRequests()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						//决策管理器
						o.setAccessDecisionManager(accessDecisionManager);
						//安全元数据源
						o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
						o.afterPropertiesSet();
						return o;
					}
				})
				.and()
				//禁用session，因为在小程序中cookie是不可用的
				.sessionManagement()
				.disable()
				.authorizeRequests()
				//允许所有用户请求登出接口
				.and()
				.logout()
				.permitAll()
				//登出成功处理
				.logoutSuccessHandler(logoutSuccessHandler)
				//异常处理
				.and()
				.exceptionHandling()
				//匿名用户访问无权限资源时
				.authenticationEntryPoint(authenticationEntryPoint)
				//权限被拒绝时
				.accessDeniedHandler(accessDeniedHandler);

//		http.addFilterBefore(abstractSecurityInterceptor, FilterSecurityInterceptor.class);
		http.addFilterAt(myUsernamePasswordAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
	}


}
