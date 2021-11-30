package com.greenacademy.websidepj.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Customer;
import com.greenacademy.websidepj.entity.Order;
import com.greenacademy.websidepj.entity.OrderDetail;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.model.CartInfo;
import com.greenacademy.websidepj.model.CartLineInfo;
import com.greenacademy.websidepj.model.CustomerInfo;
import com.greenacademy.websidepj.repository.CustomerRepository;
import com.greenacademy.websidepj.repository.OrderDetailRepository;
import com.greenacademy.websidepj.repository.OrderRepository;
import com.greenacademy.websidepj.repository.TicketRepository;
import com.greenacademy.websidepj.utils.Utils;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired 
	private TicketService ticketService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private TicketCategoryService ticketCategoryService;
	
	// xem thong tin
	public Order getOrderById(Long orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		Order o = order.get();
		return o;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	// tạo mới
	public Order createOrder(Order newOrder) {
		return orderRepository.save(newOrder);
	}

	// cập nhật
	public Order updateOrder(Order orderModel) {
		Optional<Order> order = orderRepository.findById(orderModel.getOrderId());
		Order orderEntity = order.get();
		orderEntity.setStatus(orderModel.getStatus());

		return orderRepository.save(orderEntity);
	}

	// xoa
	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}

	public Order addOrder(HttpServletRequest request, CartInfo cartInfo) {
		
		Order order = new Order();
		// save customer
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		Customer customer = new Customer();
		customer.setCustomerName(customerInfo.getCustomerName());
		customer.setCustomerAddress(customerInfo.getCustomerAddress());
		customer.setCustomerEmail(customerInfo.getCustomerEmail());
		customer.setCustomerPhone(customerInfo.getCustomerPhone());
		customerRepository.save(customer);

		java.util.Date today = new java.util.Date();
		order.setOrderDate(new java.sql.Date(today.getTime()));
		order.setTime(new java.util.Date(today.getTime()));
		order.setTotalPrice(cartInfo.getAmountTotal());
		order.setCustomer(customer);
		order.setOrderDetailQuantity(cartInfo.getQuantityTotal());
		orderRepository.save(order);

		List<CartLineInfo> lines = cartInfo.getCartLines();

		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setTotalPrice(line.getAmount());
			detail.setPrice(line.getBookedTicketInfo().getPrice().getUnitPrice());
			detail.setQuantity(line.getQuantity());
			detail.setDiscount(line.getDiscount());

			Ticket ticketInfo = Utils.getTicketInfoInSession(request);
			Ticket t = ticketService.newTicket(ticketInfo.getMovie().getMovieId(), ticketInfo.getSchedule().getScheduleId(),
					ticketInfo.getSeat().getSeatId(), ticketInfo.getTicketCategory().getCatId());
			
			
			Long code = t.getTicketId();
			Ticket ticket = this.ticketRepository.getOne(code);
			detail.setTicket(ticket);
			orderDetailRepository.save(detail);

			//product.setRemain(product.getProductNumber() - detail.getQuanity());
			//product.setProductNumber(product.getRemain());
		}

		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(order.getOrderId());
		return order;

	}
	
	public Order addOrderUser(HttpServletRequest request, CartInfo cartInfo) {
		
		Order order = new Order();
		// save customer
		User user = cartInfo.getUser();
		String name = user.getFirstName() + " " + user.getLastName();
		Customer customer = new Customer();
		customer.setCustomerName(name);
		customer.setCustomerAddress(user.getAddress());
		customer.setCustomerEmail(user.getEmail());
		customer.setCustomerPhone(user.getPhoneNumber());
		customer.setCustomerCode(user.getUserId());
		customerRepository.save(customer);
		
		java.util.Date today = new java.util.Date();
		order.setOrderDate(new java.sql.Date(today.getTime()));
		order.setTime(new java.util.Date(today.getTime()));
		order.setTotalPrice(cartInfo.getAmountTotal());
		order.setCustomer(customer);
		order.setUser(user);
		order.setOrderDetailQuantity(cartInfo.getQuantityTotal());
		orderRepository.save(order);

		List<CartLineInfo> lines = cartInfo.getCartLines();

		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setTotalPrice(line.getAmount());
			detail.setPrice(line.getBookedTicketInfo().getPrice().getUnitPrice());
			detail.setQuantity(line.getQuantity());
			detail.setDiscount(line.getDiscount());

			Ticket ticketInfo = Utils.getTicketInfoInSession(request);
			Ticket t = ticketService.newTicket(ticketInfo.getMovie().getMovieId(), ticketInfo.getSchedule().getScheduleId(),
					ticketInfo.getSeat().getSeatId(), ticketInfo.getTicketCategory().getCatId());
			
			
			Long code = t.getTicketId();
			Ticket ticket = this.ticketRepository.getOne(code);
			detail.setTicket(ticket);
			orderDetailRepository.save(detail);

			//product.setRemain(product.getProductNumber() - detail.getQuanity());
			//product.setProductNumber(product.getRemain());
		}

		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(order.getOrderId());
		return order;

	}

	// tim kiem
	public List<Order> searchByNameAndDate(String name, Date date){
		return orderRepository.searchByNameAndDate(name, date);
	}
	
	public void createDefaultOrder() {
		Order order = new Order();
		// save customer
		User user = userService.getByUserName("user");
		String name = user.getFirstName() + " " + user.getLastName();
		Customer customer = new Customer();
		customer.setCustomerName(name);
		customer.setCustomerAddress(user.getAddress());
		customer.setCustomerEmail(user.getEmail());
		customer.setCustomerPhone(user.getPhoneNumber());
		customer.setCustomerCode(user.getUserId());
		customerRepository.save(customer);
		
		java.util.Date today = new java.util.Date();
		order.setOrderDate(new java.sql.Date(today.getTime()));
		order.setTime(new java.util.Date(today.getTime()));
		order.setTotalPrice(50000);
		order.setCustomer(customer);
		order.setUser(user);
		order.setOrderDetailQuantity(1);
		orderRepository.save(order);

		OrderDetail detail = new OrderDetail();
		detail.setOrder(order);
		detail.setTotalPrice(50000);
		detail.setPrice(50000);
		detail.setQuantity(1);
		detail.setDiscount(0);
		
		Ticket t = new Ticket();
		t.setMovie(movieService.getMovieById((long) 1));
		t.setSchedule(scheduleService.getScheduleById((long) 1));
		t.setSeat(seatService.getSeatById((long) 1));
		t.setTicketCategory(ticketCategoryService.getTicketCategoryById((long) 1));
		
		Long code = t.getTicketId();
		Ticket ticket = this.ticketRepository.getOne(code);
		detail.setTicket(ticket);
		orderDetailRepository.save(detail);
	}
}
