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
public class AccountTest {
	private InputStream inputStream;
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	private IAccountDao accountDao;

	@Before
	public void init() throws IOException {
		// 1. 获取mybatis主配置文件的字节输入流
		inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 根据字节输入流构建SqlSessionFactory
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 3. 根据SqlSessionFactory生产一个SqlSession
		sqlSession = factory.openSession();
		// 4. 使用SqlSession创建Dao的代理对象
		accountDao = sqlSession.getMapper(IAccountDao.class);
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
	 * 查询所有账户，以及账户所属的用户信息
	 */
	@Test
	public void testFindAll() {
		//使用延时加载后
		List<Account> accounts = accountDao.findAll();//这条语句只会查询Account的信息，执行sql为：select * from account;
		for (Account account : accounts) {
			System.out.println("---------每个账户的信息-----------");
			System.out.println(account);
			System.out.println(account.getUser());//当需要从Account中获取User信息时，才会去查询User的信息，此时才会执行sql：select * from user where id = ?;
		}
	}

	/**
	 * 根据用户id查询账户信息
	 */
	@Test
	public void testFindAccountByUid() {
		List<Account> accounts = accountDao.findAccountByUid(46);
		for (Account account : accounts) {
			System.out.println(account);
		}
	}
}
