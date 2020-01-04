package com.itcast.dao;

/**
 * @Author Lian Flower
 * @Date 2019/12/18 19:36
 * @Version 1.0
 */

import com.itcast.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface IUserDao {

	/**
	 * 查询所有用户
	 * @return
	 */
	@Select("select * from user")
	List<User> findAll();
}
