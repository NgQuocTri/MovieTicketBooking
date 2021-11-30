package com.greenacademy.websidepj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	private Long customerId;
	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
	private Long customerCode;
	private String customerCategory;
	private Order order;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id",unique=true,nullable=false)
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long id) {
		this.customerId = id;
	}
	
	@Column(name="Name")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Column(name="Address")
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@Column(name="Email")
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	@Column(name="Phone_Number")
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Column(name="customerCode")
	public Long getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(Long customerCode) {
		this.customerCode = customerCode;
	}
	
	@Column(name="Customer_category")
	public String getCustomerCategory() {
		return customerCategory;
	}
	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}
	
	@OneToOne(mappedBy="customer")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
