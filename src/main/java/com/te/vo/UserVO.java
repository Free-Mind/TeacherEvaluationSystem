package com.te.vo;

/**
 * 
 * @author LichKing
 * 登录时前后台交互的数据类型
 */
public class UserVO {

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;
	
	private String password;
	
}
