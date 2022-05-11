package com.yourautospa.cms.controller;

import java.util.ArrayList;
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
import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Product;
import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.OrderService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private OrderService orderService;	
	
	@GetMapping("/register")
	public String home(Model theModel) {
		//List<Order> theOrders = orderService.findAll();
		List<Order> theOrders = orderService.findAllByCompletedOnIsNullOrderByCreatedOn();
		Order theOrder = new Order();
		Customer theCustomer = new Customer();
		List<Product> orderProducts = new ArrayList<>();
		
		theModel.addAttribute(orderProducts);
		theModel.addAttribute(theCustomer);
		theModel.addAttribute("orders", theOrders);
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("currentOrder", theOrder);
		return "register/register";
	}
	
	@GetMapping("/findOrder")
	public String findOrder(@RequestParam("orderId")int theId,
							Model theModel){

		List<Order> theOrders = orderService.findAllByCompletedOnIsNullOrderByCreatedOn();
		Order theOrder = orderService.findById(theId);
		System.out.println("order"+theOrder.getTotal());
		List<Product> orderProducts = theOrder.getProducts();
		Vehicle theVehicle = theOrder.getVehicle();
		System.out.println("plate"+theVehicle.getPlate());
		Customer theCustomer;
				
		if(theVehicle.getCustomer() == null) {
			theCustomer = new Customer();
		}else {
			theCustomer = theVehicle.getCustomer();
		}
		
		for(Product product: orderProducts) {
			System.out.println(product.getName());
		}
		
		theModel.addAttribute("vehicle", theVehicle);
		theModel.addAttribute("orderProducts", orderProducts);
		theModel.addAttribute("customer",theCustomer);
		theModel.addAttribute("orders", theOrders);
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("currentOrder", theOrder);
		
		return "register/register";
	}
	
	@PostMapping("/completeOrder")
	public String saveVehicle(@ModelAttribute("currentOrder") Order currentOrder) {
		Order thisOrder = orderService.findById(currentOrder.getId());
		thisOrder.setPayment(currentOrder.getPayment());
		thisOrder.setAmountPaid(currentOrder.getAmountPaid());
		thisOrder.setCompletedOn();
		thisOrder.setComments(currentOrder.getComments());
		orderService.save(thisOrder);
	
	return "redirect:/register/register";
	
	}
	
	@GetMapping("/cancelOrder")
	public String cancel(@RequestParam("orderId")int theId) {
		
		
	orderService.deleteById(theId);
	
	return "redirect:/register/register";
	}
	

}
