package com.greenacademy.websidepj;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenacademy.websidepj.service.FeedbackService;
import com.greenacademy.websidepj.service.GenreService;
import com.greenacademy.websidepj.service.MovieAgeService;
import com.greenacademy.websidepj.service.MovieService;
import com.greenacademy.websidepj.service.PosterService;
import com.greenacademy.websidepj.service.PriceService;
import com.greenacademy.websidepj.service.ScheduleService;
import com.greenacademy.websidepj.service.SeatService;
import com.greenacademy.websidepj.service.TicketCategoryService;
import com.greenacademy.websidepj.service.UserService;

@Component
public class InitializationLoader {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private MovieAgeService movieAgeService;
	
	@Autowired
	private TicketCategoryService ticketCategoryService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private PosterService posterService;
	
	@Autowired
	private FeedbackService feedbackService;

	@PostConstruct
	public void init() {
		try {
			if (userService.getByUserName("admin") == null) {
				userService.createDefaultAdmin();
			}
			if (userService.getByUserName("user") == null) {
				userService.createDefaultUser();
			}
			if(genreService.getAllGenres().size()==0) {
				genreService.createGenre();
			}
			if(movieAgeService.getAllMovieAges().size()==0) {
				movieAgeService.createMovieAge();
			}
			if(ticketCategoryService.getAllTicketCategories().size()==0) {
				ticketCategoryService.createTicketCategory();
			}
			if(priceService.getAllPrices().size()==0) {
				priceService.createPrice();
			}
			if(seatService.getAllSeats().size()==0) { 
				seatService.createSeat(); 
			}
			if(movieService.getAllMovies().size() == 0) {
				movieService.createDefaultMovies();
			}
			if (scheduleService.getAllSchedules().size() == 0) {
				scheduleService.creatDefaultSchedules();
			}
			if (posterService.getAllPosters().size() == 0) {
				posterService.createDefaultPosters();
			}
			if (feedbackService.getAllFeedbacks().size() == 0) {
				feedbackService.createDefaultFeedback();
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
