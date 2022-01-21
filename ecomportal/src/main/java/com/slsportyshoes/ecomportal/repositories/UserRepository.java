package com.slsportyshoes.ecomportal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.slsportyshoes.ecomportal.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
		
	public User findByUsername(String username);
}
