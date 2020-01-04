package com.itheima.mybatis.io;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 15:48
 * @Version 1.0
 */

import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的类
 */
public class Resources {

	/**
	 * 传入配置文件名，返回该配置文件的字节输入流
	 * @param filePath	mybatis主配置文件SqlMapConfig.xml
	 * @return
	 */
	public static InputStream getResourceAsStream(String filePath) {
		return Resources.class.getClassLoader().getResourceAsStream(filePath);
	}
}
