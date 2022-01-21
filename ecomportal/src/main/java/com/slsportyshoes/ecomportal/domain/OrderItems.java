package com.slsportyshoes.ecomportal.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemid;
	
	@OneToOne
	@JoinColumn(name="producid", referencedColumnName="id")
	private Product product;
	
	@OneToOne
	@JoinColumn(name="categoryid", referencedColumnName="categoryid")
	private ProductCategory category;
	
	private int qty;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name="orderid")
	@JsonBackReference
	private OrderHeader order;

	public OrderItems() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public OrderItems(Product product, ProductCategory cat, int qty) {
		this.product = product;
		this.category = cat;
		this.qty = qty;
	}

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public OrderHeader getOrder() {
		return order;
	}

	public void setOrder(OrderHeader order) {
		this.order = order;
	}
		
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "OrderItems [itemid=" + itemid + ", product=" + product + ", qty=" + qty + "]";
	}
	
	
}
