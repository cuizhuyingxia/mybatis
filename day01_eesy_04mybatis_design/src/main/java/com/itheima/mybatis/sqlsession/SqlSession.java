package com.itheima.mybatis.sqlsession;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 16:01
 * @Version 1.0
 */

/**
 * 和数据库交互的接口，可以创建dao接口的代理对象
 */
public interface SqlSession {

	/**
	 * 创建dao接口的代理对象
	 * @param daoInterfaceClass dao接口的Class对象
	 * @param <T>
	 * @return
	 */
	<T> T getMapper(Class<T> daoInterfaceClass);

	/**
	 * 释放资源
	 */
	void close();
}
