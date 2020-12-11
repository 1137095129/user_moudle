package com.thhy.project.usermodule.util.encryption.aop;

import com.thhy.project.usermodule.util.core.CodeEnum;

public @interface EncryptAopAnnotation {
	Class<?> value() default Object.class;
	CodeEnum code() default CodeEnum.JSON;
}
