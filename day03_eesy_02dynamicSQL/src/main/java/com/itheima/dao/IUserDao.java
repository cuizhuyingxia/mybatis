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
	 * 根据QueryVo（就是一个类，不过类里面有其它类的对象）中的查询条件，查询用户信息
	 * @param vo
	 * @return
	 */
	List<User> findUserByVo(QueryVo vo);

	/**
	 * 根据传入的参数条件查询用户
	 * @param user	插入的参数user对象中，有可能有用户名，又可能有性别，也又可能有地址，还有可能都没有
	 * @return
	 */
	List<User> findUserByCondition(User user);

	/**
	 * 根据QueryVo中的提供的id集合（就是有一个属性，是集合类型，用来存储id），查询用户列表
	 * @param vo
	 * @return
	 */
	List<User> findUserInIds(QueryVo vo);
}
