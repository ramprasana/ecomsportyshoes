package com.slsportyshoes.ecomportal.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class OrderHeader {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long orderid;
	
	private Long customerid;
	private LocalDateTime orderdate;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(nullable=true)
	@JsonManagedReference
	private Set<OrderItems> items = new HashSet<>();

	/*
	 * public OrderHeader(Long orderid, Long customerid, LocalDateTime orderdate) {
	 * this.orderid = orderid; this.customerid = customerid; this.orderdate =
	 * orderdate; }
	 */

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public LocalDateTime getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDateTime orderdate) {
		this.orderdate = orderdate;
	}

	public Set<OrderItems> getItems() {
		return items;
	}

	public void setItems(Set<OrderItems> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderHeader [orderid=" + orderid + ", customerid=" + customerid + ", orderdate=" + orderdate
				 + "]";
	}
	
	public OrderHeader addItems(OrderItems item) {
		item.setOrder(this);
		this.items.add(item);
		return this;
	}
	
	
}
