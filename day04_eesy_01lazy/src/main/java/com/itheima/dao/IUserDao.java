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
	 * 查询所有用户，以及用户的账户信息
	 * @return
	 */
	List<User> findAll();

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);
}
