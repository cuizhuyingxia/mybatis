package com.itheima.mybatis.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 19:15
 * @Version 1.0
 */

import com.itheima.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 用于创建数据库连接的工具类
 */
public class DataSourceUtil {

	public static Connection getConnection(Configuration cfg) {
		try {
			// 1. 注册驱动
			Class.forName(cfg.getDriver());
			// 2. 创建连接
			Connection connection = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
