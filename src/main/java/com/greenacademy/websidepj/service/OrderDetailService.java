package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Order;
import com.greenacademy.websidepj.entity.OrderDetail;
import com.greenacademy.websidepj.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderService orderService;
	
	// get id
	public OrderDetail getOrderDetailById(Long id) {
		Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
		return orderDetail.get();
	}
	
	public List<OrderDetail> getOrderDetailsByOrderId(Long orderId){
		Order order = orderService.getOrderById(orderId);
		return order.getOrderDetails();
	}
	
	// get all
	public List<OrderDetail> getAllOrderDetail(){
		return orderDetailRepository.findAll();
	}
	
	// thêm
	public OrderDetail createOrderDetail(OrderDetail newOrderDetail) {
		return orderDetailRepository.save(newOrderDetail);
	}
	
	//cập nhật
	public OrderDetail updateOrderDetail(OrderDetail newOrderDetail) {
		return orderDetailRepository.save(newOrderDetail);
	}
	
	//xoa
	public void deleteOrderDetail(Long id) {
		orderDetailRepository.deleteById(id);
	}
	
}
