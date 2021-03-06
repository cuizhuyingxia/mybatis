package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 19:04
 * @Version 1.0
 */
public class UserTest {
	private InputStream inputStream;
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	private IUserDao userDao;

	@Before
	public void init() throws IOException {
		// 1. 获取mybatis主配置文件的字节输入流
		inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 根据字节输入流构建SqlSessionFactory
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 3. 根据SqlSessionFactory生产一个SqlSession
		sqlSession = factory.openSession();
		// 4. 使用SqlSession创建Dao的代理对象
		userDao = sqlSession.getMapper(IUserDao.class);
	}

	@After
	public void destroy() throws IOException {
		// 提交事务
		sqlSession.commit();
		// 释放资源
		sqlSession.close();
		inputStream.close();
	}

	/**
	 * 查询所有用户，以及用户的账户信息
	 */
	@Test
	public void testFindAll() {
		List<User> users = userDao.findAll();
		for (User user : users) {
			System.out.println("------每个用户的信息----------");
			System.out.println(user);
			System.out.println(user.getAccounts());
		}
	}

	/**
	 * 测试一级缓存
	 */
	@Test
	public void testFirstLevelCache() {
		User user1 = userDao.findUserById(46);
		System.out.println(user1);

		// 清空一级缓存
		// sqlSession.clearCache();

		User user2 = userDao.findUserById(46);
		System.out.println(user2);

		System.out.println(user1 == user2);
	}
}
