package com.greenacademy.websidepj.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.entity.Schedule;
import com.greenacademy.websidepj.entity.Seat;
import com.greenacademy.websidepj.repository.MovieRepository;
import com.greenacademy.websidepj.repository.ScheduleRepository;


@Service
@Transactional
public class ScheduleService {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	SeatService seatService;
	
	public List<Schedule> getAllSchedules(){
		return scheduleRepository.findAll();
	}
	
	public List<Schedule> getUnfinishedSchedules(){
		List<Schedule> allSchedules = scheduleRepository.findAll();
		List<Schedule> unfinishedSchedules = new ArrayList<Schedule>();
		for (Schedule schedule : allSchedules) {
			schedule.setTransientStatus();
			if (schedule.getTransientStatus().equalsIgnoreCase("Đã chiếu") == false) {
				unfinishedSchedules.add(schedule);
			}
		}
		return unfinishedSchedules;
	}
	
	public List<Schedule> getUnfinishedSchedulesByMovie(Movie movie){
		List<Schedule> unfinishedSchedules = new ArrayList<Schedule>();
		for (Schedule schedule : movie.getSchedules()) {
			if (schedule.getTransientStatus().equalsIgnoreCase("Đã chiếu") == false) {
				unfinishedSchedules.add(schedule);
			}
		}
		return unfinishedSchedules;
	}
	
	public Schedule saveSchedule(Date transientDate,String transientHour) {
		Schedule scheduleEntity=new Schedule();
		scheduleEntity.setDate(transientDate);
		scheduleEntity.setHour(transientHour);
		List<Seat> seats = seatService.getAllSeats();
		scheduleEntity.setSeats(seats);
		scheduleRepository.save(scheduleEntity);
		return scheduleEntity;
	}
	
	public Schedule getScheduleById(Long scheduleId) {
		Optional<Schedule> schedule=scheduleRepository.findById(scheduleId);
		return schedule.get();
	}
	
	public String getHour(Long scheduleId) {
		return scheduleRepository.getHour(scheduleId);
	}
	
	public Schedule getScheduleByDateAndHour(Date date, String hour) {
		return scheduleRepository.findByDateAndHour(date, hour);
	}
	
	public List<Schedule> getScheduleByDateToHour(java.sql.Date date) {
		return scheduleRepository.findByDateToHour(date);
	}
	
	public List<Schedule> getSchedulesByDate(java.sql.Date date,Movie movie){
		List<Schedule> unfinishedMovieSchedules = this.getUnfinishedSchedulesByMovie(movie);
		List<Schedule> schedules = new ArrayList<Schedule>();
		for (Schedule schedule : unfinishedMovieSchedules) {
			if (schedule.getDate().toString().equals(date.toString())) schedules.add(schedule);
		}
		return schedules;
	}
	
	public List<Date> showDate(Date date,int dayNumberAfterOrBefore) {
		List<java.sql.Date> chosenDates = new ArrayList<java.sql.Date>();
		java.sql.Date nextDate = new java.sql.Date(System.currentTimeMillis());
		int d=0;
		if (dayNumberAfterOrBefore>0) {
			chosenDates.add(date);
			for(int i=1;i<7;i++) {
				d=date.getDate();
				nextDate=new java.sql.Date(date.getYear(),date.getMonth(),d+i);
				chosenDates.add(nextDate);
			}
		}
		if (dayNumberAfterOrBefore<0) {
			for(int i=7-1;i>0;i--) {
				d=date.getDate();
				nextDate=new java.sql.Date(date.getYear(),date.getMonth(),d-i);
				chosenDates.add(nextDate);
			}
			chosenDates.add(date);
		}
		return chosenDates;
	}
	
	public List<Schedule> increasedDate(List<Schedule> schedules){
		List<Schedule> increasedSchedules = new ArrayList<Schedule>();
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		for (Schedule schedule : schedules) {
			date = new java.sql.Date(schedule.getDate().getYear(),schedule.getDate().getMonth(),schedule.getDate().getDate()+1);
			schedule.setDate(date);
			increasedSchedules.add(schedule);
		}
		return increasedSchedules;
	}
	
	public List<Schedule> deleteSameSchedule(List<Schedule> schedules){
		List<java.sql.Date> newDates = new ArrayList<java.sql.Date>();
		List<Schedule> newSchedules = new ArrayList<Schedule>();
		
		for (Schedule schedule : schedules) {
			if (!newDates.contains(schedule.getDate())) newDates.add(schedule.getDate());
		}
		int dateNumber = newDates.size();
		Schedule tempSchedule[] = new Schedule[dateNumber];
		for (int i = 0;i < dateNumber;i++) tempSchedule[i] = new Schedule();
		
		int i = 0;
		for (java.sql.Date date : newDates) {
			tempSchedule[i].setDate(date);
			newSchedules.add(tempSchedule[i]);
			i++;
		}
		return newSchedules;
	}
	
