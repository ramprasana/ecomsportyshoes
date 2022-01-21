package com.slsportyshoes.ecomportal.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
	
	@NotNull
	@Size(min=2, max=30)
	private String userName;
	
	@NotNull
	@Min(5)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [userName=" + this.userName + ", password=" + this.password + "]";
	}
	
}
