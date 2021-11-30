package com.greenacademy.websidepj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="price")
public class Price {

	private Long priceId;
	private Long unitPrice;
	private int fromHour;
	private int toHour;
	private TicketCategory ticketCategory;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="priceId",unique=true,nullable=false)
	public Long getPriceId() {
		return priceId;
	}
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	@Column(name="unitPrice")
	public Long getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column(name="fromHour")
	public int getFromHour() {
		return fromHour;
	}
	public void setFromHour(int fromHour) {
		this.fromHour = fromHour;
	}
	
	@Column(name="toHour")
	public int getToHour() {
		return toHour;
	}
	public void setToHour(int toHour) {
		this.toHour = toHour;
	}
	
	@ManyToOne
	@JoinColumn(name="ticketCategory")
	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}
	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}
	
}
