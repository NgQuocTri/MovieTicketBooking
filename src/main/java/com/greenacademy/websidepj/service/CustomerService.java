package com.greenacademy.websidepj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Customer;
import com.greenacademy.websidepj.entity.Order;
import com.greenacademy.websidepj.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Long customerId) {
		Optional<Customer> customer=customerRepository.findById(customerId);
		Customer customerEntity=customer.get();
		return customerEntity;
	}
	
	public Customer saveEditedCustomer(Customer customerModel) {
		Customer customerEntity=new Customer();
		customerEntity.setCustomerId(customerModel.getCustomerId());
		customerEntity.setCustomerName(customerModel.getCustomerName());
		customerEntity.setCustomerAddress(customerModel.getCustomerAddress());
		customerEntity.setCustomerEmail(customerModel.getCustomerEmail());
		customerEntity.setCustomerPhone(customerModel.getCustomerPhone());
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	public Customer deleteCustomer(Long customerId) {
		Customer customerEntity=new Customer();
		customerEntity.setCustomerId(customerId);
		customerRepository.delete(customerEntity);
		return customerEntity;
	}
	
	public Customer newCustomer(String name, String address, String phone, String email) {
		Customer customer = new Customer();
		customer.setCustomerName(name);
		customer.setCustomerAddress(address);
		customer.setCustomerPhone(phone);
		customer.setCustomerEmail(email);
		customerRepository.save(customer);
		
		return customer;
	}
	
	public List<Order> getOrdersByCustomerCode(Long customerCode){
		List<Order> orders = new ArrayList<Order>();
		for (Customer customer : this.getAllCustomers()) {
			if (customer.getCustomerCode() == customerCode) orders.add(customer.getOrder());
		}
		return orders;

	}
	public List<Customer> searchByNameAndPhone(String name, String phone){
		return customerRepository.searchByNameAndPhone(name, phone);
	}
	
}
