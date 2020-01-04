package com.itheima.domain;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/9 15:32
 * @Version 1.0
 */
public class QueryVo {

	private User user;
	private List<Integer> ids;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
