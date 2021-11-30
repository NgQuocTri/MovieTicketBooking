package com.greenacademy.websidepj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.service.HomeService;

@Controller
public class LoginController {

	@Autowired
	HomeService homeService;
	
	@RequestMapping(value="/login")
	public String getLoginView() {
		return "login";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String getRegisterView(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,Model model) {
		String errorMessage = homeService.registerUser(user);
		model.addAttribute("errorMessage",errorMessage);
		if (errorMessage != null) {
			return "register";
		}
		return "redirect:/login";
	}
}
