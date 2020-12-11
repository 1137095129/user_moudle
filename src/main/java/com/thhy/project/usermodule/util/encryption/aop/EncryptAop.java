package com.thhy.project.usermodule.util.encryption.aop;

import com.thhy.project.usermodule.config.DefaultUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class EncryptAop {
	@Pointcut("@annotation(com.thhy.project.usermodule.util.encryption.aop.EncryptAopAnnotation)")
	private void pointcut(){

	}

	@Before(value = "pointcut()")
	public void before(JoinPoint joinpoint){
		Object[] args = joinpoint.getArgs();
		MethodSignature signature = (MethodSignature) joinpoint.getSignature();
		EncryptAopAnnotation encryptAopAnnotation = signature.getMethod().getAnnotation(EncryptAopAnnotation.class);
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		DefaultUser user = (DefaultUser) request.getAttribute("user");

	}
}
