package com.yourautospa.cms.controller;

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

import com.yourautospa.cms.entity.Order;
import com.yourautospa.cms.entity.Product;
import com.yourautospa.cms.entity.Vehicle;
import com.yourautospa.cms.service.OrderService;
import com.yourautospa.cms.service.ProductService;
import com.yourautospa.cms.service.VehicleService;

@Controller
@RequestMapping("/greeter")
public class GreeterController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/order")
	public String greeter(Model theModel) {
		Vehicle theVehicle = new Vehicle();
		Product theProduct = new Product();
		Order theOrder = new Order();
		List<Product> tunnelItems = productService.findBySubscriptionFalseAndWashTrueOrExtraTrue();
		theVehicle.setPlate("GA");

		theModel.addAttribute("vehicle", theVehicle);
		theModel.addAttribute("product", theProduct);
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("tunnelItems", tunnelItems);

		return "greeter/orderForm";
	}

	@PostMapping("/addCar")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle theVehicle, Model theModel) {
		String thePlate = theVehicle.getPlate();
		Vehicle tempVehicle = vehicleService.findOrAdd(thePlate);
		Product tempProduct = productService.findById(tempVehicle.getSubscription());
		List<Product> tunnelItems = productService.findBySubscriptionFalseAndWashTrueOrExtraTrue();
//		List<Product> theWashes = productService.findBySubscriptionFalseAndWashTrue();
//		List<Product> theExtras = productService.findByExtraTrue();
		Order theOrder = new Order();

		vehicleService.save(tempVehicle);
		theModel.addAttribute(tempVehicle);
		theModel.addAttribute(tempProduct);
//		theModel.addAttribute("products", theWashes);
//		theModel.addAttribute("extras", theExtras);
		theModel.addAttribute("tunnelItems", tunnelItems);
		theModel.addAttribute(theOrder);

		return "greeter/orderForm";

	}

	@PostMapping("/createOrder")
	public String createOrder(@ModelAttribute("order") Order theOrder, 
			@RequestParam(value = "orderItems", required = false) int[] orderItems, BindingResult bindingResult, Model model) {
		
		if (orderItems != null) {
			Product product = null;
			for (int i = 0; i < orderItems.length; i++) {
				if (productService.existsById(orderItems[i])) {
					product = new Product();
					product.setId(orderItems[i]);
					theOrder.getProducts().add(product);

				}
			}

			for (int i = 0; i < theOrder.getProducts().size(); i++) {
				System.out.println(theOrder.getProducts().get(i));
			}
		}
		
		orderService.save(theOrder);

//	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee,
//			@RequestParam(value = "cers", required = false) int[] cers, BindingResult bindingResult, Model model) {
//
//		if (cers != null) {
//			Certificate certificate = null;
//			for (int i = 0; i < cers.length; i++) {
//				if (certificateService.existsById(cers[i])) {
//					certificate = new Certificate();
//					certificate.setId(cers[i]);
//					theEmployee.getCertificates().add(certificate);
//
//				}
//			}
//
//			for (int i = 0; i < theEmployee.getCertificates().size(); i++) {
//				System.out.println(theEmployee.getCertificates().get(i));
//			}
//		}
//
//		employeeService.save(theEmployee);
//
//		return "redirect:/employees/list";
//		Product tempProduct = productService.findById(tempVehicle.getSubscription());
//		List<Product> theProducts = productService.findAll();
//		List<Extra> theExtras = extraService.findAll();
//		Order theOrder = new Order();
//		
//		
//	vehicleService.save(tempVehicle);
//	theModel.addAttribute(tempVehicle);
//	theModel.addAttribute(tempProduct);
//	theModel.addAttribute("products", theProducts);
//	theModel.addAttribute("extras", theExtras);
//	theModel.addAttribute(theOrder);

		return "redirect:/greeter/order";

	}

//	@PostMapping("/addCar")
//	public String addCar(@ModelAttribute("vehicle") Vehicle theVehicle, Model theModel) {
//
//		//String thePlate = theVehicle.getPlate();
//		
//		vehicleService.save(theVehicle);
//		
//		//Vehicle tempVehicle = vehicleService.findOrAdd(thePlate);
//
//		theModel.addAttribute("vehicle", theVehicle);
//
//		return "greeter";
//
//	}
	@GetMapping("/clear")
	public String clear(Model theModel) {
		return "redirect:/greeter/order";
//		Vehicle theVehicle = new Vehicle();
//		theVehicle.setPlate("GA");
//		
//		theModel.addAttribute("vehicle", theVehicle);
//
//		return "greeter/orderForm";
	}
}
