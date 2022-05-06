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

import com.yourautospa.cms.entity.Employee;
import com.yourautospa.cms.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
//	public EmployeeController(EmployeeService theEmployeeService) {
//		employeeService = theEmployeeService;
//	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		List<Employee> theEmployees = employeeService.findAll();
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/employee-list";
		
	}
	
	@GetMapping("/addForm")
	public String addForm(Model theModel){
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("employeeId")int theId,
							Model theModel){
		
		Employee theEmployee = employeeService.findById(theId);
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
	
	employeeService.save(theEmployee);
	
	return "redirect:/employees/list";
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int theId) {
		
	employeeService.deleteById(theId);
	
	return "redirect:/employees/list";
	}

	
}
