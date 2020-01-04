package com.itheima.mybatis.cfg;

/**
 * @Author Lian Flower
 * @Date 2019/9/8 17:08
 * @Version 1.0
 */

/**
 * 用于封装执行的sql语句和返回结果的全限定类名
 */
public class Mapper {

	private String queryString;		// sql语句
	private String resultType;		// 返回结果实体类的全限定类名

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
}
