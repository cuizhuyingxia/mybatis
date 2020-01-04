package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/7 20:35
 * @Version 1.0
 */

/**
 * 用户的持久层接口
 */
public interface IUserDao {

	/**
	 * 查询所有操作
	 * @return
	 */
	// 查询注解
	@Select("select * from user")
	List<User> findAll();
}
