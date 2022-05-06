package com.yourautospa.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourautospa.cms.entity.Customer;
import com.yourautospa.cms.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	public CustomerController(CustomerService theCustomerService) {
//		customerService = theCustomerService;
//	}
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> theCustomers = customerService.findAll();
		theModel.addAttribute("customers", theCustomers);
		
		return "customers/customer-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customers/customer-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("customerId")int theId,
							Model theModel){
		
		Customer theCustomer = customerService.findById(theId);
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customers/customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
	
	customerService.save(theCustomer);
	
	return "redirect:/customers/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId")int theId) {
		
	customerService.deleteById(theId);
	
	return "redirect:/customers/list";
	}

	
}
