package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

	/*
	 * @Query("SELECT distinct s FROM Seat s WHERE s.row = :row and s.chair = :chair"
	 * ) public Seat findByRowAndChair (@Param("row") char row, @Param("chair") int
	 * chair);
	 * 
	 * 
	 * @Query("SELECT distinct s FROM Seat s WHERE s.chair = :chair") public Seat
	 * findByChair (@Param("chair") int chair);
	 */
	
}
