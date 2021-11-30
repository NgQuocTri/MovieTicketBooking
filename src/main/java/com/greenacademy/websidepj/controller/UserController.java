package com.greenacademy.websidepj.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Permission;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.model.PasswordChangeModel;
import com.greenacademy.websidepj.service.HomeService;
import com.greenacademy.websidepj.service.ImageService;
import com.greenacademy.websidepj.service.PermissionService;
import com.greenacademy.websidepj.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class UserController {
	
	@Autowired
	private HomeService homeService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView getNewUserView() {
		User user = new User();
		List<Permission> permissionList = permissionService.getAllPermissions();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("newUser");
		model.addObject("user", user);
		model.addObject("permissionList", permissionList);
		return model;
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUserView(@RequestParam("userId") Long userId) {
		User user = homeService.getUserDetail(userId);
		List<Permission> permissionList = permissionService.getAllPermissions();
		ModelAndView model = new ModelAndView();
		model.setViewName("editUser");
		model.addObject("user", user);
		model.addObject("permissionList", permissionList);
		return model;
	}
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView listUserView() {
		List<User> userList = homeService.getAllUsers();
		ModelAndView model = new ModelAndView();
		model.setViewName("userList");
		model.addObject("userList",userList);
		return model;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User userModel, HttpServletRequest request, Model model) {
		if (userModel.getImageFile() != null) {
			String uploadPath = request.getServletContext().getRealPath("uploads/user");
			String imageName = imageService.uploadFile(uploadPath, userModel.getImageFile());
			
			if (imageName != null && !imageName.isEmpty()) {
				userModel.setImageName(imageName);
			}
		}
		
		if (userModel.getUserId() == null) {
			String errorMessage = homeService.saveUser(userModel);
			
			if(errorMessage != null) {
				List<Permission> permissionList = permissionService.getAllPermissions();
				
				model.addAttribute("errorMessage", errorMessage);
				model.addAttribute("permissionList", permissionList);
				return "newUser";
			}
		}
		else {
			homeService.updateUser(userModel);	
		}
		
		return "redirect:/admin/userList";
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam("userId") Long id) {
		boolean result = homeService.deleteUser(id);
		ModelAndView model = new ModelAndView();
		List<User> userList = homeService.getAllUsers();
		
		if (result) {
			model.setViewName("userList");
			model.addObject("userList",userList);
		}
		return model;
	}
	
	@RequestMapping(value = "/viewUser", method = RequestMethod.GET)
	public ModelAndView viewUser(@RequestParam("userId") Long userId) {
		ModelAndView model = new ModelAndView();
		User user = homeService.getUserDetail(userId);
		model.setViewName("viewUser");
		model.addObject("user",user);
		return model;
	}
	
	@RequestMapping(value="/searchUser", method= RequestMethod.POST)
	public String searchUser(@RequestParam("search") String searchValue , HttpServletRequest request, Model model) {
		List<User> users = userService.searchByName(searchValue);	
		model.addAttribute("search", true);
		model.addAttribute("userList", users);
		model.addAttribute("searchValue", searchValue);
		return "userList";	
	}
	
	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public ModelAndView getUserProfileView(Principal principal) {
		String userName = principal.getName();
		User user = homeService.getUserByUserName(userName);
		ModelAndView model = new ModelAndView();
		model.setViewName("admin-userProfile");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value = "/userProfile/changePassword", method = RequestMethod.GET)
	public ModelAndView getChangedPasswordView1() {
		PasswordChangeModel changedPasswordModel = new PasswordChangeModel();
		ModelAndView model = new ModelAndView();
		model.setViewName("admin-changePassword");
		model.addObject("changedPasswordModel",changedPasswordModel);
		return model;
	}
	
	
	@RequestMapping(value = "/userProfile/changePassword", method = RequestMethod.POST)
	public String getChangedPasswordView2(@ModelAttribute("changedPasswordModel") PasswordChangeModel changedPasswordModel,Principal principal,Model model) {
		String userName = principal.getName();
		String error = userService.changePassword(userName, changedPasswordModel);
		if (error != null) {
			model.addAttribute("error",error);
			return "admin-changePassword";
		}
		User user = userService.getByUserName(userName);
		model.addAttribute("user", user);
		return "admin-userProfile";
	}
}