	public String convertDateToString(List<java.sql.Date> dates) {
		String dateAsString = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Date date : dates) {
			if (dateAsString != null) {
				dateAsString += ","+ dateFormat.format(date);
			}
			else {
				dateAsString = dateFormat.format(date);
			}
		}
		return dateAsString;
	}
	
	public java.sql.Date getToday(){
		java.util.Date date = new java.util.Date();
		java.sql.Date today = new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
		return today;
	}
	
	public void creatDefaultSchedules() {
		List<Seat> seats = seatService.getAllSeats();
		java.util.Date date = new java.util.Date();
		java.sql.Date today = new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
		java.sql.Date date1 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+1);
		java.sql.Date date2 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+2);
		java.sql.Date date3 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+3);
		java.sql.Date date4 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+4);
		java.sql.Date date5 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+5);
		java.sql.Date date6 = new java.sql.Date(today.getYear(),today.getMonth(),today.getDate()+6);
		
		String time1 = "08:00";
		String time2 = "10:00";
		String time3 = "13:00";
		String time4 = "15:00";
		String time5 = "17:00";
		String time6 = "19:00";
		String time7 = "21:00";
		String time8 = "23:00";
		String time9 = "01:00";
		
		int i=0;
		
		List<Movie> movies = movieRepository.findAll();
		Optional<Movie> movieEntity = null;
		
		//Kinh di
		//Movie1
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie theWitch = movieEntity.get();
		
		Date dateOftheWitch1 = today;
		String hourOftheWitch1 = time1;
		Schedule scheduleOftheWitch1 = new Schedule();
		scheduleOftheWitch1.setDate(dateOftheWitch1);
		scheduleOftheWitch1.setHour(hourOftheWitch1);
		scheduleOftheWitch1.setSeats(seats);
		scheduleOftheWitch1.setMovie(theWitch);
		scheduleRepository.save(scheduleOftheWitch1);
		
		Date dateOftheWitch2 = today;
		String hourOftheWitch2 = time2;
		Schedule scheduleOftheWitch2 = new Schedule();
		scheduleOftheWitch2.setDate(dateOftheWitch2);
		scheduleOftheWitch2.setHour(hourOftheWitch2);
		scheduleOftheWitch2.setSeats(seats);
		scheduleOftheWitch2.setMovie(theWitch);
		scheduleRepository.save(scheduleOftheWitch2);
		
		Date dateOftheWitch3 = today;
		String hourOftheWitch3 = time3;
		Schedule scheduleOftheWitch3 = new Schedule();
		scheduleOftheWitch3.setDate(dateOftheWitch3);
		scheduleOftheWitch3.setHour(hourOftheWitch3);
		scheduleOftheWitch3.setSeats(seats);
		scheduleOftheWitch3.setMovie(theWitch);
		scheduleRepository.save(scheduleOftheWitch3);
		
		//Movie2
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie hamCaMap = movieEntity.get();
		
		Date dateOfHamCaMap1 = today;
		String hourOfHamCaMap1 = time4;
		Schedule scheduleOfHamCaMap1 = new Schedule();
		scheduleOfHamCaMap1.setDate(dateOfHamCaMap1);
		scheduleOfHamCaMap1.setHour(hourOfHamCaMap1);
		scheduleOfHamCaMap1.setSeats(seats);
		scheduleOfHamCaMap1.setMovie(hamCaMap);
		scheduleRepository.save(scheduleOfHamCaMap1);
		
		Date dateOfHamCaMap2 = today;
		String hourOfHamCaMap2 = time5;
		Schedule scheduleOfHamCaMap2 = new Schedule();
		scheduleOfHamCaMap2.setDate(dateOfHamCaMap2);
		scheduleOfHamCaMap2.setHour(hourOfHamCaMap2);
		scheduleOfHamCaMap2.setSeats(seats);
		scheduleOfHamCaMap2.setMovie(hamCaMap);
		scheduleRepository.save(scheduleOfHamCaMap2);
		
		Date dateOfHamCaMap3 = today;
		String hourOfHamCaMap3 = time6;
		Schedule scheduleOfHamCaMap3 = new Schedule();
		scheduleOfHamCaMap3.setDate(dateOfHamCaMap3);
		scheduleOfHamCaMap3.setHour(hourOfHamCaMap3);
		scheduleOfHamCaMap3.setSeats(seats);
		scheduleOfHamCaMap3.setMovie(hamCaMap);
		scheduleRepository.save(scheduleOfHamCaMap3);
		
		//Hanh dong
		//Movie3
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie avengersEndgame = movieEntity.get();
		
		Date dateOfAvengersEndgame1 = today;
		String hourOfAvengersEndgame1 = time7;
		Schedule scheduleOfAvengersEndgame1 = new Schedule();
		scheduleOfAvengersEndgame1.setDate(dateOfAvengersEndgame1);
		scheduleOfAvengersEndgame1.setHour(hourOfAvengersEndgame1);
		scheduleOfAvengersEndgame1.setSeats(seats);
		scheduleOfAvengersEndgame1.setMovie(avengersEndgame);
		scheduleRepository.save(scheduleOfAvengersEndgame1);
		
		Date dateOfAvengersEndgame2 = today;
		String hourOfAvengersEndgame2 = time8;
		Schedule scheduleOfAvengersEndgame2 = new Schedule();
		scheduleOfAvengersEndgame2.setDate(dateOfAvengersEndgame2);
		scheduleOfAvengersEndgame2.setHour(hourOfAvengersEndgame2);
		scheduleOfAvengersEndgame2.setSeats(seats);
		scheduleOfAvengersEndgame2.setMovie(avengersEndgame);
		scheduleRepository.save(scheduleOfAvengersEndgame2);
		
		Date dateOfAvengersEndgame3 = today;
		String hourOfAvengersEndgame3 = time9;
		Schedule scheduleOfAvengersEndgame3 = new Schedule();
		scheduleOfAvengersEndgame3.setDate(dateOfAvengersEndgame3);
		scheduleOfAvengersEndgame3.setHour(hourOfAvengersEndgame3);
		scheduleOfAvengersEndgame3.setSeats(seats);
		scheduleOfAvengersEndgame3.setMovie(avengersEndgame);
		scheduleRepository.save(scheduleOfAvengersEndgame3);
		
		//Movie4
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie madMax = movieEntity.get();
		
		Date dateOfMadMax1 = date1;
		String hourOfMadMax1 = time1;
		Schedule scheduleOfMadMax1 = new Schedule();
		scheduleOfMadMax1.setDate(dateOfMadMax1);
		scheduleOfMadMax1.setHour(hourOfMadMax1);
		scheduleOfMadMax1.setSeats(seats);
		scheduleOfMadMax1.setMovie(madMax);
		scheduleRepository.save(scheduleOfMadMax1);
		
		Date dateOfMadMax2 = date1;
		String hourOfMadMax2 = time2;
		Schedule scheduleOfMadMax2 = new Schedule();
		scheduleOfMadMax2.setDate(dateOfMadMax2);
		scheduleOfMadMax2.setHour(hourOfMadMax2);
		scheduleOfMadMax2.setSeats(seats);
		scheduleOfMadMax2.setMovie(madMax);
		scheduleRepository.save(scheduleOfMadMax2);
		
		Date dateOfMadMax3 = date1;
		String hourOfMadMax3 = time3;
		Schedule scheduleOfMadMax3 = new Schedule();
		scheduleOfMadMax3.setDate(dateOfMadMax3);
		scheduleOfMadMax3.setHour(hourOfMadMax3);
		scheduleOfMadMax3.setSeats(seats);
		scheduleOfMadMax3.setMovie(madMax);
		scheduleRepository.save(scheduleOfMadMax3);
		
		//Chien tranh
		//Movie5
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie midway = movieEntity.get();
		
		Date dateOfMidway1 = date1;
		String hourOfMidway1 = time4;
		Schedule scheduleOfMidway1 = new Schedule();
		scheduleOfMidway1.setDate(dateOfMidway1);
		scheduleOfMidway1.setHour(hourOfMidway1);
		scheduleOfMidway1.setSeats(seats);
		scheduleOfMidway1.setMovie(midway);
		scheduleRepository.save(scheduleOfMidway1);
		
		Date dateOfMidway2 = date1;
		String hourOfMidway2 = time5;
		Schedule scheduleOfMidway2 = new Schedule();
		scheduleOfMidway2.setDate(dateOfMidway2);
		scheduleOfMidway2.setHour(hourOfMidway2);
		scheduleOfMidway2.setSeats(seats);
		scheduleOfMidway2.setMovie(midway);
		scheduleRepository.save(scheduleOfMidway2);
		
		Date dateOfMidway3 = date1;
		String hourOfMidway3 = time6;
		Schedule scheduleOfMidway3 = new Schedule();
		scheduleOfMidway3.setDate(dateOfMidway3);
		scheduleOfMidway3.setHour(hourOfMidway3);
		scheduleOfMidway3.setSeats(seats);
		scheduleOfMidway3.setMovie(midway);
		scheduleRepository.save(scheduleOfMidway3);
		
		//Movie6
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie movie1917 = movieEntity.get();
		
		Date dateOfMovie1917_1 = date1;
		String hourOfMovie1917_1 = time7;
		Schedule scheduleOfMovie1917_1 = new Schedule();
		scheduleOfMovie1917_1.setDate(dateOfMovie1917_1);
		scheduleOfMovie1917_1.setHour(hourOfMovie1917_1);
		scheduleOfMovie1917_1.setSeats(seats);
		scheduleOfMovie1917_1.setMovie(movie1917);
		scheduleRepository.save(scheduleOfMovie1917_1);
		
		Date dateOfMovie1917_2 = date1;
		String hourOfMovie1917_2 = time8;
		Schedule scheduleOfMovie1917_2 = new Schedule();
		scheduleOfMovie1917_2.setDate(dateOfMovie1917_2);
		scheduleOfMovie1917_2.setHour(hourOfMovie1917_2);
		scheduleOfMovie1917_2.setSeats(seats);
		scheduleOfMovie1917_2.setMovie(movie1917);
		scheduleRepository.save(scheduleOfMovie1917_2);
		
		Date dateOfMovie1917_3 = date1;
		String hourOfMovie1917_3 = time9;
		Schedule scheduleOfMovie1917_3 = new Schedule();
		scheduleOfMovie1917_3.setDate(dateOfMovie1917_3);
		scheduleOfMovie1917_3.setHour(hourOfMovie1917_3);
		scheduleOfMovie1917_3.setSeats(seats);
		scheduleOfMovie1917_3.setMovie(movie1917);
		scheduleRepository.save(scheduleOfMovie1917_3);
		
		//Khoa hoc vien tuong
		//Movie7
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie starWar = movieEntity.get();
		
		Date dateOfStarWar1 = date2;
		String hourOfStarWar1 = time1;
		Schedule scheduleOfStarWar1 = new Schedule();
		scheduleOfStarWar1.setDate(dateOfStarWar1);
		scheduleOfStarWar1.setHour(hourOfStarWar1);
		scheduleOfStarWar1.setSeats(seats);
		scheduleOfStarWar1.setMovie(starWar);
		scheduleRepository.save(scheduleOfStarWar1);
		
		Date dateOfStarWar2 = date2;
		String hourOfStarWar2 = time2;
		Schedule scheduleOfStarWar2 = new Schedule();
		scheduleOfStarWar2.setDate(dateOfStarWar2);
		scheduleOfStarWar2.setHour(hourOfStarWar2);
		scheduleOfStarWar2.setSeats(seats);
		scheduleOfStarWar2.setMovie(starWar);
		scheduleRepository.save(scheduleOfStarWar2);
		
		Date dateOfStarWar3 = date2;
		String hourOfStarWar3 = time3;
		Schedule scheduleOfStarWar3 = new Schedule();
		scheduleOfStarWar3.setDate(dateOfStarWar3);
		scheduleOfStarWar3.setHour(hourOfStarWar3);
		scheduleOfStarWar3.setSeats(seats);
		scheduleOfStarWar3.setMovie(starWar);
		scheduleRepository.save(scheduleOfStarWar3);
		
		//Movie8
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie gravity = movieEntity.get();
		
		Date dateOfGravity1 = date2;
		String hourOfGravity1 = time4;
		Schedule scheduleOfGravity1 = new Schedule();
		scheduleOfGravity1.setDate(dateOfGravity1);
		scheduleOfGravity1.setHour(hourOfGravity1);
		scheduleOfGravity1.setSeats(seats);
		scheduleOfGravity1.setMovie(gravity);
		scheduleRepository.save(scheduleOfGravity1);
		
		Date dateOfGravity2 = date2;
		String hourOfGravity2 = time5;
		Schedule scheduleOfGravity2 = new Schedule();
		scheduleOfGravity2.setDate(dateOfGravity2);
		scheduleOfGravity2.setHour(hourOfGravity2);
		scheduleOfGravity2.setSeats(seats);
		scheduleOfGravity2.setMovie(gravity);
		scheduleRepository.save(scheduleOfGravity2);
		
		Date dateOfGravity3 = date2;
		String hourOfGravity3 = time6;
		Schedule scheduleOfGravity3 = new Schedule();
		scheduleOfGravity3.setDate(dateOfGravity3);
		scheduleOfGravity3.setHour(hourOfGravity3);
		scheduleOfGravity3.setSeats(seats);
		scheduleOfGravity3.setMovie(gravity);
		scheduleRepository.save(scheduleOfGravity3);
		
		//Lich su
		//Movie9
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie hanoi12NgayDem = movieEntity.get();
		
		Date dateOfHanoi12NgayDem1 = date2;
		String hourOfHanoi12NgayDem1 = time7;
		Schedule scheduleOfHanoi12NgayDem1 = new Schedule();
		scheduleOfHanoi12NgayDem1.setDate(dateOfHanoi12NgayDem1);
		scheduleOfHanoi12NgayDem1.setHour(hourOfHanoi12NgayDem1);
		scheduleOfHanoi12NgayDem1.setSeats(seats);
		scheduleOfHanoi12NgayDem1.setMovie(hanoi12NgayDem);
		scheduleRepository.save(scheduleOfHanoi12NgayDem1);
		
		Date dateOfHanoi12NgayDem2 = date2;
		String hourOfHanoi12NgayDem2 = time8;
		Schedule scheduleOfHanoi12NgayDem2 = new Schedule();
		scheduleOfHanoi12NgayDem2.setDate(dateOfHanoi12NgayDem2);
		scheduleOfHanoi12NgayDem2.setHour(hourOfHanoi12NgayDem2);
		scheduleOfHanoi12NgayDem2.setSeats(seats);
		scheduleOfHanoi12NgayDem2.setMovie(hanoi12NgayDem);
		scheduleRepository.save(scheduleOfHanoi12NgayDem2);
		
		Date dateOfHanoi12NgayDem3 = date2;
		String hourOfHanoi12NgayDem3 = time9;
		Schedule scheduleOfHanoi12NgayDem3 = new Schedule();
		scheduleOfHanoi12NgayDem3.setDate(dateOfHanoi12NgayDem3);
		scheduleOfHanoi12NgayDem3.setHour(hourOfHanoi12NgayDem3);
		scheduleOfHanoi12NgayDem3.setSeats(seats);
		scheduleOfHanoi12NgayDem3.setMovie(hanoi12NgayDem);
		scheduleRepository.save(scheduleOfHanoi12NgayDem3);
		
		//Movie10
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie giaiPhongSaiGon = movieEntity.get();
		
		Date dateOfGiaiPhongSaiGon1 = date3;
		String hourOfGiaiPhongSaiGon1 = time1;
		Schedule scheduleOfGiaiPhongSaiGon1 = new Schedule();
		scheduleOfGiaiPhongSaiGon1.setDate(dateOfGiaiPhongSaiGon1);
		scheduleOfGiaiPhongSaiGon1.setHour(hourOfGiaiPhongSaiGon1);
		scheduleOfGiaiPhongSaiGon1.setSeats(seats);
		scheduleOfGiaiPhongSaiGon1.setMovie(giaiPhongSaiGon);
		scheduleRepository.save(scheduleOfGiaiPhongSaiGon1);
		
		Date dateOfGiaiPhongSaiGon2 = date3;
		String hourOfGiaiPhongSaiGon2 = time2;
		Schedule scheduleOfGiaiPhongSaiGon2 = new Schedule();
		scheduleOfGiaiPhongSaiGon2.setDate(dateOfGiaiPhongSaiGon2);
		scheduleOfGiaiPhongSaiGon2.setHour(hourOfGiaiPhongSaiGon2);
		scheduleOfGiaiPhongSaiGon2.setSeats(seats);
		scheduleOfGiaiPhongSaiGon2.setMovie(giaiPhongSaiGon);
		scheduleRepository.save(scheduleOfGiaiPhongSaiGon2);
		
		Date dateOfGiaiPhongSaiGon3 = date3;
		String hourOfGiaiPhongSaiGon3 = time3;
		Schedule scheduleOfGiaiPhongSaiGon3 = new Schedule();
		scheduleOfGiaiPhongSaiGon3.setDate(dateOfGiaiPhongSaiGon3);
		scheduleOfGiaiPhongSaiGon3.setHour(hourOfGiaiPhongSaiGon3);
		scheduleOfGiaiPhongSaiGon3.setSeats(seats);
		scheduleOfGiaiPhongSaiGon3.setMovie(giaiPhongSaiGon);
		scheduleRepository.save(scheduleOfGiaiPhongSaiGon3);
		
		//Phieu luu
		//Movie11
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie kingKong = movieEntity.get();
		
		Date dateOfKingKong1 = date3;
		String hourOfKingKong1 = time4;
		Schedule scheduleOfKingKong1 = new Schedule();
		scheduleOfKingKong1.setDate(dateOfKingKong1);
		scheduleOfKingKong1.setHour(hourOfKingKong1);
		scheduleOfKingKong1.setSeats(seats);
		scheduleOfKingKong1.setMovie(kingKong);
		scheduleRepository.save(scheduleOfKingKong1);
		
		Date dateOfKingKong2 = date3;
		String hourOfKingKong2 = time5;
		Schedule scheduleOfKingKong2 = new Schedule();
		scheduleOfKingKong2.setDate(dateOfKingKong2);
		scheduleOfKingKong2.setHour(hourOfKingKong2);
		scheduleOfKingKong2.setSeats(seats);
		scheduleOfKingKong2.setMovie(kingKong);
		scheduleRepository.save(scheduleOfKingKong2);
		
		Date dateOfKingKong3 = date3;
		String hourOfKingKong3 = time6;
		Schedule scheduleOfKingKong3 = new Schedule();
		scheduleOfKingKong3.setDate(dateOfKingKong3);
		scheduleOfKingKong3.setHour(hourOfKingKong3);
		scheduleOfKingKong3.setSeats(seats);
		scheduleOfKingKong3.setMovie(kingKong);
		scheduleRepository.save(scheduleOfKingKong3);
		
		//Movie12
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie tombRaider = movieEntity.get();
		
		Date dateOfTombRaider1 = date3;
		String hourOfTombRaider1 = time7;
		Schedule scheduleOfTombRaider1 = new Schedule();
		scheduleOfTombRaider1.setDate(dateOfTombRaider1);
		scheduleOfTombRaider1.setHour(hourOfTombRaider1);
		scheduleOfTombRaider1.setSeats(seats);
		scheduleOfTombRaider1.setMovie(tombRaider);
		scheduleRepository.save(scheduleOfTombRaider1);
		
		Date dateOfTombRaider2 = date3;
		String hourOfTombRaider2 = time8;
		Schedule scheduleOfTombRaider2 = new Schedule();
		scheduleOfTombRaider2.setDate(dateOfTombRaider2);
		scheduleOfTombRaider2.setHour(hourOfTombRaider2);
		scheduleOfTombRaider2.setSeats(seats);
		scheduleOfTombRaider2.setMovie(tombRaider);
		scheduleRepository.save(scheduleOfTombRaider2);
		
		Date dateOfTombRaider3 = date3;
		String hourOfTombRaider3 = time9;
		Schedule scheduleOfTombRaider3 = new Schedule();
		scheduleOfTombRaider3.setDate(dateOfTombRaider3);
		scheduleOfTombRaider3.setHour(hourOfTombRaider3);
		scheduleOfTombRaider3.setSeats(seats);
		scheduleOfTombRaider3.setMovie(tombRaider);
		scheduleRepository.save(scheduleOfTombRaider3);
		
		//Phim hai
		//Movie13
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie helloCoBa = movieEntity.get();
		
		Date dateOfHelloCoBa1 = date4;
		String hourOfHelloCoBa1 = time1;
		Schedule scheduleOfHelloCoBa1 = new Schedule();
		scheduleOfHelloCoBa1.setDate(dateOfHelloCoBa1);
		scheduleOfHelloCoBa1.setHour(hourOfHelloCoBa1);
		scheduleOfHelloCoBa1.setSeats(seats);
		scheduleOfHelloCoBa1.setMovie(helloCoBa);
		scheduleRepository.save(scheduleOfHelloCoBa1);
		
		Date dateOfHelloCoBa2 = date4;
		String hourOfHelloCoBa2 = time2;
		Schedule scheduleOfHelloCoBa2 = new Schedule();
		scheduleOfHelloCoBa2.setDate(dateOfHelloCoBa2);
		scheduleOfHelloCoBa2.setHour(hourOfHelloCoBa2);
		scheduleOfHelloCoBa2.setSeats(seats);
		scheduleOfHelloCoBa2.setMovie(helloCoBa);
		scheduleRepository.save(scheduleOfHelloCoBa2);
		
		Date dateOfHelloCoBa3 = date4;
		String hourOfHelloCoBa3 = time3;
		Schedule scheduleOfHelloCoBa3 = new Schedule();
		scheduleOfHelloCoBa3.setDate(dateOfHelloCoBa3);
		scheduleOfHelloCoBa3.setHour(hourOfHelloCoBa3);
		scheduleOfHelloCoBa3.setSeats(seats);
		scheduleOfHelloCoBa3.setMovie(helloCoBa);
		scheduleRepository.save(scheduleOfHelloCoBa3);
		
		//Movie14
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie coDauDaiChien = movieEntity.get();
		
		Date dateOfCoDauDaiChien1 = date4;
		String hourOfCoDauDaiChien1 = time4;
		Schedule scheduleOfCoDauDaiChien1 = new Schedule();
		scheduleOfCoDauDaiChien1.setDate(dateOfCoDauDaiChien1);
		scheduleOfCoDauDaiChien1.setHour(hourOfCoDauDaiChien1);
		scheduleOfCoDauDaiChien1.setSeats(seats);
		scheduleOfCoDauDaiChien1.setMovie(coDauDaiChien);
		scheduleRepository.save(scheduleOfCoDauDaiChien1);
		
		Date dateOfCoDauDaiChien2 = date4;
		String hourOfCoDauDaiChien2 = time5;
		Schedule scheduleOfCoDauDaiChien2 = new Schedule();
		scheduleOfCoDauDaiChien2.setDate(dateOfCoDauDaiChien2);
		scheduleOfCoDauDaiChien2.setHour(hourOfCoDauDaiChien2);
		scheduleOfCoDauDaiChien2.setSeats(seats);
		scheduleOfCoDauDaiChien2.setMovie(coDauDaiChien);
		scheduleRepository.save(scheduleOfCoDauDaiChien2);
		
		Date dateOfCoDauDaiChien3 = date4;
		String hourOfCoDauDaiChien3 = time6;
		Schedule scheduleOfCoDauDaiChien3 = new Schedule();
		scheduleOfCoDauDaiChien3.setDate(dateOfCoDauDaiChien3);
		scheduleOfCoDauDaiChien3.setHour(hourOfCoDauDaiChien3);
		scheduleOfCoDauDaiChien3.setSeats(seats);
		scheduleOfCoDauDaiChien3.setMovie(coDauDaiChien);
		scheduleRepository.save(scheduleOfCoDauDaiChien3);
		
		//Ky ao
		//Movie15
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie luaPhat = movieEntity.get();
		
		Date dateOfLuaPhat1 = date4;
		String hourOfLuaPhat1 = time7;
		Schedule scheduleOfLuaPhat1 = new Schedule();
		scheduleOfLuaPhat1.setDate(dateOfLuaPhat1);
		scheduleOfLuaPhat1.setHour(hourOfLuaPhat1);
		scheduleOfLuaPhat1.setSeats(seats);
		scheduleOfLuaPhat1.setMovie(luaPhat);
		scheduleRepository.save(scheduleOfLuaPhat1);
		
		Date dateOfLuaPhat2 = date4;
		String hourOfLuaPhat2 = time8;
		Schedule scheduleOfLuaPhat2 = new Schedule();
		scheduleOfLuaPhat2.setDate(dateOfLuaPhat2);
		scheduleOfLuaPhat2.setHour(hourOfLuaPhat2);
		scheduleOfLuaPhat2.setSeats(seats);
		scheduleOfLuaPhat2.setMovie(luaPhat);
		scheduleRepository.save(scheduleOfLuaPhat2);
		
		Date dateOfLuaPhat3 = date4;
		String hourOfLuaPhat3 = time9;
		Schedule scheduleOfLuaPhat3 = new Schedule();
		scheduleOfLuaPhat3.setDate(dateOfLuaPhat3);
		scheduleOfLuaPhat3.setHour(hourOfLuaPhat3);
		scheduleOfLuaPhat3.setSeats(seats);
		scheduleOfLuaPhat3.setMovie(luaPhat);
		scheduleRepository.save(scheduleOfLuaPhat3);
		
		//Movie16
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie lastAirbender = movieEntity.get();
		
		Date dateOfLastAirbender1 = date5;
		String hourOfLastAirbender1 = time1;
		Schedule scheduleOfLastAirbender1 = new Schedule();
		scheduleOfLastAirbender1.setDate(dateOfLastAirbender1);
		scheduleOfLastAirbender1.setHour(hourOfLastAirbender1);
		scheduleOfLastAirbender1.setSeats(seats);
		scheduleOfLastAirbender1.setMovie(lastAirbender);
		scheduleRepository.save(scheduleOfLastAirbender1);
		
		Date dateOfLastAirbender2 = date5;
		String hourOfLastAirbender2 = time2;
		Schedule scheduleOfLastAirbender2 = new Schedule();
		scheduleOfLastAirbender2.setDate(dateOfLastAirbender2);
		scheduleOfLastAirbender2.setHour(hourOfLastAirbender2);
		scheduleOfLastAirbender2.setSeats(seats);
		scheduleOfLastAirbender2.setMovie(lastAirbender);
		scheduleRepository.save(scheduleOfLastAirbender2);
		
		Date dateOfLastAirbender3 = date5;
		String hourOfLastAirbender3 = time3;
		Schedule scheduleOfLastAirbender3 = new Schedule();
		scheduleOfLastAirbender3.setDate(dateOfLastAirbender3);
		scheduleOfLastAirbender3.setHour(hourOfLastAirbender3);
		scheduleOfLastAirbender3.setSeats(seats);
		scheduleOfLastAirbender3.setMovie(lastAirbender);
		scheduleRepository.save(scheduleOfLastAirbender3);
		
		//Tinh cam
		//Movie17
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie meBeforeYou = movieEntity.get();
		
		Date dateOfMeBeforeYou1 = date5;
		String hourOfMeBeforeYou1 = time4;
		Schedule scheduleOfMeBeforeYou1 = new Schedule();
		scheduleOfMeBeforeYou1.setDate(dateOfMeBeforeYou1);
		scheduleOfMeBeforeYou1.setHour(hourOfMeBeforeYou1);
		scheduleOfMeBeforeYou1.setSeats(seats);
		scheduleOfMeBeforeYou1.setMovie(meBeforeYou);
		scheduleRepository.save(scheduleOfMeBeforeYou1);
		
		Date dateOfMeBeforeYou2 = date5;
		String hourOfMeBeforeYou2 = time5;
		Schedule scheduleOfMeBeforeYou2 = new Schedule();
		scheduleOfMeBeforeYou2.setDate(dateOfMeBeforeYou2);
		scheduleOfMeBeforeYou2.setHour(hourOfMeBeforeYou2);
		scheduleOfMeBeforeYou2.setSeats(seats);
		scheduleOfMeBeforeYou2.setMovie(meBeforeYou);
		scheduleRepository.save(scheduleOfMeBeforeYou2);
		
		Date dateOfMeBeforeYou3 = date5;
		String hourOfMeBeforeYou3 = time6;
		Schedule scheduleOfMeBeforeYou3 = new Schedule();
		scheduleOfMeBeforeYou3.setDate(dateOfMeBeforeYou3);
		scheduleOfMeBeforeYou3.setHour(hourOfMeBeforeYou3);
		scheduleOfMeBeforeYou3.setSeats(seats);
		scheduleOfMeBeforeYou3.setMovie(meBeforeYou);
		scheduleRepository.save(scheduleOfMeBeforeYou3);
		
		//Movie18
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie matBiec = movieEntity.get();
		
		Date dateOfMatBiec1 = date5;
		String hourOfMatBiec1 = time7;
		Schedule scheduleOfMatBiec1 = new Schedule();
		scheduleOfMatBiec1.setDate(dateOfMatBiec1);
		scheduleOfMatBiec1.setHour(hourOfMatBiec1);
		scheduleOfMatBiec1.setSeats(seats);
		scheduleOfMatBiec1.setMovie(matBiec);
		scheduleRepository.save(scheduleOfMatBiec1);
		
		Date dateOfMatBiec2 = date5;
		String hourOfMatBiec2 = time8;
		Schedule scheduleOfMatBiec2 = new Schedule();
		scheduleOfMatBiec2.setDate(dateOfMatBiec2);
		scheduleOfMatBiec2.setHour(hourOfMatBiec2);
		scheduleOfMatBiec2.setSeats(seats);
		scheduleOfMatBiec2.setMovie(matBiec);
		scheduleRepository.save(scheduleOfMatBiec2);
		
		Date dateOfMatBiec3 = date5;
		String hourOfMatBiec3 = time9;
		Schedule scheduleOfMatBiec3 = new Schedule();
		scheduleOfMatBiec3.setDate(dateOfMatBiec3);
		scheduleOfMatBiec3.setHour(hourOfMatBiec3);
		scheduleOfMatBiec3.setSeats(seats);
		scheduleOfMatBiec3.setMovie(matBiec);
		scheduleRepository.save(scheduleOfMatBiec3);
		
		//Hoat hinh
		//Movie19
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie frozen = movieEntity.get();
		
		Date dateOfFrozen1 = date6;
		String hourOfFrozen1 = time1;
		Schedule scheduleOfFrozen1 = new Schedule();
		scheduleOfFrozen1.setDate(dateOfFrozen1);
		scheduleOfFrozen1.setHour(hourOfFrozen1);
		scheduleOfFrozen1.setSeats(seats);
		scheduleOfFrozen1.setMovie(frozen);
		scheduleRepository.save(scheduleOfFrozen1);
		
		Date dateOfFrozen2 = date6;
		String hourOfFrozen2 = time2;
		Schedule scheduleOfFrozen2 = new Schedule();
		scheduleOfFrozen2.setDate(dateOfFrozen2);
		scheduleOfFrozen2.setHour(hourOfFrozen2);
		scheduleOfFrozen2.setSeats(seats);
		scheduleOfFrozen2.setMovie(frozen);
		scheduleRepository.save(scheduleOfFrozen2);
		
		Date dateOfFrozen3 = date6;
		String hourOfFrozen3 = time3;
		Schedule scheduleOfFrozen3 = new Schedule();
		scheduleOfFrozen3.setDate(dateOfFrozen3);
		scheduleOfFrozen3.setHour(hourOfFrozen3);
		scheduleOfFrozen3.setSeats(seats);
		scheduleOfFrozen3.setMovie(frozen);
		scheduleRepository.save(scheduleOfFrozen3);
		
		//Movie20
		i++;
		movieEntity = movieRepository.findById(movies.get(i).getMovieId());
		Movie kimiNoNawa = movieEntity.get();
		
		Date dateOfKimiNoNawa1 = date6;
		String hourOfKimiNoNawa1 = time4;
		Schedule scheduleOfKimiNoNawa1 = new Schedule();
		scheduleOfKimiNoNawa1.setDate(dateOfKimiNoNawa1);
		scheduleOfKimiNoNawa1.setHour(hourOfKimiNoNawa1);
		scheduleOfKimiNoNawa1.setSeats(seats);
		scheduleOfKimiNoNawa1.setMovie(kimiNoNawa);
		scheduleRepository.save(scheduleOfKimiNoNawa1);
		
		Date dateOfKimiNoNawa2 = date6;
		String hourOfKimiNoNawa2 = time5;
		Schedule scheduleOfKimiNoNawa2 = new Schedule();
		scheduleOfKimiNoNawa2.setDate(dateOfKimiNoNawa2);
		scheduleOfKimiNoNawa2.setHour(hourOfKimiNoNawa2);
		scheduleOfKimiNoNawa2.setSeats(seats);
		scheduleOfKimiNoNawa2.setMovie(kimiNoNawa);
		scheduleRepository.save(scheduleOfKimiNoNawa2);
		
		Date dateOfKimiNoNawa3 = date6;
		String hourOfKimiNoNawa3 = time6;
		Schedule scheduleOfKimiNoNawa3 = new Schedule();
		scheduleOfKimiNoNawa3.setDate(dateOfKimiNoNawa3);
		scheduleOfKimiNoNawa3.setHour(hourOfKimiNoNawa3);
		scheduleOfKimiNoNawa3.setSeats(seats);
		scheduleOfKimiNoNawa3.setMovie(kimiNoNawa);
		scheduleRepository.save(scheduleOfKimiNoNawa3);
	}
}
