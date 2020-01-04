package com.itheima.dao;

import com.itheima.domain.QueryVo;
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
	 * 保存用户
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 更新用户
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 根据id删除用户
	 * @param userId
	 */
	void deleteUser(Integer userId);

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	User findById(Integer userId);

	/**
	 * 根据username模糊查询用户信息
	 * @param username
	 * @return
	 */
	List<User> findByUsername(String username);

	/**
	 * 查询总用户数
	 * @return
	 */
	int findTotal();

	/**
	 * 根据QueryVo（就是一个类，不过类里面有其它类的对象）中的查询条件，查询用户信息
	 * @param vo
	 * @return
	 */
	List<User> findUserByVo(QueryVo vo);

}
