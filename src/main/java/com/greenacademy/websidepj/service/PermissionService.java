package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Permission;
import com.greenacademy.websidepj.repository.PermissionRepository;

@Service
public class PermissionService {
	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> getAllPermissions(){
		return permissionRepository.findAll();
	}
	
	public Permission getPermissionById(long permissionId) {
		Optional<Permission> permission = permissionRepository.findById(permissionId);
		return permission.get();
	}
}
