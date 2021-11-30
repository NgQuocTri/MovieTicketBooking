package com.greenacademy.websidepj.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.greenacademy.websidepj.model.CartLineInfo;
import com.greenacademy.websidepj.model.CustomerInfo;

@Entity
@Table(name = "Orders")
public class Order {

	private Long orderId;
	private Date orderDate;
	private double totalPrice;
	private java.util.Date time;
	private boolean status;
	private double orderDetailQuantity;

	private Customer customer;
	private User user;
	private List<OrderDetail> orderDetails;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "Order_Date", nullable = false)
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "totalPrice", nullable = false)
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "Time")
	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	@Column(name = "Status")
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name = "orderDetailQuantity")
	public double getOrderDetailQuantity() {
		return orderDetailQuantity;
	}

	public void setOrderDetailQuantity(double orderDetailQuantity) {
		this.orderDetailQuantity = orderDetailQuantity;
	}

	@OneToMany(mappedBy = "order")
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setListODWithCartLines(List<CartLineInfo> cartLines) {
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		int sizeOfCartLines = cartLines.size();
		OrderDetail orderDetail[] = new OrderDetail[sizeOfCartLines];
		for (int i = 0; i < sizeOfCartLines; i++) {
			orderDetail[i] = new OrderDetail();
		}
		int i = 0;
		for (CartLineInfo cartLine : cartLines) {
			orderDetail[i].setQuantity(cartLine.getQuantity());
			orderDetail[i].setPrice(cartLine.getBookedTicketInfo().getPrice().getUnitPrice());
			orderDetail[i].setTotalPrice(cartLine.getAmount());
			orderDetail[i].setDiscount(cartLine.getDiscount());
			orderDetail[i].setTicketWithBookedTicketInfo(cartLine.getBookedTicketInfo());
			orderDetails.add(orderDetail[i]);
			i++;
		}
		this.orderDetails = orderDetails;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		
		if (customer == null) {
			customer = new Customer ();			
		}
		this.customer.setCustomerName(customerInfo.getCustomerName());
		this.customer.setCustomerAddress(customerInfo.getCustomerAddress());
		this.customer.setCustomerEmail(customerInfo.getCustomerEmail());
		this.customer.setCustomerPhone(customerInfo.getCustomerPhone());
	}

	public void setCustomerInfo(User user) {
		
		if (customer == null) {
			customer = new Customer ();			
		}
		String name = user.getFirstName() + " " + user.getLastName();
		this.customer.setCustomerName(name);
		this.customer.setCustomerAddress(user.getAddress());
		this.customer.setCustomerEmail(user.getEmail());
		this.customer.setCustomerPhone(user.getPhoneNumber());
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
