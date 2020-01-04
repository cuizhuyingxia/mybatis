package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 18:32
 * @Version 1.0
 */
public class MybatisAnnotationTest {

	/**
	 * 测试基于注解的mybatis使用
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// 1. 获取mybatis主配置文件的字节输入流
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2. 根据字节输入流构建SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 3. 根据SqlSessionFactory生产一个SqlSession
		SqlSession sqlSession = factory.openSession();
		// 4. 使用SqlSession获取Dao的代理对象
		IUserDao userDao = sqlSession.getMapper(IUserDao.class);
		// 5. 执行Dao的方法
		List<User> users = userDao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
		// 6. 释放资源
		sqlSession.close();
		inputStream.close();
	}
}
