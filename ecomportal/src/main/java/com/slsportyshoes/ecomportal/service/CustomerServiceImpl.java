package com.slsportyshoes.ecomportal.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.slsportyshoes.ecomportal.domain.Customer;
import com.slsportyshoes.ecomportal.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}


	@Override
	public Set<Customer> getCustomers() {
		Set<Customer> customerSet = new HashSet<>();
		
		customerRepository.findAll().iterator().forEachRemaining(customerSet::add);
		System.out.println(customerSet);
		return customerSet;
	}


	@Override
	public Customer findByFirstName(String firstName) {
		Customer customer = customerRepository.findByFirstName(firstName);
		return customer;
	}


	@Override
	public Customer findBycustomerid(Long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		
		if(!customerOptional.isPresent()) {
			throw new RuntimeException();
		}
		return customerOptional.get();
	}
	
	
	

}
