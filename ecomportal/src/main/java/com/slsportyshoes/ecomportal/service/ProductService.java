package com.slsportyshoes.ecomportal.service;

import java.util.Set;

import com.slsportyshoes.ecomportal.domain.Product;

public interface ProductService {
	public Set<Product> getProducts();
	public Product getProductById(String id);
	public Product saveProduct(Product prod);
	public void deleteById(String id);
}
