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
import com.greenacademy.websidepj.model.PasswordChangeModel;
import com.greenacademy.websidepj.repository.PermissionRepository;
import com.greenacademy.websidepj.repository.UserRepository;

@Transactional
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private static final String DEFAULT_INITIAL_PASSWORD = "admin";

	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User getByUserId(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.get();
	}
	
	public boolean checkCoincide(String userName) {
		User user = this.getByUserName(userName);
		if (user != null) return true;
		return false;
	}

	public void createDefaultAdmin() throws Exception {
		// TODO create all groups and permissions
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		
		// creation of the super admin admin:admin)
		List<Permission> permissionList = new ArrayList<Permission>();
		Permission permission = new Permission();
		permission.setPermissionName("ADMIN");
		permissionRepository.save(permission);
		permissionList.add(permission);
		
		User user = new User();
		user.setUserName("admin");
		user.setPassword(password);
		user.setEmail("admin@greenacedamy.com");
		user.setFirstName("Administrator");
		user.setLastName("user");
		user.setImageName("admin.jpg");
		user.setPhoneNumber("0355803075");
		user.setAddress("59 B???ch ?????ng, Qu???n 1, TP HCM");
		user.setIsActive(true);
		user.setPermissionList(permissionList);
		
		userRepository.save(user);
	}
	
	public void createDefaultUser() {
		String password = passwordEncoder.encode("user");
		List<Permission> permissions=new ArrayList<Permission>();
		Permission permission=new Permission();
		permission.setPermissionName("USER");
		permissionRepository.save(permission);
		permissions.add(permission);
		
		User user=new User();
		user.setUserName("user");
		user.setPassword(password);
		user.setEmail("abc@greenAcademy.com");
		user.setFirstName("Binh");
		user.setLastName("Hoa");
		user.setImageName("Thanh Binh.jpg");
		user.setPhoneNumber("0455804075");
		user.setAddress("307/44 V??nh Vi???n, Qu???n 10, TP HCM");
		user.setIsActive(true);
		user.setPermissionList(permissions);
		userRepository.save(user);
	}
	
	public List<User> searchByName(String name){
		return userRepository.searchByName(name);
	}
			
	public String changePassword(String userName, PasswordChangeModel model) {
		if (model.getOldPassword() == null || model.getOldPassword().isEmpty()) return "M???t kh???u c?? ch??a ???????c ??i???n v??o!";
		if (model.getNewPassword1() == null || model.getNewPassword1().isEmpty()) return "M???t kh???u m???i 1 ch??a ???????c ??i???n v??o!";
		if (model.getNewPassword2() == null || model.getNewPassword2().isEmpty()) return "M???t kh???u m???i 2 ch??a ???????c ??i???n v??o!";
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			if(passwordEncoder.matches(model.getOldPassword(), user.getPassword())) {
				if(model.getNewPassword1().equals(model.getNewPassword2())){
					String newEncodedPassword = passwordEncoder.encode(model.getNewPassword1());
					user.setPassword(newEncodedPassword);
					userRepository.save(user);
					
				}else {
					return "M???t kh???u m???i kh??ng kh???p!";
				}
			}
			else {
				return "M???t kh???u c?? kh??ng kh???p!";
			}
		}else {
			return "Kh??ng t??m th???y t??i kho???n n??y!";
		}
		return null;
	}
	
	public String changPassword(String userName, String newPassword,String retypePassword) {
		
		if(newPassword.isEmpty()) {
			return "B???n ch??a nh???p m???t kh???u m???i!";
		}
		if(retypePassword.isEmpty()) {
			return "B???n ch??a nh???p l???i m???t kh???u m???i!";
		}
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			if(newPassword.equals(retypePassword)){
				String newEncodedPassword = passwordEncoder.encode(retypePassword);
				user.setPassword(newEncodedPassword);
				userRepository.save(user);
				
			}else {
				return "M???t kh???u m???i kh??ng kh???p!";
			}	
		}else {
			return "Kh??ng t??m th???y t??i kho???n n??y!";
		}
		
		return null;
	}
	
	public String checkUserInfo(String userName, String phoneNumber,String email) {
		if(userName.isEmpty() || userName == null) {
			return "B???n ch??a nh???p t??n ng?????i d??ng!";
		}
		if (email.isEmpty() || email == null) {
			return "B???n ch??a nh???p Email!";
		}
		if (phoneNumber.isEmpty() || phoneNumber == null) {
			return "B???n ch??a nh???p s??? ??i???n tho???i!";
		}
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			return "Kh??ng t??m th???y t??i kho???n n??y!";
		}
		if (user.getEmail().equals(email) == false) {
			return "Email kh??ng kh???p v???i t??i kho???n ???? nh???p!";
		}
		if (user.getPhoneNumber().equals(phoneNumber) == false) {
			return "S??? ??i???n tho???i kh??ng kh???p v???i t??i kho???n ???? nh???p!";
		}
		return null;
	}
}
