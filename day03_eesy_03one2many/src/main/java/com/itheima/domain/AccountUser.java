package com.itheima.domain;

/**
 * @Author Lian Flower
 * @Date 2019/9/10 18:43
 * @Version 1.0
 */
public class AccountUser extends Account {

	private String username;
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return super.toString() + "             AccountUser{" +
				"username='" + username + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
