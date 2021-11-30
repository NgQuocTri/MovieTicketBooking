package com.greenacademy.websidepj.model;

import java.util.ArrayList;
import java.util.List;

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.entity.User;

public class CartInfo {
	private CustomerInfo customerInfo;
	private Long orderNum;
	private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
	private User user;
	
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
		
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}
	
	private CartLineInfo findLineById(Long ticketId) {
		for(CartLineInfo cartLineInfo : this.cartLines) {
			if(cartLineInfo.getBookedTicketInfo().getTicketId().equals(ticketId)) {
				return cartLineInfo;
			}
		}
		return null;
	}
	
	public void addBookedTicket(BookedTicketInfo bookedTicketInfo,int quantity) {
		CartLineInfo cartLineInfo=this.findLineById(bookedTicketInfo.getTicketId());
		if(cartLineInfo==null) {
			cartLineInfo=new CartLineInfo(bookedTicketInfo.getPrice());
			cartLineInfo.setBookedTicketInfo(bookedTicketInfo);
			this.cartLines.add(cartLineInfo);
		}
		else {
			cartLineInfo.setBookedTicketInfo(bookedTicketInfo);
			//this.cartLines.add(cartLineInfo);
		}
		int newQuantity=cartLineInfo.getQuantity() + quantity;
		if(newQuantity<=0) {
			this.cartLines.remove(cartLineInfo);
		}
		else {
			if(newQuantity <= cartLineInfo.getBookedTicketInfo().getTicketAmount()) {
				newQuantity=newQuantity+0;
			}
			else {
				newQuantity=cartLineInfo.getBookedTicketInfo().getTicketAmount();
			}
			cartLineInfo.setQuantity(newQuantity);
		}
	}
	
	public void removeBookedTicket(BookedTicketInfo bookedTicketInfo) {
		CartLineInfo cartLineInfo=this.findLineById(bookedTicketInfo.getTicketId());
		if(cartLineInfo != null) {
			this.cartLines.remove(cartLineInfo);
		}
	}
	
	public String updateBookedTicket(Long ticketId,int quantity) {
		CartLineInfo cartLine=this.findLineById(ticketId);
		String errorMessage="";
		if(cartLine != null) {
			if(quantity <= 0) {
				this.cartLines.remove(cartLine);
			}
			if(quantity > cartLine.getBookedTicketInfo().getTicketAmount()) {
				errorMessage="Ticket amounts of"+cartLine.getBookedTicketInfo().getMovie().getMovieName()+" is not available";
				cartLine.setErroMessage(errorMessage);
				return errorMessage;
			}
			else {
				cartLine.setQuantity(quantity);
				cartLine.setErroMessage(null);
			}
		}
		return null;
	}
	
	public void updateQuantity(CartInfo cartForm) {
		if(cartForm!=null) {
			List<CartLineInfo> cartLines=this.getCartLines();
			for(CartLineInfo cartLine : cartLines){
				this.updateBookedTicket(cartLine.getBookedTicketInfo().getTicketId(), cartLine.getQuantity());
			}
		}
	}

	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}
	
	public void setDiscountToIndex(Discount discount) {	
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		java.sql.Date datesql = new java.sql.Date(date.getTime());
		
		for(CartLineInfo cartLine : this.cartLines) {
			if(!discount.getStartDate().after(datesql) && !discount.getEndDate().before(datesql)) {
				cartLine.setDiscount(discount.getPercentage());
			}		
		}
	}
	
	public int getQuantityTotal() {
		int quantity=0;
		for(CartLineInfo cartLine : this.cartLines) {
			quantity+=cartLine.getQuantity();
		}
		return quantity;
	}
		
	public double getAmountTotal() {
		double amount=0;
		for(CartLineInfo cartLine : this.cartLines) {
			
			amount+=cartLine.getAmount();
		}
		return amount;
	}
	
	public double getAmountDiscountTotal() {
		double amount=0;
		for(CartLineInfo cartLine : this.cartLines) {
			
			amount+=cartLine.getAmountDiscount();
		}
		return amount;
	}
	
	public double getDiscount() {
		double discount=0;
		for(CartLineInfo cartLine : this.cartLines) {
			discount =cartLine.getDiscount();
			break;
		}
		return discount;
	}
}
