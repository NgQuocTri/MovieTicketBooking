package com.greenacademy.websidepj.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("SELECT distinct s FROM Schedule s WHERE s.date = :date and s.hour = :hour")
	public Schedule findByDateAndHour (@Param("date") Date date, @Param("hour") String hour); 
	
	@Query("SELECT s FROM Schedule s WHERE s.date = :date")
	public List<Schedule> findByDateToHour (@Param("date") Date date);
	
	@Query("SELECT s.hour FROM Schedule s WHERE s.scheduleId = :scheduleId")
	public String getHour (@Param("scheduleId") Long scheduleId);
}
