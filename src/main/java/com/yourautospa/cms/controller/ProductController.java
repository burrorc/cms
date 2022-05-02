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

import com.yourautospa.cms.entity.Product;
import com.yourautospa.cms.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	public VehicleController(VehicleService theVehicleService) {
//		productService = theVehicleService;
//	}
	
	@GetMapping("/list")
	public String listProductes(Model theModel) {
		
		List<Product> theProducts = productService.findAll();
		theModel.addAttribute("products", theProducts);
		
		return "products/product-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Product theProduct = new Product();
		
		theModel.addAttribute("product", theProduct);
		
		return "products/product-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("productId")int theId,
							Model theModel){
		
		Product theProduct = productService.findById(theId);
		
		theModel.addAttribute("product", theProduct);
		
		return "products/product-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@ModelAttribute("product") Product theProduct) {
	
	productService.save(theProduct);
	
	return "redirect:/products/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("productId")int theId) {
		
	productService.deleteById(theId);
	
	return "redirect:/products/list";
	}

	
}
