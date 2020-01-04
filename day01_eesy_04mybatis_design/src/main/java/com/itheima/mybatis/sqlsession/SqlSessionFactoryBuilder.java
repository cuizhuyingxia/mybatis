package com.itheima.mybatis.sqlsession;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 15:55
 * @Version 1.0
 */

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.itheima.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory工厂对象
 */
public class SqlSessionFactoryBuilder {

	/**
	 * 创建SqlSessionFactory工厂对象
	 * @param config	mybatis主配置文件SqlMapConfig.xml的字节输入流
	 * @return
	 */
	public SqlSessionFactory build(InputStream config) {
		// 创建自定义Mybatis的配置类对象，保存了解析过的xml文件的配置信息
		Configuration configuration = XMLConfigBuilder.loadConfiguration(config);
		return new DefaultSqlSessionFactory(configuration);
	}
}
