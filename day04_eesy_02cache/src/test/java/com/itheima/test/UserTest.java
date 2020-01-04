package com.itheima.test;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 11:00
 * @Version 1.0
 */

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
 * 测试mybatis的CRUD方法
 */
public class UserTest {
	private InputStream inputStream;
	private SqlSessionFactory factory;
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
		factory = new SqlSessionFactoryBuilder().build(inputStream);
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
	 * 测试mybatis的一级缓存
	 */
	@Test
	public void testFirstLevelCache() {
		User user1 = userDao.findById(60);
		System.out.println(user1);

		/*
		一级缓存:
			指的是mybatis中SqlSession对象的缓存。
			当我们执行查询之后，查询的结果会同时存入SqlSession为我们提供的一块区域中。
			该区域的结构是一个Map，当我们再次查询同样的数据时，mybatis会先去SqlSession中查询是否有该数据，
			如果有则直接取出数据，而不会再去数据库中查询数据
			当SqlSession被关闭后，SqlSession对象的缓存也就消失了
		 */


		// 清除SqlSession中的缓存：

		// 方法1：sqlSession.clearCache();
		//sqlSession.clearCache();

		// 方法2：关闭SqlSession，再重新打开一个SqlSession的方法，以此来清空SqlSession的缓存
		/*sqlSession.close();
		sqlSession = factory.openSession();
		IUserDao userDao2 = sqlSession.getMapper(IUserDao.class);*/

		User user2 = userDao.findById(60);
		System.out.println(user2);

		System.out.println(user1 == user2);
	}

	/**
	 * 测试一级缓存的同步
	 */

	@Test
	public void testUpdateUsername() {
		// 1. 查询用户信息
		User user1 = userDao.findById(60);
		System.out.println(user1);

		// 2. 更新用户
		user1.setUsername("coco");
		userDao.updateUsername(user1.getUsername());

		// 3. 再次查询用户信息
		User user2 = userDao.findById(60);
		System.out.println(user2);	// 可以查询到更新过的数据

		/*
		因为当我们调用了修改、添加、删除、commit()、close()等方法时，会自动清空SqlSession中的一级缓存
		所以上面我们在更新用户后了，就会触发mybatis自动清空SqlSession中的缓存，
		再次查询用户信息时，会获取到新的数据
		 */

		System.out.println(user1 == user2);

	}

	/**
	 * 测试二级缓存
	 */
	@Test
	public void testSecondLevelCache() {

		/*
		二级缓存:
			指的是mybatis中SqlSessionFactory对象的缓存。
			由同一个SqlSessionFactory对象创建的SqlSession对象，会共享SqlSessionFactory对象的缓存。
		 */

		// 使用factory创建一个SqlSession对象，然后查询数据
		SqlSession sqlSession1 = factory.openSession();
		IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
		User user1 = userDao1.findById(60);
		System.out.println(user1);
		sqlSession1.close();

		// 再使用factory创建一个SqlSession对象，然后查询相同的数据
		SqlSession sqlSession2 = factory.openSession();
		IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
		User user2 = userDao2.findById(60);
		System.out.println(user2);
		sqlSession2.close();

		/*
		开启二级缓存步骤：
			1. 让mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
			2. 让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
			3. 让当前的操作支持二级缓存（在IUserDao.xml中的select标签中配置）
		 */

		// 当开启了二级缓存后，通过控制台的输出可以看出，只向数据库中查询了一次，说明userDao2查询的数据，是从SqlSessionFactory对象的缓存中获取的
		// 如果没有开启二级缓存，则会向数据库中查询两次

		System.out.println(user1 == user2);				// false	==比较对象的地址
		//System.out.println(user1.equals(user2));		// true		equals比较对象中存储的内容
		// 既然是从SqlSessionFactory对象的缓存中获取的数据，那为什么两次的结果会是false呢
		// 因为SqlSessionFactory缓存中存储的只是数据，每次从SqlSessionFactory缓存中取出数据时，都会去创建一个新的对象，并把数据封装进去，然后返回新的对象
		// 所以每次返回的都不是同一个对象，只是对象中的数据相同而已，因此比对结果会是false
	}
}
