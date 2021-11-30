package com.greenacademy.websidepj.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greenacademy.websidepj.entity.Permission;
import com.greenacademy.websidepj.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	public final static String ROLE_PREFIX = "ROLE_";// Spring Security 4
	public final static String PERMISSION_AUTHENTICATED = "AUTH";

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userService.getByUserName(userName);
		Collection<GrantedAuthority> authorities = new	ArrayList<GrantedAuthority>();

		try {
			if (user == null) {
				return null;
			}
			GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + PERMISSION_AUTHENTICATED);// required to login
			authorities.add(role);
			if (user.getPermissionList() != null && user.getPermissionList().size() > 0) {
				List<Permission> permissions = user.getPermissionList();
				for (Permission permission : permissions) {
					GrantedAuthority auth = new SimpleGrantedAuthority(ROLE_PREFIX + permission.getPermissionName());
					authorities.add(auth);
				}
			}
		} 
		catch (Exception e) {
			throw new UsernameNotFoundException("User not found.");
		}
		org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(userName, user.getPassword(), user.getIsActive(), true, true, true, authorities);
		return secUser;
	}
}
