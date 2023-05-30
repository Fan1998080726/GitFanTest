package com.sdkj.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * 
 * @author  fcl  2021年4月14日09:22:33
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnno {
	 String operateType()	default ""; // 记录日志的操作类型
 
}
