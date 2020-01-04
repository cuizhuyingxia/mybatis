package com.itheima.domain;

import java.io.Serializable;

/**
 * @Author Lian Flower
 * @Date 2019/9/10 18:04
 * @Version 1.0
 */
public class Account implements Serializable {

	private Integer id;
	private Integer uid;
	private Double money;

	// 因为account表和user表是多对一的关系
	// 也可以理解为account是从表，user是主表
	// 即Account是从表实体类，User是主表实体类
	/*
	所以如果我们需要查询所有账户信息，同时还要获取账户所属用户的信息，
	那我们就可以在从表实体类中引入一个主表实体类的对象，并提供getter和setter方法，
	这样我们就可以将查询到主表的信息封装到从表的实体类中
	 */
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", uid=" + uid +
				", money=" + money +
				'}';
	}
}
