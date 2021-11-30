package com.greenacademy.websidepj.entity;

import java.sql.Date;
import java.time.LocalTime;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="schedule")
public class Schedule {
	private Long scheduleId;
	private Date date;
	private String hour;
	private String transientStatus;
	private Movie movie;
	private List<Seat> seats;
	private List<Ticket> tickets;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="scheduleId",unique=true,nullable=false)
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="hour")
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String calculateStatus() {
		java.util.Date utilToday = new java.util.Date();
		java.sql.Date today = new java.sql.Date(utilToday.getYear(),utilToday.getMonth(),utilToday.getDate());

		if(this.date.after(today)) return "Chưa chiếu";
		if(this.date.before(today)) return "Đã chiếu";
		
		String hStr=this.hour.split(":")[0];
		int h=Integer.parseInt(hStr);
		String mStr=this.hour.split(":")[1];
		int m=Integer.parseInt(mStr);
		int movieScheduleSeconds=h*3600+m*60;
		
		LocalTime now=java.time.LocalTime.now();
		int nowSeconds=now.getHour()*3600+now.getMinute()*60+now.getSecond();
		
		int secondsDifference=movieScheduleSeconds-nowSeconds;
		if(secondsDifference>=0 && secondsDifference<=(10*60)) return "Chuẩn bị chiếu";
		if(secondsDifference>=-(2*3600) && secondsDifference<0) return "Đang chiếu";
		if(secondsDifference<-(2*3600)) return "Đã chiếu";
		return "Chưa chiếu";
	}
	
	@Transient
	public String getTransientStatus() {
		setTransientStatus();
		return transientStatus;
	}
	public void setTransientStatus() {
		this.transientStatus=calculateStatus();
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="movieId")
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Schedule_Seat", joinColumns= {@JoinColumn(name="scheduleId")},
	inverseJoinColumns= {@JoinColumn(name="seatId")})
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	@OneToMany(mappedBy="schedule")
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
