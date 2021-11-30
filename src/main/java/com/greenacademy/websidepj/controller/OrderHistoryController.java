package com.greenacademy.websidepj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenacademy.websidepj.entity.Order;
import com.greenacademy.websidepj.entity.OrderDetail;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.repository.UserRepository;
import com.greenacademy.websidepj.service.CustomerService;
import com.greenacademy.websidepj.service.OrderDetailService;

@Controller
@RequestMapping(value="/user")
public class OrderHistoryController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping(value="/orderHistoryList", method = RequestMethod.GET)
	public String getOrderHistoryView(Principal principal,Model model) {
		User user = userRepository.findByUserName(principal.getName());
		Long customerCode = user.getUserId();
		List<Order> orders = customerService.getOrdersByCustomerCode(customerCode);
		model.addAttribute("orders",orders);
		return "user-orderHistory";
	}
	
	@RequestMapping(value="/orderDetailList", method = RequestMethod.GET)
	public String getOrderDetailListView(@RequestParam("orderId") Long orderId,Model model) {
		List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
		model.addAttribute("orderDetails",orderDetails);
		model.addAttribute("orderId",orderId);
		return "user-orderDetailList";
	}
	
}
