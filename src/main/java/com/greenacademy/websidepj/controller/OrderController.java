package com.greenacademy.websidepj.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Order;
import com.greenacademy.websidepj.service.OrderService;

@Controller
@RequestMapping(value = "/admin")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/listOrder", method=RequestMethod.GET)
	public ModelAndView getEditUserView() {
		List<Order> orderList = orderService.getAllOrders();		
		ModelAndView model = new ModelAndView();
		model.setViewName("listOrder");
		model.addObject("orderList", orderList);
		return model;
	}
	
	@RequestMapping(value = "/detailOrder", method = RequestMethod.GET)
	public ModelAndView getDetailUser(@RequestParam("orderId") Long orderId) {

		Order order = orderService.getOrderById(orderId);

		ModelAndView model = new ModelAndView();
		model.setViewName("detailOrder");
		model.addObject("order", order);
		return model;
	}

	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST) 
	public ModelAndView getCreateOrder(@ModelAttribute("order") Order newOrder) {
		orderService.createOrder(newOrder);
		List<Order> orderList = orderService.getAllOrders();
		ModelAndView model = new ModelAndView(); 
		model.setViewName("createOrder");
		model.addObject("orderList", orderList);
		return model; 
	}
	 
	@RequestMapping(value = "/editOrder", method = RequestMethod.GET)
	public ModelAndView getEditUserView(@RequestParam("orderId") Long orderId) {
		Order order = orderService.getOrderById(orderId);
		ModelAndView model = new ModelAndView();
		model.setViewName("editOrder");
		model.addObject("order", order);
		return model;
	}

	
	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	public ModelAndView saveOrder(@ModelAttribute("order") Order newOrder) {
		Order order = new Order();
		
		if (newOrder.getOrderId()==null) {
			order = orderService.createOrder(newOrder);
		}
		else {
			order = orderService.updateOrder(newOrder);
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("detailOrder");
		model.addObject("order", order);
		return model;
	}
	
	@RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
	public String searchOrder(@RequestParam("searchName") String searchName, 
							  @RequestParam("searchDate") Date searchDate, 
							  HttpServletRequest request, Model model) {
		List<Order> orderList = orderService.searchByNameAndDate(searchName,searchDate);
		model.addAttribute("search", true);
		model.addAttribute("orderList", orderList);
		model.addAttribute("searchName", searchName);
		model.addAttribute("searchDate", searchDate);
		return "listOrder";
	}
	
	@RequestMapping(value="/saveDelOrder", method= RequestMethod.GET)
	public ModelAndView saveDelUser(Long orderId) {
		orderService.deleteOrder(orderId);
		List<Order> orderList = orderService.getAllOrders();
		// Save thanh cong & chuyen sang trang home
		ModelAndView model = new ModelAndView();
		model.setViewName("listOrder");
		model.addObject("orderList", orderList);
		return model;
	}
	 
}
