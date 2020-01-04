package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/10 21:01
 * @Version 1.0
 */
public interface IRoleDao {

	/**
	 * 查询所有角色信息，以及所属的用户信息
	 * @return
	 */
	List<Role> findAll();


}
