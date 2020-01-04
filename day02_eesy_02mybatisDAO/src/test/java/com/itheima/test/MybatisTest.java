package com.itheima.test;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 11:00
 * @Version 1.0
 */

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
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
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的CRUD方法
 */
public class MybatisTest {
	private InputStream inputStream;
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
		// 3. 使用工厂对象创建dao接口的实现类对象
		userDao = new UserDaoImpl(factory);
	}

	/**
	 * 释放资源
	 * @throws IOException
	 */
	@After
	public void destroy() throws IOException {
		inputStream.close();
	}

	/**
	 * 查询所有用户
	 */
	@Test
	public void testFindAll() throws IOException {
		// 执行查询所有用户方法
		List<User> users = userDao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 保存用户
	 */
	@Test
	public void testSaveUser() {
		// 创建user对象
		User user = new User();
		user.setUsername("枫叶");
		user.setAddress("一方净土");
		user.setSex("男");
		user.setBirthday(new Date());
		System.out.println(user);
		// 执行保存用户方法
		userDao.saveUser(user);
		// 打印刚插入的这条记录的id
		System.out.println(user);
	}

	/**
	 * 更新用户
	 */
	@Test
	public void testUpdateUser() {
		// 创建user对象
		User user = new User();
		user.setId(52);
		user.setAddress("北京");
		user.setSex("9");
		// 执行更新用户方法
		userDao.updateUser(user);
	}

	/**
	 * 根据id删除用户
	 */
	@Test
	public void deleteUser() {
		// 执行删除用户方法
		userDao.deleteUser(52);
	}

	/**
	 * 根据id查询用户
	 */
	@Test
	public void testFindById() {
		User user = userDao.findById(57);
		System.out.println(user);
	}

	/**
	 * 根据username模糊查询用户信息
	 */
	@Test
	public void testFindByUsername() {
		List<User> users = userDao.findByUsername("%猫%");
		// List<User> users = userDao.findByUsername("猫");
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 查询总用户数
	 */
	@Test
	public void testFindTotal() {
		int total = userDao.findTotal();
		System.out.println(total);
	}

}
