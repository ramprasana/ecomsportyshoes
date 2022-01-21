package com.slsportyshoes.ecomportal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.slsportyshoes.ecomportal.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
