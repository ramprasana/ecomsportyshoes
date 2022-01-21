package com.slsportyshoes.ecomportal.service;

import java.util.Set;

import com.slsportyshoes.ecomportal.domain.ProductCategory;

public interface ProductCategoryService {
	public Set<ProductCategory> getProductCategory();
	public ProductCategory getProductCategoryById(String id);	
}
