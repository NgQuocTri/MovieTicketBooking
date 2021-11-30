package com.greenacademy.websidepj.model;

public class CustomerForm {

	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
	private boolean valid;
	
	public CustomerForm() {
		super();
	}

	public CustomerForm(CustomerInfo customerInfo) {
		if(customerInfo!=null) {
			this.customerName=customerInfo.getCustomerName();
			this.customerAddress=customerInfo.getCustomerAddress();
			this.customerEmail=customerInfo.getCustomerEmail();
			this.customerPhone=customerInfo.getCustomerPhone();
		}
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
