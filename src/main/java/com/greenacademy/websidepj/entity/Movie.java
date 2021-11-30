package com.greenacademy.websidepj.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="movie")
public class Movie {
	private Long movieId;
	private String movieName;
	private Genre genre;
	private MovieAge movieAge;
	private String imageName;
	private MultipartFile imageFile;
	private List<Schedule> schedules;
	private String description;
	private String trailerLink;
	
	private Date transientDate;
	private String transientHour;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movieId",unique=true,nullable=false)
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	@Column(name="movieName")
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genreId")
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ageLimitId")
	public MovieAge getMovieAge() {
		return movieAge;
	}
	public void setMovieAge(MovieAge movieAge) {
		this.movieAge = movieAge;
	}
	
	@Column(name="imageName")
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	@Transient
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	@OneToMany(mappedBy="movie")
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	@Column(name="description", length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="trailerLink")
	public String getTrailerLink() {
		return trailerLink;
	}
	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}
	
	@Transient
	public Date getTransientDate() {
		return transientDate;
	}
	public void setTransientDate(Date transientDate) {
		this.transientDate = transientDate;
	}
	
	@Transient
	public String getTransientHour() {
		return transientHour;
	}
	public void setTransientHour(String transientHours) {
		this.transientHour = transientHours;
	}
	
	
}

