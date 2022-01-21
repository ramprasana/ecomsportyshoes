package com.slsportyshoes.ecomportal.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slsportyshoes.ecomportal.domain.Customer;
import com.slsportyshoes.ecomportal.domain.OrderHeader;
import com.slsportyshoes.ecomportal.domain.OrderItems;
import com.slsportyshoes.ecomportal.domain.OrderReport;
import com.slsportyshoes.ecomportal.domain.Product;
import com.slsportyshoes.ecomportal.domain.ProductCategory;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired 
	ProductService prodService;
	
	@Autowired
	CustomerService custService;
	
	@Autowired
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<OrderReport> getOrderDetails(LocalDateTime orderDate, String categoryId) {
		
		HashSet orderReports = new HashSet<>();
		Product product = null;
		Customer customer = null;
		ProductCategory productCategory = null;
		
		Query query = em.createQuery("Select h from OrderHeader h join h.items i join i.product p join p.category c "
				+ "where h.orderdate <= :orderDate AND c.categoryid = :categoryId")
				.setParameter("categoryId", categoryId).setParameter("orderDate", orderDate);
		@SuppressWarnings("unchecked")
		List<OrderHeader> orderHeader = (List<OrderHeader>) query.getResultList();
		
		Set orderItems = new HashSet<OrderItems>();
		
		for(OrderHeader order : orderHeader) {
			System.out.print(order.getOrderid());
			System.out.println(" , " +order.getOrderdate());
			
			OrderReport ordRpt = new OrderReport();
			ordRpt.setOrderId(order.getOrderid());
			ordRpt.setOrderDate(order.getOrderdate());
			customer = custService.findBycustomerid(order.getCustomerid());
			ordRpt.setAdress1(customer.getAddress1());
			ordRpt.setCity(customer.getCity());
			ordRpt.setCustomerFName(customer.getFirstName());
			ordRpt.setCustomerLName(customer.getLastName());
			ordRpt.setCustomerId(customer.getCustomerid());	
			orderItems = order.getItems();
			for(Object item : orderItems) {
				OrderItems it = (OrderItems) item;
				product = it.getProduct();
				productCategory = it.getCategory();
				if(productCategory.getCategoryid().equalsIgnoreCase(categoryId)) {
					System.out.print(" , " + it.getQty());
					System.out.print(" , " + product.getId());
					System.out.print(" , " + product.getPrice());
					System.out.println(" , " + productCategory.getDescription());
					
					ordRpt.setQty(it.getQty());
					ordRpt.setProductId(product.getId());
					ordRpt.setProductDesc(product.getDescription());
					ordRpt.setCategoryDesc(productCategory.getDescription());
					
				}
				orderReports.add(ordRpt);
			}
		}		
		return orderReports;
		
	}

}
