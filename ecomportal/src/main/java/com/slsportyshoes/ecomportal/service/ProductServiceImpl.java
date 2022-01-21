package com.slsportyshoes.ecomportal.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.slsportyshoes.ecomportal.domain.Product;
import com.slsportyshoes.ecomportal.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Set<Product> getProducts() {
		Set<Product> productSet = new HashSet<>();
		productRepository.findAll().iterator().forEachRemaining(productSet::add);
		return productSet;
	}

	@Override
	public Product getProductById(String id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if(!productOptional.isPresent()) {
			throw new RuntimeException("Product not found");
		}
		return productOptional.get();
	}

	@Override
	@Transactional
	public Product saveProduct(Product prod) {
		Product savedProd = productRepository.save(prod);
		return savedProd;
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		productRepository.deleteById(id);		
	}
	
	

}
