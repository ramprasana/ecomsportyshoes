package com.slsportyshoes.ecomportal.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.slsportyshoes.ecomportal.domain.ProductCategory;
import com.slsportyshoes.ecomportal.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private final ProductCategoryRepository prodCategoryRepository;
		
	public ProductCategoryServiceImpl(ProductCategoryRepository prodCategoryRepository) {
		this.prodCategoryRepository = prodCategoryRepository;
	}

	@Override
	public Set<ProductCategory> getProductCategory() {
		HashSet<ProductCategory> prodCategorySet = new HashSet<>();
		
		prodCategoryRepository.findAll().iterator().forEachRemaining(prodCategorySet::add);
		
		return prodCategorySet;
	}

	@Override
	public ProductCategory getProductCategoryById(String id) {
		Optional<ProductCategory> prodCategoryOptional = prodCategoryRepository.findById(id);
		
		if(!prodCategoryOptional.isPresent()) {
			throw new RuntimeException("Product Category not found");
		}
		
		return prodCategoryOptional.get();
	}

}
