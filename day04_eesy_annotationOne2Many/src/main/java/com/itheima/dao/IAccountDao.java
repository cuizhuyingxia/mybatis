package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 20:10
 * @Version 1.0
 */
public interface IAccountDao {

	/**
	 * 查询所有账户，以及账户所属的用户信息
	 * @return
	 */
	@Select("select * from account")
	@Results(id = "accountMap", value = {
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "uid", property = "uid"),
			@Result(column = "uid", property = "uid"),
			@Result(property = "user", column = "uid", one = @One(select = "com.itheima.dao.IUserDao.findUserById", fetchType = FetchType.EAGER))
			/*	user为Account实体类中引入的User对象	select用于指定使用哪个方法去查询账户对应的用户信息
				column用于告诉select通过account表的uid去查询用户的信息		fetchType指定使用立即加载还是延时加载	*/
	})
	List<Account> findAll();

	/**
	 * 根据用户id查询账户信息
	 * @param uid
	 * @return
	 */
	@Select("select * from account where uid = #{uid}")
	List<Account> findAccountByUid(Integer uid);
}
