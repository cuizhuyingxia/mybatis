package com.itheima.mybatis.sqlsession.proxy;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 18:41
 * @Version 1.0
 */
public class MapperProxy implements InvocationHandler {
	// mappers集合的key是：接口全限定类名 + 方法名		value为：Mapper对象，存储sql语句和返回值的全限定类名
	private Map<String,Mapper> mappers;
	private Connection conn;

	public MapperProxy(Map<String,Mapper> mappers, Connection conn) {
		this.mappers = mappers;
		this.conn = conn;
	}
	/**
	 * 用于对方法进行增强的，这里我们的增强其实就是调用selectList方法
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 1. 获取执行的方法名
		String methodName = method.getName();
		// 2. 获取方法所在类的名称（全类名）
		String className = method.getDeclaringClass().getName();
		// 3. 组合key
		String key = className+"."+methodName;
		// 4. 获取mappers中的Mapper对象
		Mapper mapper = mappers.get(key);
		// 5. 判断是否有mapper
		if (mapper == null) {
			throw new IllegalArgumentException("getMapper()的参数传入有误");
		}
		// 6. 调用工具类，执行查询所有
		return new Executor().selectList(mapper, conn);
	}
}
