package com.slsportyshoes.ecomportal.service;

import java.util.Set;

import com.slsportyshoes.ecomportal.domain.Customer;

public interface CustomerService {
	public Set<Customer> getCustomers();
	public Customer findByFirstName(String firstName);
	public Customer findBycustomerid(Long id);
}
