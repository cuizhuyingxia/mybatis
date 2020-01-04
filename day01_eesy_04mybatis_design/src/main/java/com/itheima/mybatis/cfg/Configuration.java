package com.itheima.mybatis.cfg;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 17:05
 * @Version 1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Mybatis的配置类
 */
public class Configuration {

	private String driver;
	private String url;
	private String username;
	private String password;
	private Map<String,Mapper> mappers = new HashMap<String, Mapper>();

	public Map<String, Mapper> getMappers() {
		return mappers;
	}

	public void setMappers(Map<String, Mapper> mappers) {
		// 因为mybatis主配直文件中可能会有多个映射文件，所以需要使用追加的方式，封装每个映射文件中的配置信息
		// 如果使用this.mappers = mappers，那么后面封装的映射文件的信息会覆盖前面封装的映射文件的信息
		this.mappers.putAll(mappers);
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
