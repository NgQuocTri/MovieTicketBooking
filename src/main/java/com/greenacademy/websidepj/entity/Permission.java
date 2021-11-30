package com.greenacademy.websidepj.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission {
	private Long permissionId;
	private String permissionName;
	private List<User> userList;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Permission_Id", unique = true, nullable = false)
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long id) {
		this.permissionId = id;
	}
	
	@Column(name = "Permission_name")
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String name) {
		this.permissionName = name;
	}
	
	@ManyToMany
	@JoinTable(name = "User_Permission",
		joinColumns = { @JoinColumn(name = "User_Id") },
		inverseJoinColumns = { @JoinColumn(name = "Permission_Id") })
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
