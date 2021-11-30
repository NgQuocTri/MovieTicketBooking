package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenacademy.websidepj.entity.Feedback;
import com.greenacademy.websidepj.repository.FeedbackRepository;
import com.greenacademy.websidepj.service.FeedbackService;

@Controller
@RequestMapping(value="/admin")
public class FeedbackController {

	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private FeedbackService feedbackService;
	
	
	@RequestMapping(value="/feedbackList", method = RequestMethod.GET)
	public String feedbackListView(Model model) {
		List<Feedback> feedbacks = feedbackRepository.findAll();
		model.addAttribute("feedbacks",feedbacks);
		return "admin-feedbackList";
	}
	
	@RequestMapping(value="/deleteFeedback",method = RequestMethod.GET)
	public String deleteFeedback(@RequestParam("feedbackId") Long feedbackId,Model model) {
		feedbackService.deleteFeedback(feedbackId);
		List<Feedback> feedbacks = feedbackRepository.findAll();
		model.addAttribute("feedbacks",feedbacks);
		return "admin-feedbackList";
	}
	
	@RequestMapping(value = "/searchFeedback", method = RequestMethod.POST)
	public String searchFeedback(@RequestParam("search") String searchValue ,HttpServletRequest request, Model model) {
		List<Feedback> feedbacks = feedbackService.searchByCustomer(searchValue);
		model.addAttribute("search", true);
		model.addAttribute("feedbacks",feedbacks);
		model.addAttribute("searchValue", searchValue);
		return "admin-feedbackList";	
	}
}
