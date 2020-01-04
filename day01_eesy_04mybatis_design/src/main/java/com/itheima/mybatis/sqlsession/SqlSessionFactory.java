package com.itheima.mybatis.sqlsession;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 15:59
 * @Version 1.0
 */
public interface SqlSessionFactory {

	/**
	 * 用于打开一个新的SqlSession对象
	 * @return
	 */
	SqlSession openSession();
}
