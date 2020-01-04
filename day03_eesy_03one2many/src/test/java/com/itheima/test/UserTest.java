package com.itheima.test;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 11:00
 * @Version 1.0
 */

import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;
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
 * 测试mybatis的CRUD方法
 */
public class UserTest {
	private InputStream inputStream;
	private SqlSession sqlSession;
	private IUserDao userDao;

	/*
    @Before注解修饰的方法会在测试方法之前执行
    @After注解修饰的方法会在测试方法之后执行
    注意：无论测试方法是否会出现异常，@Before注解和@After注解修饰的方法都会被执行
     */

	/**
	 * 创建dao接口代理对象
	 * @throws IOException
	 */
	@Before
	public void init() throws IOException {
		// 1. 读取mybatis主配置文件，返回字节输入流
		inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 获取SqlSessionFactory工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 3. 获取SqlSession对象
		sqlSession = factory.openSession();
		// 4. 获取dao接口的代理对象
		userDao = sqlSession.getMapper(IUserDao.class);
	}

	/**
	 * 释放资源
	 * @throws IOException
	 */
	@After
	public void destroy() throws IOException {
		// 提交事务
		sqlSession.commit();
		// 释放资源
		sqlSession.close();
		inputStream.close();
	}

	/**
	 * 查询所有用户信息，和用户所属的账户信息，并封装到User中
	 */
	@Test
	public void testFindUserAndAccount() {
		List<User> users = userDao.findUserAndAccount();
		for (User user : users) {
			System.out.println("----------每个用户的信息--------------");
			System.out.println(user);
			System.out.println(user.getAccounts());
		}
	}
}
