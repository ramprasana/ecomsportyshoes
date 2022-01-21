package com.slsportyshoes.ecomportal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.slsportyshoes.ecomportal.domain.OrderItems;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Long> {

}
