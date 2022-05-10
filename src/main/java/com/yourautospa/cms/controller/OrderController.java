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

import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
//	public VehicleController(VehicleService theVehicleService) {
//		orderService = theVehicleService;
//	}
	
	@GetMapping("/list")
	public String listOrders(Model theModel) {
		
		List<Order> theOrders = orderService.findAll();
		theModel.addAttribute("orders", theOrders);
		
		return "orders/order-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Order theOrder = new Order();
		
		theModel.addAttribute("order", theOrder);
		
		return "orders/order-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("orderId")int theId,
							Model theModel){
		
		Order theOrder = orderService.findById(theId);
		
		theModel.addAttribute("order", theOrder);
		
		return "orders/order-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@ModelAttribute("order") Order theOrder) {
	
	orderService.save(theOrder);
	
	return "redirect:/orders/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("orderId")int theId) {
		
	orderService.deleteById(theId);
	
	return "redirect:/orders/list";
	}

	
}
