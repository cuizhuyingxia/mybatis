package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 18:15
 * @Version 1.0
 */

/**
 * 在mybatis中，针对CRUD操作的注解，一共有4个：@Select	@Insert	@Update @Delete
 */

/**
 * 开启二级缓存
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

	/**
	 * 查询所有用户，以及用户的账户信息
	 * @return
	 */
	@Select("select * from user")
	@Results(id = "userMap", value = {
			@Result(id = true, column = "id", property = "userId"),
			@Result(column = "username", property = "userName"),
			@Result(column = "address", property = "userAddress"),
			@Result(column = "sex", property = "userSex"),
			@Result(column = "birthday", property = "userBirthday"),
			@Result(property = "accounts", column = "id", many = @Many(select = "com.itheima.dao.IAccountDao.findAccountByUid", fetchType = FetchType.LAZY))
			/*	accounts为User实体类中引入的Account对象的集合		select用于指定使用哪个方法去查询用户对应的账户信息
				column用于告诉select通过user表的id去查询账户的信息		fetchType指定使用立即加载还是延时加载	*/
	})
	List<User> findAll();

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from user where id = #{id}")
	@ResultMap("userMap")
	User findUserById(Integer id);

	/**
	 * 根据username模糊查询
	 */
	@Select("select * from user where username like #{username}")
	@ResultMap("userMap")
	List<User> findByUsername(String username);


}
