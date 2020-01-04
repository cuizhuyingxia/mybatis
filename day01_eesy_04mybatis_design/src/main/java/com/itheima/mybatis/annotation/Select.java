package com.itheima.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 20:31
 * @Version 1.0
 */

// 注解的生命周期为：被保留到运行时阶段
@Retention(RetentionPolicy.RUNTIME)
// 注解能够作用到的位置为：方法上
@Target(ElementType.METHOD)
public @interface Select {

	/**
	 * 该属性用于配置sql语句
	 * @return
	 */
	String value();
}
