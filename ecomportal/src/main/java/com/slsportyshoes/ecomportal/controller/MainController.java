package com.slsportyshoes.ecomportal.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.slsportyshoes.ecomportal.domain.ChangePasssForm;
import com.slsportyshoes.ecomportal.domain.Customer;
import com.slsportyshoes.ecomportal.domain.LoginForm;
import com.slsportyshoes.ecomportal.domain.OrderReport;
import com.slsportyshoes.ecomportal.domain.Product;
import com.slsportyshoes.ecomportal.domain.ProductCategory;
import com.slsportyshoes.ecomportal.domain.User;
//import com.slsportyshoes.ecomportal.domain.User;
//import com.slsportyshoes.ecomportal.repositories.UserRepository;
import com.slsportyshoes.ecomportal.service.CustomerService;
import com.slsportyshoes.ecomportal.service.OrderService;
import com.slsportyshoes.ecomportal.service.ProductCategoryService;
import com.slsportyshoes.ecomportal.service.ProductService;
import com.slsportyshoes.ecomportal.service.UserService;

@Controller
@SessionAttributes("loginuser")
public class MainController {

	private final UserService userService;
	private final CustomerService customerService;
	private final ProductService productService;
	private final ProductCategoryService productCategory;
	private final OrderService orderService;
	
	public MainController(UserService userService, CustomerService customerService, 
			ProductService productService, ProductCategoryService productCategory, OrderService orderService) {
		this.userService = userService;
		this.customerService = customerService;
		this.productService = productService;
		this.productCategory = productCategory;
		this.orderService = orderService;
	}

	@GetMapping({"","/","/login"})
	public String showLoginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping({"","/","/login"})
	public String validateLogInfo(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
		//model.addAttribute("user", loginForm.getUserName());
		model.addAttribute("loginuser",loginForm.getUserName());
		if(userService.isUserAdmin(loginForm.getUserName())) {
			return "home";
		} else {
			return "login";
		}
		
	}
	
	@GetMapping("/customer/list")
	public String showCustomer(Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("customers",customerService.getCustomers());
		return "customers/list";
	}
	
	@GetMapping("/customer/search")
	public String searchCustomer(Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("customer", new Customer());
		return "customers/search";
	}
	
	@PostMapping("/customer/searchresult")
	public String searchCustomerResult(Customer customer, Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("customer", customerService.findByFirstName(customer.getFirstName()));
		return "customers/show";
	}
	
	@GetMapping("/users/chgpass")
	public String changePassword(Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("message","");
		model.addAttribute("changpassword",new ChangePasssForm());
		return "/users/chgpass";
	}
	
//	@RequestMapping(value="/users/updateuser",params="submit",method=RequestMethod.POST)
	@PostMapping(value="/users/updateuser", params="submit")
	public String updateUser(@ModelAttribute ChangePasssForm changepassword,BindingResult bindingResult, Model model) {
		System.out.println(changepassword.getOldpassword());
		String loginUser = (String) model.getAttribute("loginuser");
		if(changepassword.getNewpassword1().equals(changepassword.getNewpassword2())) {
			User usertobeSaved = userService.findByUsername(loginUser);
			usertobeSaved.setPassword(changepassword.getNewpassword1());
			User saveduser = userService.save(usertobeSaved);
			model.addAttribute("message", saveduser.getUsername() + " password saved successfully");
		}
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("changpassword",new ChangePasssForm());
		return "/users/chgpass";
	}
	
	@PostMapping(value="/users/updateuser", params="cancel")
	public String cancelUser(@ModelAttribute ChangePasssForm changepassword,BindingResult bindingResult, Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("changpassword",new ChangePasssForm());
		return "/users/chgpass";
	}
	
	@GetMapping("/products/show")
	public String showProduct(Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("products", productService.getProducts());
		return "/product/list";
	}
	
	@GetMapping("product/{id}/show")
	public String showProductDetails(@PathVariable String id, Model model) {
		model.addAttribute("loginuser",model.getAttribute("loginuser"));
		model.addAttribute("product",productService.getProductById(id));
		return "/product/show";
	}
	
	@GetMapping("/product/{id}/update")
	public String updateProduct(@PathVariable String id, Model model) {
		model.addAttribute("loginuser",model.getAttribute("loginuser"));
		model.addAttribute("product",productService.getProductById(id));
		model.addAttribute("productcategoryes",productCategory.getProductCategory());
		return "/product/productform";
	}
	
	@PostMapping(value="product",params="submit")
	public String saveProduct(@ModelAttribute Product product) {
		System.out.println("Product --> " + product);
		Product savedProduct = productService.saveProduct(product);
		System.out.println("Saved Product - " + savedProduct.getId());
		return "redirect:/product/" + savedProduct.getId() +"/show";
	}

	@PostMapping(value="product",params="cancel")
	public String cancelProduct(@ModelAttribute Product product) {
//		Product product = productService.getProductById(null)
		System.out.println("Inside the saveProduct cancel action ");
		return "redirect:/products/show";
	}
	
	@PostMapping(value="product",params="new")
	public String newProduct(@ModelAttribute Product product) {
		System.out.println("Product --> " + product);
		Product savedProduct = productService.saveProduct(product);
		System.out.println("Saved Product - " + savedProduct.getId());
		return "redirect:/product/" + savedProduct.getId() +"/show";
	}
	
	
	@GetMapping("/product/new")
	public String newProduct(Model model) {
		model.addAttribute("loginuser",model.getAttribute("loginuser"));
		model.addAttribute("product", new Product());
		model.addAttribute("productcategoryes",productCategory.getProductCategory());
		return "/product/productformnew";
	}
	
	@GetMapping("/product/{id}/delete")
	public String deleteProduct(@PathVariable String id, Model model) {
		System.out.println("Product Deleted - " + id);
		productService.deleteById(id);
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("products", productService.getProducts());
		return "/product/list";		
	}
	
	@GetMapping("/report/purchase")
	public String searchOrder(Model model) {
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("productcategoryes",productCategory.getProductCategory());
		return "/orders/search";
	}
	
	@PostMapping(value="/orders/report",
				consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String reportOrders(@RequestParam(value="orderDate", required=false) String orderDate,
								@RequestParam(value="categoryId", required=false) String categoryId,
								Model model) {
//		System.out.println("Date - > " + order_date);
		System.out.println("Category Id -> " + categoryId);
		System.out.println("Order Date -> " + orderDate);
		LocalDateTime orderDat = LocalDateTime.now();
		model.addAttribute("loginuser", model.getAttribute("loginuser"));
		model.addAttribute("orders",orderService.getOrderDetails(orderDat, categoryId));
		return "/orders/report";
	}
}
