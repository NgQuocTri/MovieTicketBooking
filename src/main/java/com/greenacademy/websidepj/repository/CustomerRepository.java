package com.greenacademy.websidepj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("Select c from Customer c where c.customerName like %:name%")
	public List<Customer> searchByName(@Param("name") String name);
	
	@Query("Select c from Customer c where c.customerPhone like %:number%")
	public List<Customer> searchByPhoneNum(@Param("number") String number);
	
	@Query("Select c from Customer c where c.customerName like %:name% and c.customerPhone = :phone")
	public List<Customer> searchByNameAndPhone(@Param("name") String name, @Param("phone") String phone);
}
