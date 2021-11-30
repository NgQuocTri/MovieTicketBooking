package com.greenacademy.websidepj.model;

import java.util.Date;
import java.util.List;

import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.entity.OrderDetail;
import com.greenacademy.websidepj.entity.Price;
import com.greenacademy.websidepj.entity.Schedule;
import com.greenacademy.websidepj.entity.Seat;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.entity.TicketCategory;

public class BookedTicketInfo {

	private Long ticketId;
	private Date issueDate;
	private int vat;

	private Seat seat;
	private List<OrderDetail> orderDetailList;
	private TicketCategory ticketCategory;
	private Movie movie;
	private Schedule schedule;

	private Price price;
	private int ticketAmount;

	public BookedTicketInfo() {

	}

	public BookedTicketInfo(Ticket ticket, Price price) {
		this.ticketId = ticket.getTicketId();
		this.issueDate = ticket.getIssueDate();
		this.vat = ticket.getVat();
		this.seat = ticket.getSeat();
		this.orderDetailList = ticket.getOrderDetailList();
		this.ticketCategory = ticket.getTicketCategory();
		this.movie = ticket.getMovie();
		this.schedule = ticket.getSchedule();
		this.price = price;
		this.ticketAmount = 1;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}

	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public int getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	/*
	 * public double getPrice_hour() { Price price =
	 * priceService.getPriceByTicketCategoryAndHour(schedule.getHour(),
	 * ticketCategory); return price.getUnitPrice();
	 */

}
