package com.slsportyshoes.ecomportal.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductCategory {

	@Id
	private String categoryid;
	private String description;
	
	@OneToMany(mappedBy="category")
	private Set<Product> product;
	
	
	public ProductCategory(String categoryid, String description) {
		super();
		this.categoryid = categoryid;
		this.description = description;
	}

	public ProductCategory() {
		super();
	}
	
	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductCategory [catgeoryid=" + categoryid + ", description=" + description
				+ "]";
	}
	
}
