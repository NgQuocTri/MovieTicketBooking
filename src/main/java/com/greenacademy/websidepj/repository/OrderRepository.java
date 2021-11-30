package com.greenacademy.websidepj.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{
	
	@Query("Select o from Order o where o.customer.customerName like %:name% and o.orderDate = :date")
	public List<Order> searchByNameAndDate(@Param("name") String name, @Param("date") Date date);
	
}
