package com.greenacademy.websidepj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Schedule;
import com.greenacademy.websidepj.entity.Seat;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.repository.SeatRepository;
import com.greenacademy.websidepj.repository.TicketRepository;

@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public List<Seat> getAllSeats() {
		return seatRepository.findAll();
	}
	
	public List<Seat> getAllActiveSeats(Schedule schedule){
		List<Seat> activeSeats = new ArrayList<Seat>();
		List<Ticket> allTickets = ticketRepository.findAll();
		for (Ticket ticket : allTickets) {
			if (ticket.getSchedule() == schedule) activeSeats.add(ticket.getSeat());
		}
		return activeSeats;
	}
	
	public List<Seat> getAllInactiveSeats(Schedule schedule){
		List<Seat> inactiveSeats = new ArrayList<Seat>();
		List<Ticket> allTickets = ticketRepository.findAll();
		if (allTickets.size() == 0) {
			inactiveSeats = seatRepository.findAll();
		}
		else {
			boolean check = false;
			for (Seat seat : this.getAllSeats()) {
				for (Seat activeSeat : this.getAllActiveSeats(schedule)) {
					if (seat == activeSeat) check = true;
				}
				if (check == false) inactiveSeats.add(seat);
				check = false;
			}
		}
		return inactiveSeats;
	}
	
	public void createSeat() {
//		rowSum=8;
//		chairSum=20;
//		seatSum=8*20=160;
		Seat seat[]=new Seat[160];
		for (int k = 0; k < 160; k++) {
			seat[k] = new Seat();
		}
		int k=0;
		String chair=null;
		for (char i = 'A'; i <= 'H'; i++) {
			for (int j = 1; j <= 20; j++) {
				chair=""+i+j;
				seat[k].setChair(chair);
				seatRepository.save(seat[k]);
				k++;
			}
		}
	}

	public Seat getSeatById(Long seatId) {
		Optional<Seat> seat = seatRepository.findById(seatId);
		Seat s = seat.get();
		return s;
	}
	
	public Seat getSeatByChair(String chair) {
		for (Seat seat : this.getAllSeats()) {
			if (seat.getChair().equalsIgnoreCase(chair) == true) return seat;
		}
		return null;
	}
	
}
