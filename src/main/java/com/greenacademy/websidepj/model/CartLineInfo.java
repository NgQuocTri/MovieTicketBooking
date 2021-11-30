package com.greenacademy.websidepj.model;


import com.greenacademy.websidepj.entity.Price;

public class CartLineInfo {

	private BookedTicketInfo bookedTicketInfo;
	private int quantity;
	private double discount = 0;
	private String erroMessage;

	Price price;

	public CartLineInfo(Price price) {
		this.quantity = 0;
		this.price = price;
	}

	public BookedTicketInfo getBookedTicketInfo() {
		return bookedTicketInfo;
	}

	public void setBookedTicketInfo(BookedTicketInfo bookedTicketInfo) {
		this.bookedTicketInfo = bookedTicketInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return (price.getUnitPrice() * this.quantity) ;
	}
	
	public double getAmountDiscount() {
		return (price.getUnitPrice() * this.quantity) - (price.getUnitPrice() * this.quantity * getDiscount() / 100);
	}

	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

}
