package com.slsportyshoes.ecomportal.service;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.slsportyshoes.ecomportal.domain.User;
import com.slsportyshoes.ecomportal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
		
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public Set<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserAdmin(String username) {
		User user = findByUsername(username);
		if((user.getUsername().equalsIgnoreCase("admin")) && (user.getPassword().equalsIgnoreCase("manager")))
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public User save(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	
}
