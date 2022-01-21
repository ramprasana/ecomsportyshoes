package com.slsportyshoes.ecomportal.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String description;
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="categoryid", nullable=false)
	private ProductCategory category;
	
	
	public ProductCategory getCategory() {
		// return this.category.getDescription();
		return this.category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Product() {
		super();
	}
		
	public Product(String id, String description, BigDecimal price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", price=" + price + "]";
	}

	
	
}
