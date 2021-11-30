package com.greenacademy.websidepj.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movieAge")
public class MovieAge {
	private Long ageId;
	private String ageLimit;
	private List<Movie> movies;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ageId",unique=true,nullable=false)
	public Long getAgeId() {
		return ageId;
	}
	public void setAgeId(Long ageId) {
		this.ageId = ageId;
	}
	
	@Column(name="ageLimit")
	public String getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}
	
	@OneToMany(mappedBy="movieAge")
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}