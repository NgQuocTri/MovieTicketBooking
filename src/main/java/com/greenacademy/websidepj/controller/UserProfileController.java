package com.greenacademy.websidepj.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.model.PasswordChangeModel;
import com.greenacademy.websidepj.service.HomeService;
import com.greenacademy.websidepj.service.ImageService;
import com.greenacademy.websidepj.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserProfileController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public ModelAndView getUserProfileView(Principal principal) {
		String userName = principal.getName();
		User user = homeService.getUserByUserName(userName);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-userProfile");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value="/editUser", method = RequestMethod.GET)
	public String getUserEditView(@RequestParam("userId") Long userId,Model model) {
		User user = userService.getByUserId(userId);
		model.addAttribute("user",user);
		return "user-editUser";
	}
	
	@RequestMapping(value="/saveEditedUser", method = RequestMethod.POST)
	public String saveEditedUser(@ModelAttribute("user") User userModel,Model model,HttpServletRequest request) {
		if (userModel.getImageFile() != null) {
			String uploadPath = request.getServletContext().getRealPath("uploads/user");
			String imageName = imageService.uploadFile(uploadPath, userModel.getImageFile());
			
			if (imageName != null && !imageName.isEmpty()) {
				userModel.setImageName(imageName);
			}
		}
		
		User user = homeService.updateUser(userModel);
		model.addAttribute("user",user);
		return "user-userProfile";
	}
	
	@RequestMapping(value = "/userProfile/changePassword", method = RequestMethod.GET)
	public ModelAndView getChangedPasswordView1() {
		PasswordChangeModel changedPasswordModel = new PasswordChangeModel();
		ModelAndView model = new ModelAndView();
		model.setViewName("user-changePassword");
		model.addObject("changedPasswordModel",changedPasswordModel);
		return model;
	}
	
	
	@RequestMapping(value = "/userProfile/changePassword", method = RequestMethod.POST)
	public String getChangedPasswordView2(@ModelAttribute("changedPasswordModel") PasswordChangeModel changedPasswordModel,Principal principal,Model model) {
		String userName = principal.getName();
		String error = userService.changePassword(userName, changedPasswordModel);
		if (error != null) {
			model.addAttribute("error",error);
			return "user-changePassword";
		}
		User user = userService.getByUserName(userName);
		model.addAttribute("user", user);
		return "user-userProfile";
	}
	
	@RequestMapping(value="/orderHistory", method = RequestMethod.GET)
	public String getOrderHistoryView(Principal principal,Model model) {
		User user = userService.getByUserName(principal.getName());
		model.addAttribute("user",user);
		return "orderHistory";
	}
}
