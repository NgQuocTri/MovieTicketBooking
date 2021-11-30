package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.service.TicketService;

@Controller
@RequestMapping(value="/admin")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value = "/listTicket", method = RequestMethod.GET)
	public ModelAndView getTicketList() {
		List<Ticket> ticketList = ticketService.getAllTickets();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("listTicket");
		model.addObject("ticketList", ticketList);
		return model;
	}

	@RequestMapping(value = "/deleteTicket", method = RequestMethod.GET)
	public ModelAndView deleteTicketView(@RequestParam("ticketId") Long id) {
		boolean result = ticketService.deleteTicket(id);
		ModelAndView model = new ModelAndView();
		List<Ticket> ticketList = ticketService.getAllTickets();
		
		if (result) {
			model.setViewName("listTicket");
			model.addObject("ticketList", ticketList);
		}
		return model;
	}
	
	@RequestMapping(value = "/detailTicket", method = RequestMethod.GET)
	public String getTicketDetail(@RequestParam("ticketId") Long id,@RequestParam("orderId") Long orderId, Model model) {
		Ticket ticket = ticketService.getTicketDetail(id);
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("ticket", ticket);
		
		return "detailTicket";
	}
	
	@RequestMapping(value = "/searchTicket", method = RequestMethod.POST)
	public String searchTicket(@RequestParam("search") String searchValue ,HttpServletRequest request, Model model) {
		List<Ticket> ticketList = ticketService.searchByMovie(searchValue);
		model.addAttribute("ticketList", ticketList);
		model.addAttribute("search", true);
		model.addAttribute("searchValue", searchValue);
		return "listTicket";	
	}
}
