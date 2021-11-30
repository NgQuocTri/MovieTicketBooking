package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.MovieAge;
import com.greenacademy.websidepj.repository.MovieAgeRepository;

@Service
@Transactional
public class MovieAgeService {

	@Autowired
	MovieAgeRepository movieAgeRepository;
	
	public List<MovieAge> getAllMovieAges() {
		return movieAgeRepository.findAll();
	}
	
	public MovieAge getMovieAgeById(Long movieAgeId) {
		Optional<MovieAge> movieAge=movieAgeRepository.findById(movieAgeId);
		MovieAge movieAgeEntity=movieAge.get();
		return movieAgeEntity;
	}
	
	public void createMovieAge() {
		MovieAge p=new MovieAge();
		p.setAgeLimit("P");
		movieAgeRepository.save(p);
		
		MovieAge c13=new MovieAge();
		c13.setAgeLimit("C13");
		movieAgeRepository.save(c13);
		
		MovieAge c16=new MovieAge();
		c16.setAgeLimit("C16");
		movieAgeRepository.save(c16);
		
		MovieAge c18=new MovieAge();
		c18.setAgeLimit("C18");
		movieAgeRepository.save(c18);
	}
}
