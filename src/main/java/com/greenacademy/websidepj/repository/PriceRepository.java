package com.greenacademy.websidepj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query("select p from Price p where p.ticketCategory.catName like %:catName% ")
	public List<Price> searchByTicketCat(@Param("catName")String catName);
}