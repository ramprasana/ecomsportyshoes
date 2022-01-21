package com.slsportyshoes.ecomportal.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slsportyshoes.ecomportal.domain.OrderHeader;
import com.slsportyshoes.ecomportal.domain.OrderReport;

public interface OrderService {
	public Set<OrderReport> getOrderDetails(LocalDateTime orderDate, String categoryId);
}
