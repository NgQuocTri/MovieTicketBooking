package com.greenacademy.websidepj.entity;

import java.util.Date;
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

@Entity
@Table(name="Ticket")
public class Ticket {
	private Long ticketId;
	private Date issueDate;	
	private int vat;
	
	private Seat seat;
//	private OrderDetail orderDetail;
	private TicketCategory ticketCategory;
	private Movie movie;
	private Schedule schedule;
	
	private List<OrderDetail> orderDetailList;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Ticket_Id", unique = true, nullable = false)
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
	@Column(name="Issue_Date")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Column(name="VAT")
	public int getVat() {
		return vat;
	}
	public void setVat(int vat) {
		this.vat = vat;
	}
	
//	@ManyToOne
//	@JoinColumn(name="OrderDetail_Id")
//	public OrderDetail getOrderDetail() {
//		return orderDetail;
//	}
//	public void setOrderDetail(OrderDetail orderDetail) {
//		this.orderDetail = orderDetail;
//	}
	
	@ManyToOne
	@JoinColumn(name="TicketCategory_Id")
	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}
	public void setTicketCategory(TicketCategory TicketCat) {
		this.ticketCategory = TicketCat;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="movieInfo")
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@ManyToOne
	@JoinColumn(name="seat_id")
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	@OneToMany(mappedBy = "ticket")
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
	@ManyToOne
	@JoinColumn(name="schedule_Id")
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
}
