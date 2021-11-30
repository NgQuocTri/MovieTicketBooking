package com.greenacademy.websidepj.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Seat")
public class Seat {
	private Long seatId;
	private String chair;
	private List<Schedule> schedules;
	private List<Ticket> tickets;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Seat_Id", unique = true, nullable = false)
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}
	@Column(name="Chair")
	public String getChair() {
		return chair;
	}
	public void setChair(String chair) {
		this.chair = chair;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Schedule_Seat", joinColumns= {@JoinColumn(name="scheduleId")},
	inverseJoinColumns= {@JoinColumn(name="seatId")})
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	@OneToMany(mappedBy="seat")
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
