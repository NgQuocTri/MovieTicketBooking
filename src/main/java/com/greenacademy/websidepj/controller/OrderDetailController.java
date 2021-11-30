package com.greenacademy.websidepj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.OrderDetail;
import com.greenacademy.websidepj.service.OrderDetailService;

@Controller
@RequestMapping(value = "/home")
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	
	@RequestMapping(value = "/listOrderDetail", method=RequestMethod.GET)
	public ModelAndView getEditUserView() {
		List<OrderDetail> odList = orderDetailService.getAllOrderDetail();		
		ModelAndView model = new ModelAndView();
		model.setViewName("listOrderDetail");
		model.addObject("odList", odList);
		return model;
	}
	
	
	@RequestMapping(value = "/detailOrderDetail", method = RequestMethod.GET)
	public ModelAndView getDetailUser(@RequestParam("id") Long id) {

		OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);

		ModelAndView model = new ModelAndView();
		model.setViewName("detailOrder");
		model.addObject("orderDetail", orderDetail);
		return model;
	}

	@RequestMapping(value = "/editOrderDetail", method = RequestMethod.GET)
	public ModelAndView getEditUserView(@RequestParam("id") Long id) {
		OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);

		ModelAndView model = new ModelAndView();
		model.setViewName("editOrderDetail");
		model.addObject("orderDetail", orderDetail);
		return model;
	}

	
	@RequestMapping(value = "/saveOrderDetail", method = RequestMethod.POST)
	public ModelAndView saveOrder(OrderDetail newOrderDetail) {

		OrderDetail orderDetail = orderDetailService.updateOrderDetail(newOrderDetail);

		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		model.addObject("orderDetail", orderDetail);
		return model;
	}
	
	@RequestMapping(value="/saveDelOrderDetail", method= RequestMethod.GET)
	public ModelAndView saveDelUser(Long id) {
		orderDetailService.deleteOrderDetail(id);
		List<OrderDetail> odList = orderDetailService.getAllOrderDetail();
		// Save thanh cong & chuyen sang trang home
		ModelAndView model = new ModelAndView();
		model.setViewName("listOrderDetail");
		model.addObject("odList", odList);
		return model;
	}
	
}
