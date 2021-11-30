package com.greenacademy.websidepj.model;

public class CustomerInfo {
	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
	private boolean valid;
	
	public CustomerInfo() {
		super();
	}

	public CustomerInfo(String customerName, String customerAddress, String customerEmail, String customerPhone,
			boolean valid) {
		super();
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.valid = valid;
	}

	public CustomerInfo(CustomerForm customerForm) {
		super();
		this.customerName = customerForm.getCustomerName();
		this.customerAddress = customerForm.getCustomerAddress();
		this.customerEmail = customerForm.getCustomerEmail();
		this.customerPhone = customerForm.getCustomerPhone();
		this.valid = customerForm.isValid();
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
