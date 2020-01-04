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
import java.util.Date;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 19:04
 * @Version 1.0
 */
public class AnnotationCRUDTest {
	private InputStream inputStream;
	private SqlSessionFactory factory;
	private SqlSession sqlSession;
	private IUserDao userDao;

	@Before
	public void init() throws IOException {
		// 1. 获取mybatis主配置文件的字节输入流
		inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 根据字节输入流构建
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
	 * 保存用户
	 */
	@Test
	public void testSaveUser() {
		// 创建User对象
		User user = new User();
		user.setUsername("明月");
		user.setAddress("星空");
		user.setSex("1");
		user.setBirthday(new Date());
		// 保存
		userDao.saveUser(user);
	}

	/**
	 * 更新用户
	 */
	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setId(64);
		user.setUsername("月是故乡明");
		// 更新
		userDao.updateUser(user);
	}

	/**
	 * 删除用户
	 */
	@Test
	public void testDeleteUser() {
		userDao.deleteUser(53);
	}

	/**
	 * 根据id查询用户
	 */
	@Test
	public void testFindUserById() {
		User user = userDao.findUserById(64);
		System.out.println(user);
	}

	/**
	 * 根据username模糊查询
	 */
	@Test
	public void testFindByUsername() {
		List<User> users = userDao.findByUsername("%月%");
		// List<User> users = userDao.findByUsername("月");
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 查询总用户数
	 */
	@Test
	public void testFindTotalUser() {
		Integer totalUser = userDao.findTotalUser();
		System.out.println(totalUser);
	}
}
