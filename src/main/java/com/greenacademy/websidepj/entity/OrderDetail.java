package com.greenacademy.websidepj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greenacademy.websidepj.model.BookedTicketInfo;

@Entity
@Table(name = "Order_Details")
public class OrderDetail {

	private Long orderDetailId;
	private int quantity;
	private double price;
	private double totalPrice;
	private double discount;
	
	private Ticket ticket;
	private Order order;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", unique = true, nullable = false)
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORDER_ID", nullable = false)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TICKET_ID", nullable = false)
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@Column(name = "Quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}
	
	@Column(name = "Price", nullable = false)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
	
	@Column(name = "Total_Price")
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = takeTotalPrice();
	}
	public double takeTotalPrice() {
		return price * (1 - discount/100);
	}
	
	@Column(name = "Discount")
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void setTicketWithBookedTicketInfo(BookedTicketInfo bookedTicketInfo) {
		this.ticket.setIssueDate(bookedTicketInfo.getIssueDate());
		this.ticket.setVat(bookedTicketInfo.getVat());
		this.ticket.setSeat(bookedTicketInfo.getSeat());
		this.ticket.setOrderDetailList(bookedTicketInfo.getOrderDetailList());
		this.ticket.setTicketCategory(bookedTicketInfo.getTicketCategory());
		this.ticket.setMovie(bookedTicketInfo.getMovie());
		this.ticket.setSchedule(bookedTicketInfo.getSchedule());
	}
}
