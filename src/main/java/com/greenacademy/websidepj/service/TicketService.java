package com.greenacademy.websidepj.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.repository.TicketRepository;
import com.greenacademy.websidepj.utils.Utils;

@Service
@Transactional
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private MovieService movieService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private TicketCategoryService ticketCategoryService;
	
	public List<Ticket> getAllTickets(){
		return ticketRepository.findAll();
	}
	
	public List<Ticket> searchByMovie(String movieName){
		return ticketRepository.searchByMovie(movieName);
	}
	
	public Ticket getTicketDetail(Long ticketId) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		Ticket t = ticket.get();
		return t;
	}
	
	public Ticket saveTicket(Ticket ticketModel) {
		Ticket ticketEntity = new Ticket();
		ticketEntity.setTicketCategory(ticketModel.getTicketCategory());
		//ticketEntity.setIssueDate(getDateNow());
		ticketEntity.setVat(ticketModel.getVat());
		ticketEntity.setMovie(ticketModel.getMovie());
		ticketEntity.setSeat(ticketModel.getSeat());
		ticketRepository.save(ticketEntity);
		
		return ticketEntity;
	}
	
	public Ticket updateTicket(Ticket ticketModel) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketModel.getTicketId());
		Ticket ticketEntity = ticket.get();
		ticketEntity.setTicketCategory(ticketModel.getTicketCategory());
		ticketEntity.setVat(ticketModel.getVat());
		ticketEntity.setMovie(ticketModel.getMovie());
		ticketRepository.save(ticketEntity);
		return ticketEntity;
	}
	
	public Ticket newTicket(Long movieId, Long scheduleId, Long seatId, Long catId) {
		Ticket ticketEntity = new Ticket();
		ticketEntity.setMovie(movieService.getMovieById(movieId));
		ticketEntity.setSchedule(scheduleService.getScheduleById(scheduleId));
		ticketEntity.setSeat(seatService.getSeatById(seatId));
		ticketEntity.setTicketCategory(ticketCategoryService.getTicketId(catId));
		ticketEntity.setIssueDate(getDateNow());
		ticketEntity.setVat(10);
		ticketRepository.save(ticketEntity);
		return ticketEntity;
	}
	
	public Ticket newBookTicket(HttpServletRequest request, Long movieId, Long scheduleId, Long seatId, Long catId) {
		
		
		Ticket ticketEntity = new Ticket();
		
		ticketEntity.setTicketId(movieId);
		
		ticketEntity.setMovie(movieService.getMovieById(movieId));
		ticketEntity.setSchedule(scheduleService.getScheduleById(scheduleId));
		ticketEntity.setSeat(seatService.getSeatById(seatId));
		ticketEntity.setTicketCategory(ticketCategoryService.getTicketId(catId));
		ticketEntity.setIssueDate(getDateNow());
		ticketEntity.setVat(10);
		/* ticketRepository.save(ticketEntity); */
		Utils.storeTicketInfoInSession(request, ticketEntity);
		return ticketEntity;
	}
	
	public boolean deleteTicket(Long id) {
		boolean result = false;
		if (ticketRepository.findById(id) != null) {
			ticketRepository.deleteById(id);
			result = true;
		}
		
		return result;
	}
	
	public void deleteSameTicket() {
		List<Ticket> allTickets1 = new ArrayList<Ticket>();
		List<Ticket> allTickets2 = new ArrayList<Ticket>();
		int check = 0;
		majorOuterloop:
		while(true) {
			allTickets1 = this.getAllTickets();
			allTickets2 = this.getAllTickets();
			subOuterloop:
			for (Ticket ticket1 : allTickets1) {
				check = 0;
				for (Ticket ticket2 : allTickets2) {
					if (ticket1 == this.getAllTickets().get(this.getAllTickets().size()-1)) break majorOuterloop;
					
					if (ticket1.getSchedule() == ticket2.getSchedule() && check < 2) check++;
					
					if (ticket1.getSchedule() == ticket2.getSchedule() && check == 2) {
						ticketRepository.delete(ticket2);
						break subOuterloop;
					}
				}
			}
		}
	}
	
	public Date getDateNow() {
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		return date;
	}
}
