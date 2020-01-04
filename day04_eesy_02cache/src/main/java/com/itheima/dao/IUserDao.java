package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 10:42
 * @Version 1.0
 */
public interface IUserDao {

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findAll();

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	User findById(Integer userId);

	/**
	 * 更新用户名
	 * @param username
	 */
	void updateUsername(String username);
}
