package com.yourautospa.cms.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourautospa.cms.entity.Customer;
import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Product;
import com.yourautospa.cms.entity.User;
import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.OrderService;
import com.yourautospa.cms.service.ProductService;
import com.yourautospa.cms.service.VehicleService;

@Controller
@RequestMapping("/greeter")
public class GreeterController {
	
	private User theGreeter;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String greeter(Model theModel) {
		Vehicle theVehicle = new Vehicle();
		theVehicle.setPlate("GA");

		theModel.addAttribute("vehicle", theVehicle);
		return "greeter/orderForm";
	}

	@PostMapping("/addCar")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle theVehicle, Model theModel) {
		String thePlate = theVehicle.getPlate();
		Order lastOrder = orderService.findFirstByPlateOrderByCreatedOnDesc(thePlate);
		String formatedLastDate;
		if(lastOrder == null) {
			formatedLastDate = "NO RECORD";
		}else {
			LocalDateTime lastDate= lastOrder.getCreatedOn();
			DateTimeFormatter formated = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			formatedLastDate = lastDate.format(formated);
		}
		
        
		if(thePlate.equals("GA")) {
			return "redirect:/greeter/order";
		}
		Vehicle tempVehicle = vehicleService.findOrAdd(thePlate);
		Product tempProduct = productService.findById(tempVehicle.getSubscription());
		
		Customer theCustomer;
		
		if(tempVehicle.getCustomer() == null) {
			theCustomer = new Customer();
		}else {
			theCustomer = tempVehicle.getCustomer();
		}
		
		List<Product> tunnelItems = productService.findBySubscriptionFalseAndWashTrueOrExtraTrue();
		Order theOrder = new Order();

		vehicleService.save(tempVehicle);
		theModel.addAttribute("lastDate", formatedLastDate);
		theModel.addAttribute(theCustomer);
		theModel.addAttribute(tempVehicle);
		theModel.addAttribute(tempProduct);
		theModel.addAttribute("tunnelItems", tunnelItems);
		theModel.addAttribute(theOrder);

		return "greeter/orderForm";

	}

	@PostMapping("/createOrder")
	public String createOrder(@ModelAttribute("order") Order theOrder, 
			@RequestParam(value = "orderItems", required = false) int[] orderItems, 
			BindingResult bindingResult, Model model) {
		
		if (orderItems != null) {
			Product product = null;
			for (int i = 0; i < orderItems.length; i++) {
				if (productService.existsById(orderItems[i])) {
					product = new Product();
					product.setId(orderItems[i]);
					theOrder.getProducts().add(product);

				}
			}
			
		}

		List<Product> theProducts = theOrder.getProducts();
		BigDecimal theTotal = new BigDecimal("0.00");
		for(Product tempProduct : theProducts) {
			Product product = productService.findById(tempProduct.getId());
			theTotal = theTotal.add(product.getPrice());
		}
		theOrder.setTotal(theTotal);
		orderService.save(theOrder);
		return "redirect:/greeter/order";

	}

	@GetMapping("/clear")
	public String clear(Model theModel) {
		return "redirect:/greeter/order";
	}
}