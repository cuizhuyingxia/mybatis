package com.itheima.test;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 11:00
 * @Version 1.0
 */

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;
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
public class AccountTest {
	private InputStream inputStream;
	private SqlSession sqlSession;
	private IAccountDao accountDao;

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
		accountDao = sqlSession.getMapper(IAccountDao.class);
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
	 * 查询所有账户信息
	 */
	@Test
	public void testFindAll() {
		List<Account> accounts = accountDao.findAll();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	/**
	 * 查询所有账户信息，同时还要获取账户所属用户的username和address
	 */
	@Test
	public void testFindAccountUser() {
		List<AccountUser> accountUsers = accountDao.findAccountUser();
		for (AccountUser accountUser : accountUsers) {
			System.out.println(accountUser);
		}
	}

	/**
	 * 查询所有账户信息，和账户所属的用户信息，并封装到Account中
	 */
	@Test
	public void testFindAccountAndUser() {
		List<Account> accounts = accountDao.findAccountAndUser();
		for (Account account : accounts) {
			System.out.println("----------每个账户的信息---------");
			System.out.println(account);
			System.out.println(account.getUser());
		}
	}

}
