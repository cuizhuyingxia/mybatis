package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 17:37
 * @Version 1.0
 */

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSession implements SqlSession {
	// 保存解析过的xml文件的配置信息
	private Configuration cfg;
	private Connection conn;

	public DefaultSqlSession(Configuration cfg) {
		this.cfg = cfg;
		conn = DataSourceUtil.getConnection(cfg);
	}

	/**
	 * 创建dao接口的代理对象
	 * @param daoInterfaceClass dao接口的Class对象
	 * @param <T>
	 * @return
	 */
	public <T> T getMapper(Class<T> daoInterfaceClass) {
		// 创建代理对象
		return (T) Proxy.newProxyInstance(
				// 参数1：被代理的对象的类加载器
				daoInterfaceClass.getClassLoader(),
				// 参数2：被代理的对象实现的接口			我们接收的参数daoInterfaceClass本身就是一个接口
				new Class[]{daoInterfaceClass},
				// 参数3：如何代理，可以定义自己的代理方式
				new MapperProxy(cfg.getMappers(), conn));
	}

	/**
	 * 释放资源
	 */
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
