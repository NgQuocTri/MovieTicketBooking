package com.greenacademy.websidepj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.service.UserService;

@Controller
public class ForgotPasswordController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword() {
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@RequestParam("userName") String userName, 
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email, Model model) {
		String error = userService.checkUserInfo(userName, phoneNumber, email);
		if (error != null) {
			model.addAttribute("errorMessage", error);
			return "forgotPassword";
		}
		model.addAttribute("userName", userName);
		return "changePassword";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView getChangePassword(ModelAndView model,
			@RequestParam("userName") String userName,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("retypePassword") String retypePassword) {
		String error = userService.changPassword(userName,newPassword, retypePassword);
		if (error != null) {
			model.addObject("errorMessagePass", error);
			model.setViewName("changePasswordError");
			return model;
		}
		model.setViewName("changePasswordSuccess");
		return model;
	}
}
