package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 17:38
 * @Version 1.0
 */
public class UserDaoImpl implements IUserDao {
	private SqlSessionFactory factory;

	public UserDaoImpl(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<User> findAll() {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的selectList方法，查询所有			// 参数1：是能从Map集合中获取mapper对象的key
		List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findAll");
		// 3. 释放资源
		sqlSession.close();
		return users;
	}

	public void saveUser(User user) {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的insert方法，插入					// 参数1：是能从Map集合中获取mapper对象的key	参数2：sql语句需要的参数
		sqlSession.insert("com.itheima.dao.IUserDao.saveUser", user);
		// 3. 提交事务
		sqlSession.commit();
		// 4. 释放资源
		sqlSession.close();
	}

	public void updateUser(User user) {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的insert方法，更新
		sqlSession.update("com.itheima.dao.IUserDao.updateUser", user);
		// 3. 提交事务
		sqlSession.commit();
		// 4. 释放资源
		sqlSession.close();
	}

	public void deleteUser(Integer userId) {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的delete方法，删除
		sqlSession.delete("com.itheima.dao.IUserDao.deleteUser", userId);
		// 3. 提交事务
		sqlSession.commit();
		// 4. 释放资源
		sqlSession.close();
	}

	public User findById(Integer userId) {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的selectOne方法，查询单个结果
		User user = sqlSession.selectOne("com.itheima.dao.IUserDao.findById", userId);
		// 3. 释放资源
		sqlSession.close();
		return user;
	}

	public List<User> findByUsername(String username) {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的selectList方法，查询所有
		List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findByUsername", username);
		// 3. 释放资源
		sqlSession.close();
		return users;
	}

	public int findTotal() {
		// 1. 获取SqlSession对象
		SqlSession sqlSession = factory.openSession();
		// 2. 调用SqlSession的selectOne方法，查询单个结果
		Integer count = sqlSession.selectOne("com.itheima.dao.IUserDao.findTotal");
		// 3. 释放资源
		sqlSession.close();
		return count;
	}
}
