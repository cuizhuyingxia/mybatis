package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/11 18:15
 * @Version 1.0
 */

/**
 * 在mybatis中，针对CRUD操作的注解，一共有4个：@Select	@Insert	@Update @Delete
 */
public interface IUserDao {

	/**
	 * 查询所有用户
	 * @return
	 */
	@Select("select * from user")
	List<User> findAll();

	/**
	 * 保存用户
	 * @param user
	 */
	@Insert("insert into user(username, address, sex, birthday) values(#{username}, #{address}, #{sex}, #{birthday})")
	void saveUser(User user);

	/**
	 * 更新用户
	 * @param user
	 */
	@Update("update user set username = #{username} where id = #{id}")
	void updateUser(User user);

	/**
	 * 删除用户
	 * @param id
	 */
	@Delete("delete from user where id = #{id}")
	void deleteUser(Integer id);

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from user where id = #{id}")
	User findUserById(Integer id);

	/**
	 * 根据username模糊查询
	 */
	// 参数占位符方式
	@Select("select * from user where username like #{username}")
	// 字符串拼接方式
	// @Select("select * from user where username like '%${value}%'")
	List<User> findByUsername(String username);

	/**
	 * 查询总用户数
	 * @return
	 */
	@Select("select count(*) from user")
	Integer findTotalUser();
}
