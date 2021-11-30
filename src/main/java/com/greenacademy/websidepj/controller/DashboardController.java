package com.greenacademy.websidepj.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenacademy.websidepj.entity.Permission;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public String getHomeView(HttpServletRequest request, Principal principal) {
		String userName = principal.getName();
		User user = userService.getByUserName(userName);
		if (user != null) {
			List<Permission> permissions = user.getPermissionList();
			for (Permission permission : permissions) {
				if (permission.getPermissionName().equalsIgnoreCase("admin")) {
					return "redirect:admin/userList";
				}
			}
		}
		return "redirect:/home";
	}
}
