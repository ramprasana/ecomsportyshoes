package com.slsportyshoes.ecomportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.slsportyshoes.ecomportal.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//public interface CustomerRepository extends CrudRepository<Customer, Long> {
	public Customer findByFirstName(String firstName);
}
