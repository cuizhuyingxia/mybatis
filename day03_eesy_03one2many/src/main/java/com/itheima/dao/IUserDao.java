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
	 * 查询所有用户信息，和用户所属的账户信息，并封装到User中
	 * @return
	 */
	List<User> findUserAndAccount();
}
