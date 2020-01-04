package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/10 18:05
 * @Version 1.0
 */
public interface IAccountDao {

	/**
	 * 查询所有账户，以及账户所属用户的信息
	 * @return
	 */
	List<Account> findAll();

	/**
	 * 根据uid查询账户信息
	 * @param uid
	 * @return
	 */
	List<Account> findAccountByUid(Integer uid);
}
