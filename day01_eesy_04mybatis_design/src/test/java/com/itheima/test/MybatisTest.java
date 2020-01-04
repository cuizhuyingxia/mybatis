package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/7 21:14
 * @Version 1.0
 */
public class MybatisTest {

	/**
	 * mybatis入门案例
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// 1. 读取配置文件
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 创建SqlSessionFactoryBuilder工厂对象
		// 先创建SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		// 然后使用SqlSessionFactoryBuilder对象的build方法，创建SqlSessionFactoryBuilder工厂对象
		SqlSessionFactory factory = builder.build(inputStream);
		// 3. 使用工厂对象创建SqlSession对象。SqlSession对象可以用来操作数据库
		SqlSession session = factory.openSession();
		// 4. 使用SqlSession对象创建IUserDao的代理对象
		IUserDao userDao = session.getMapper(IUserDao.class);
		// 5. 使用代理对象执行方法
		List<User> users = userDao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
		// 6. 释放资源
		session.close();
		inputStream.close();

	}
}
