package com.greenacademy.websidepj.repository;

import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

	@Query("Select distinct u from Movie u Where u.movieName=:movieName")
	public Movie findByMovieName(@Param("movieName") String movieName);
	
	@Query("select m from Movie m where m.movieName like %:movieName% ")
	public List<Movie> searchByName(@Param("movieName")String movieName);
}
