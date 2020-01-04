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
	 * 查询所有用户信息，以及所属的角色信息
	 * @return
	 */
	List<User> findAll();

}
