package com.slsportyshoes.ecomportal.domain;

import java.time.LocalDateTime;

public class OrderReport {

	private Long orderId;
	private Long customerId;
	private String customerFName;
	private String customerLName;
	private String adress1;
	private String city;
	private LocalDateTime orderDate;
	private String productId;
	private String productDesc;
	private String categoryDesc;
	private int qty;
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryId) {
		this.categoryDesc = categoryId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long	 orderId) {
		this.orderId = orderId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerFName() {
		return customerFName;
	}
	public void setCustomerFName(String customerFName) {
		this.customerFName = customerFName;
	}
	public String getCustomerLName() {
		return customerLName;
	}
	public void setCustomerLName(String customerLName) {
		this.customerLName = customerLName;
	}
	public String getAdress1() {
		return adress1;
	}
	public void setAdress1(String adress1) {
		this.adress1 = adress1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	
}
