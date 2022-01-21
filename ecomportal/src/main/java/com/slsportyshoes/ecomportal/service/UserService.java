package com.slsportyshoes.ecomportal.service;

import java.util.Set;

import com.slsportyshoes.ecomportal.domain.User;

public interface UserService {
	public User findByUsername(String username);
	public Set<User> getUsers();
	public boolean isUserAdmin(String username);
	public User save(User user);
}
