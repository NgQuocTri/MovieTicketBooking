package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{

	@Query("Select distinct u from Discount u Where u.percentage=:percentage")
	public Discount findByPercen(@Param("percentage") double percentage);
}
