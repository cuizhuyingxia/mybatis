package com.itheima.test;

import com.itheima.dao.IUserDao;
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
public class SecondLevelCacheTest {
	private InputStream inputStream;
	private SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		// 1. 获取mybatis主配置文件的字节输入流
		inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 根据字节输入流构建SqlSessionFactory
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@After
	public void destroy() throws IOException {
		inputStream.close();
	}



	/**
	 * 测试二级缓存
	 */
	@Test
	public void testFirstLevelCache() {

		/*
		二级缓存:
			指的是mybatis中SqlSessionFactory对象的缓存。
			由同一个SqlSessionFactory对象创建的SqlSession对象，会共享SqlSessionFactory对象的缓存。
		 */

		// 使用factory创建一个SqlSession对象，然后查询数据
		SqlSession sqlSession1 = factory.openSession();
		IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
		User user1 = userDao1.findUserById(46);
		System.out.println(user1);
		sqlSession1.close();

		// 再使用factory创建一个SqlSession对象，然后查询相同的数据
		SqlSession sqlSession2 = factory.openSession();
		IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
		User user2 = userDao2.findUserById(46);
		System.out.println(user2);
		sqlSession2.close();

		/*
		使用注解开发，开启二级缓存步骤：
			1. 让mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
			2. 让注解所在的dao接口支持二级缓存（在dao接口中配置）
		 */

		// 当开启了二级缓存后，通过控制台的输出可以看出，只向数据库中查询了一次，说明userDao2查询的数据，是从SqlSessionFactory对象的缓存中获取的
		// 如果没有开启二级缓存，则会向数据库中查询两次

		System.out.println(user1 == user2);	// false
		// 既然是从SqlSessionFactory对象的缓存中获取的数据，那为什么两次的结果会是false呢
		// 因为SqlSessionFactory缓存中存储的只是数据，每次从SqlSessionFactory缓存中取出数据时，都会去创建一个新的对象，并把数据封装进去，然后返回新的对象
		// 所以每次返回的都不是同一个对象，只是对象中的数据相同而已，因此比对结果会是false
	}
}
