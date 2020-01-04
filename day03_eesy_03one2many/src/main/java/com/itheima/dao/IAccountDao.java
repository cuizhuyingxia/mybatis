package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/10 18:05
 * @Version 1.0
 */
public interface IAccountDao {

	/**
	 * 查询所有账户信息
	 * @return
	 */
	List<Account> findAll();

	/**
	 * 查询所有账户信息，同时还要获取账户所属用户的username和address
	 * @return
	 */
	List<AccountUser> findAccountUser();

	/**
	 * 查询所有账户信息，和账户所属的用户信息，并封装到Account中
	 * @return
	 */
	List<Account> findAccountAndUser();
}
