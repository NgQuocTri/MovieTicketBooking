package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.TicketCategory;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {

}
