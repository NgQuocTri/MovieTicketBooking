package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Customer;
import com.greenacademy.websidepj.service.CustomerService;

@Controller
@RequestMapping(value="/admin")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/customerList", method=RequestMethod.GET)
	public ModelAndView customerListView() {
		List<Customer> customers = customerService.getAllCustomers();
		ModelAndView model=new ModelAndView();
		model.setViewName("admin-customerList");
		model.addObject("customers",customers);
		return model;
	}
	
	@RequestMapping(value="/editCustomer", method = RequestMethod.GET)
	public String editCustomerView(@RequestParam("customerId") Long customerId,Model model) {
		Customer customer = customerService.getCustomerById(customerId);
		model.addAttribute("customer",customer);
		return "admin-editCustomer";
	}
	
	@RequestMapping(value="/editCustomer", method = RequestMethod.POST)
	public String saveEditedCustomer(@ModelAttribute("customer") Customer customer,Model model) {
		customerService.saveEditedCustomer(customer);
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers",customers);
		return "admin-customerList";
	}
	
	@RequestMapping(value="/deleteCustomer",method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam("customerId") Long customerId,Model model) {
		customerService.deleteCustomer(customerId);
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers",customers);
		return "admin-customerList";
	}
	
	@RequestMapping(value="/searchCustomer", method=RequestMethod.POST)
	public String searchCustomer(@RequestParam("searchName") String searchName, @RequestParam("searchPhone") String searchPhone, HttpServletRequest request, Model model) {
		List<Customer> customers=customerService.searchByNameAndPhone(searchName,searchPhone);

		model.addAttribute("search", true);
		model.addAttribute("customers",customers);
		model.addAttribute("searchName", searchName);
		model.addAttribute("searchPhone", searchPhone);
		return "customerList";
	}

}
