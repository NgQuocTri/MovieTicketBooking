package com.greenacademy.websidepj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Permission;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.repository.PermissionRepository;
import com.greenacademy.websidepj.repository.UserRepository;

@Service
@Transactional
public class HomeService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public String registerUser(User userModel) {
		if (userModel.getUserName() == null || userModel.getUserName().isEmpty()) {
			return "Tên đăng nhập chưa được điền vào!";
		}
		if (userModel.getPassword() == null || userModel.getPassword().isEmpty()) {
			return "Mật khẩu chưa được điền vào!";
		}
		if (userModel.getFirstName() == null || userModel.getFirstName().isEmpty()) {
			return "Yêu cầu nhập tên!";
		}
		if (userModel.getLastName() == null || userModel.getLastName().isEmpty()) {
			return "Yêu cầu nhập họ!";
		}
		if (userSerivce.checkCoincide(userModel.getUserName()) == true) {
			return "Tên đăng nhập này đã được sử dụng, vui lòng nhập tên đăng nhập khác!";
		}
		if (userModel.getPhoneNumber() == null || userModel.getPhoneNumber().isEmpty()) {
			return "Vui lòng nhập số điện thoại!";
		}
		if (userModel.getEmail() == null || userModel.getEmail().isEmpty()) {
			return "Vui lòng nhập Email!";
		}
		
		List<Permission> permissions = new ArrayList<Permission>();
		Permission permission = permissionRepository.findByPermissionName("USER");
		if (permission == null) {
			permission = new Permission();
			permission.setPermissionName("USER");
			permissionRepository.save(permission);
		}
		permissions.add(permission);
		
		String encodedPassword = passwordEncoder.encode(userModel.getPassword());
		userModel.setPassword(encodedPassword);
		userModel.setIsActive(true);
		userModel.setPermissionList(permissions);
		userRepository.save(userModel);
		return null;
	}
	
	public User getUserDetail(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		User u = user.get();
		return u;
	}
	
	public User getUserByUserName(String name) {
		User user = userRepository.findByUserName(name);
		return user;
	}
	
	public String saveUser(User userModel) {
		String encodedPassword = passwordEncoder.encode("user");
		userModel.setPassword(encodedPassword);
		userModel.setIsActive(true);
		if (userModel.getUserName().isEmpty()) {
			return "Tên đăng nhập chưa được điền vào!";
		}
		if (userModel.getFirstName().isEmpty()) {
			return "Vui lòng điền tên";
		}
		if (userModel.getLastName().isEmpty()) {
			return "Vui lòng điền họ";
		}
		
		userRepository.save(userModel);
		return null;
	}
	
	public User updateUser(User userModel) {
		Optional<User> user = userRepository.findById(userModel.getUserId());
		User userEntity = user.get();
		userEntity.setUserId(userModel.getUserId());
		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());
		userEntity.setEmail(userModel.getEmail());
		userEntity.setAddress(userModel.getAddress());
		userEntity.setPhoneNumber(userModel.getPhoneNumber());
		userEntity.setImageName(userModel.getImageName());
		userRepository.save(userEntity);
		
		if(userModel.getPermissionList() != null) {
			List<Permission> permissionList = userModel.getPermissionList();
			userEntity.setPermissionList(permissionList);
		}
		
		return userEntity;
	}
	
	public boolean deleteUser(Long id) {
		boolean result = false;
		if (userRepository.findById(id) != null) {
			userRepository.deleteById(id);
			result = true;
		}
		return result;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
