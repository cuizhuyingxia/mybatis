package com.itheima.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 10:39
 * @Version 1.0
 */
public class User implements Serializable {

	private Integer id;
	private String username;
	private String address;
	private String sex;
	private Date birthday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", address='" + address + '\'' +
				", sex='" + sex + '\'' +
				", birthday=" + birthday +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(username, user.username) &&
				Objects.equals(address, user.address) &&
				Objects.equals(sex, user.sex) &&
				Objects.equals(birthday, user.birthday);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, username, address, sex, birthday);
	}
}
