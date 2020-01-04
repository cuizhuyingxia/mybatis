package com.itheima.mybatis.sqlsession.defaults;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 17:30
 * @Version 1.0
 */

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
	// 保存解析过的xml文件的配置信息
	private Configuration cfg;

	// 构造方法，接收一个Configuration对象
	public DefaultSqlSessionFactory(Configuration cfg) {
		this.cfg = cfg;
	}
	/**
	 * 用于打开一个新的SqlSession对象
	 * @return
	 */
	public SqlSession openSession() {
		return new DefaultSqlSession(cfg);
	}
}
