package com.slsportyshoes.ecomportal.bootstrap;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.slsportyshoes.ecomportal.domain.Customer;
import com.slsportyshoes.ecomportal.domain.OrderHeader;
import com.slsportyshoes.ecomportal.domain.OrderItems;
import com.slsportyshoes.ecomportal.domain.Product;
import com.slsportyshoes.ecomportal.domain.ProductCategory;
import com.slsportyshoes.ecomportal.repositories.CustomerRepository;
import com.slsportyshoes.ecomportal.repositories.OrderHeaderRepository;
import com.slsportyshoes.ecomportal.repositories.OrderItemsRepository;
import com.slsportyshoes.ecomportal.repositories.ProductCategoryRepository;
import com.slsportyshoes.ecomportal.repositories.ProductRepository;
import com.slsportyshoes.ecomportal.service.CustomerService;
//import com.slsportyshoes.ecomportal.service.ProductCategoryService;
import com.slsportyshoes.ecomportal.service.ProductService;

@Component
public class OrderBootStrap implements ApplicationListener<ContextRefreshedEvent>{

	private final OrderHeaderRepository orderheaders;
	private final OrderItemsRepository orderItems;
	
	private final ProductRepository product;
	private final CustomerRepository customer;
	private final ProductCategoryRepository category;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
//	@Autowired
//	private ProductCategoryService productCategoryService;
	
	
	public OrderBootStrap(OrderHeaderRepository orderheaders, OrderItemsRepository orderItems, ProductRepository product, 
			CustomerRepository customer, ProductCategoryRepository category) {
		this.orderheaders = orderheaders;
		this.orderItems = orderItems;
		this.product = product;
		this.customer = customer;
		this.category = category; 
	}
	
	  public List<OrderHeader> getOrders() {
	  
		  List<OrderHeader> orders = new ArrayList<>(); 

//		  System.out.println(productService.getProducts());
//		  System.out.println(customerService.getCustomers());
		  
		  List<Product> product = new ArrayList<Product>(productService.getProducts());
		  List<Customer> customer = new ArrayList<Customer>(customerService.getCustomers());
//		  List<ProductCategory> category = new ArrayList<ProductCategory>(productCategoryService.getProductCategory());
		  
		  OrderHeader order = null;
		  Product prod = null;
		  ProductCategory cat = null;
		  
		  Random rand = new Random();
		  int count = 0;
		  int upperLimit = 15;
		  while(count < upperLimit) {
			  int int_random = rand.nextInt(15);
			  order = new OrderHeader();
			  order.setCustomerid((customer.get(int_random)).getCustomerid());
			  order.setOrderdate(LocalDateTime.now());
			  int itemcount = 0;
			  while(itemcount < 3) {
				  prod = product.get(int_random+itemcount);
				  cat = prod.getCategory();
				  order.addItems(new OrderItems(prod,cat,int_random+itemcount));
				  itemcount++;
			  }
			  			 
//			  order.addItems(new OrderItems((product.get(int_random+1)),int_random+1));
//			  order.addItems(new OrderItems((product.get(int_random+2)),int_random+1));
			  orders.add(order);
			  count++;
		  }
			 		  
		  return orders;
	  }
	 
	 

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("--------- Order -------");
		System.out.println(getOrders());
		System.out.println("--------- Order -------");
		orderheaders.saveAll(getOrders());
	}

}
