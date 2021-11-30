package com.greenacademy.websidepj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Feedback;
import com.greenacademy.websidepj.entity.Genre;
import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.entity.MovieAge;
import com.greenacademy.websidepj.entity.Poster;
import com.greenacademy.websidepj.entity.Price;
import com.greenacademy.websidepj.entity.Schedule;
import com.greenacademy.websidepj.entity.Seat;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.entity.TicketCategory;
import com.greenacademy.websidepj.repository.PosterRepository;
import com.greenacademy.websidepj.service.FeedbackService;
import com.greenacademy.websidepj.service.GenreService;
import com.greenacademy.websidepj.service.MovieAgeService;
import com.greenacademy.websidepj.service.MovieService;
import com.greenacademy.websidepj.service.PriceService;
import com.greenacademy.websidepj.service.ScheduleService;
import com.greenacademy.websidepj.service.SeatService;
import com.greenacademy.websidepj.service.TicketCategoryService;
import com.greenacademy.websidepj.service.TicketService;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieAgeService movieAgeService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private TicketCategoryService ticketCategoryService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private PosterRepository posterRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getHomeView() {
		java.sql.Date today = scheduleService.getToday();
		List<Movie> todayMovies = movieService.getMovieByDate(today);
		List<Movie> unfinishedMovies = movieService.getUnfinishedMovies();
		List<Poster> posters = posterRepository.findAll();
		Poster poster1 = posters.get(0);
		Poster poster2 = posters.get(1);
		Poster poster3 = posters.get(2);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-home");
		model.addObject("poster1",poster1);
		model.addObject("poster2",poster2);
		model.addObject("poster3",poster3);
		model.addObject("todayMovies",todayMovies);
		model.addObject("unfinishedMovies",unfinishedMovies);
		return model;
	}
	
	@RequestMapping(value="/todayMovies", method=RequestMethod.GET)
	public ModelAndView getMoviesByDate() {
		java.sql.Date today = scheduleService.getToday();
		List<java.sql.Date> dates = scheduleService.showDate(today, 7);
		List<Movie> moviesByDate = movieService.getMovieByDate(today);
		String datesAsString = scheduleService.convertDateToString(dates);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByCalendar");
		model.addObject("dates",dates);
		model.addObject("thisDate",today);
		model.addObject("datesAsString",datesAsString);
		model.addObject("moviesByDate",moviesByDate);
		return model;
	}
	
	@RequestMapping(value="/next7Dates", method = RequestMethod.GET)
	public ModelAndView getNext7Dates(@RequestParam("date") java.sql.Date date) {
		List<java.sql.Date> dates = scheduleService.showDate(date, 7);
		List<Movie> moviesByDate = movieService.getMovieByDate(dates.get(0));
		String datesAsString = scheduleService.convertDateToString(dates);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByCalendar");
		model.addObject("dates",dates);
		model.addObject("thisDate",dates.get(0));
		model.addObject("datesAsString",datesAsString);
		model.addObject("moviesByDate",moviesByDate);
		return model;
	}
	
	@RequestMapping(value="/previous7Dates", method = RequestMethod.GET)
	public ModelAndView getPrevious7Dates(@RequestParam("date") java.sql.Date date) {
		List<java.sql.Date> dates = scheduleService.showDate(date, -7);
		List<Movie> moviesByDate = movieService.getMovieByDate(dates.get(0));
		String datesAsString = scheduleService.convertDateToString(dates);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByCalendar");
		model.addObject("dates",dates);
		model.addObject("thisDate",dates.get(0));
		model.addObject("datesAsString",datesAsString);
		model.addObject("moviesByDate",moviesByDate);
		return model;
	}
	
	@RequestMapping(value="/movieByDate", method = RequestMethod.GET)
	public ModelAndView getMovieByDate(@RequestParam("date") java.sql.Date date,@RequestParam("dates") List<java.sql.Date> dates) {
		List<Movie> moviesByDate = movieService.getMovieByDate(date);
		String datesAsString = scheduleService.convertDateToString(dates);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByCalendar");
		model.addObject("dates",dates);
		model.addObject("thisDate",date);
		model.addObject("datesAsString",datesAsString);
		model.addObject("moviesByDate",moviesByDate);
		return model;
	}
	
	@RequestMapping(value="/movieByGenre", method = RequestMethod.GET)
	public ModelAndView getMoviesByGenre(@RequestParam("genreId") Long genreId) {
		List<Movie> moviesByGenre = movieService.getMovieByGenre(genreId);
		Genre genre = genreService.getGenreById(genreId);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByGenre");
		model.addObject("moviesByGenre",moviesByGenre);
		model.addObject("genre",genre);
		return model;
	}
	
	@RequestMapping(value="/movieByAge", method = RequestMethod.GET)
	public ModelAndView getMoviesByAge(@RequestParam("ageId") Long ageId) {
		List<Movie> moviesByAge = movieService.getMoviesByAge(ageId);
		MovieAge movieAge = movieAgeService.getMovieAgeById(ageId);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movieByAge");
		model.addObject("moviesByAge",moviesByAge);
		model.addObject("movieAge",movieAge);
		return model;
	}
	
	@RequestMapping(value = "/ticketPrice", method = RequestMethod.GET)
	public ModelAndView getTicketPrice() {
		List<Price> prices = priceService.getAllPrices();
		ModelAndView model = new ModelAndView();
		model.setViewName("user-ticketPrice");
		model.addObject("prices",prices);
		return model;
	}
	
	@RequestMapping(value = "/customerService", method = RequestMethod.GET)
	public String getCustomerServiceForm(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("feedback",feedback);
		return "user-customerService";
	}
	
	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	public String saveFeedback(@ModelAttribute("feeback") Feedback feedback,Model model) {
		String error = feedbackService.saveFeedback(feedback);
		if (error != null) {
			model.addAttribute("error",error);
		}
		else {
			model.addAttribute("succes","Bạn đã gửi thành công");
		}
		model.addAttribute("feedback",new Feedback());
		return "user-customerService";
	}
	
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public ModelAndView getMovieDetail(@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		ModelAndView model = new ModelAndView();
		model.setViewName("user-movie-detail");
		model.addObject("movie", movie);
		System.out.println("Model: "+model);
		return model;
	}

	@RequestMapping(value = "/bookTicket", method = RequestMethod.GET)
	public ModelAndView getBookTicket(@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		List<Schedule> listSche = scheduleService.getUnfinishedSchedules();
		List<Seat> listSeat = seatService.getAllSeats();
		List<TicketCategory> tickCatList = ticketCategoryService.getAllTicketCategories();

		Set<Date> dates = new HashSet<Date>();
		for (Schedule schedule : listSche) {
			dates.add(schedule.getDate());
		}

		Set<String> hours = new HashSet<String>();
		for (Schedule schedule : listSche) {
			hours.add(schedule.getHour());
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("user-bookedTicket");
		model.addObject("movie", movie);
		model.addObject("dates", dates);
		model.addObject("hours", hours);
		model.addObject("listSeat", listSeat);
		model.addObject("tickCatList", tickCatList);
		return model;
	}

	
	@RequestMapping(value = "/cartTicket", method = RequestMethod.POST)
	public String listTicketHandle(@RequestParam("date_sql") String dateStr, @RequestParam("hour") String hour,
			@RequestParam("seatId") Long seatId, @RequestParam("catId") Long catId) {

		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); Date date =
		 * null; try { date = formatter.parse(dateStr); } catch (ParseException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * java.sql.Date date_sql = new java.sql.Date(date.getTime());
		 */

		/*
		 * Schedule date_order = scheduleService.getScheduleByDateAndHour(date_sql,
		 * hour); //Schedule hour_order = scheduleService.getScheduleByHour(hour); Seat
		 * row_order = seatService.getSeatByRow(row, chair);
		 */
		// Seat chair_order = seatService.getSeatByChair(chair);

		/*
		 * ModelAndView model = new ModelAndView();
		 * model.setViewName("user-viewBookTicket"); model.addObject("date_order",
		 * date_order); //model.addObject("hour_order", hour_order);
		 * model.addObject("row_order", row_order); //model.addObject("chair_order",
		 * chair_order);
		 */
		return "redirect:/home/viewTicketInfo?date_sql=" + dateStr + "&hour=" + hour + "&seatId=" + seatId 
				+ "&catId=" + catId;
	}

	@RequestMapping(value = "/viewTicketInfo", method = RequestMethod.GET)
	public ModelAndView getTicketInfo(@RequestParam("date_sql") String dateStr, @RequestParam("hour") String hour,
			@RequestParam("seatId") Long seatId, @RequestParam("catId") Long catId) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date date_sql = new java.sql.Date(date.getTime());
		TicketCategory catTick = ticketCategoryService.getTicketId(catId);
		Seat seat_order = seatService.getSeatById(seatId);
		Schedule date_order = scheduleService.getScheduleByDateAndHour(date_sql, hour);

		ModelAndView model = new ModelAndView();
		model.setViewName("user-viewBookTicket");
		model.addObject("date_order", date_order);
		model.addObject("seat_order", seat_order);
		model.addObject("catTick", catTick);

		return model;
	}

	// chọn ngày chiếu
	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
	public ModelAndView getDate(@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		List<Schedule> notSameSchedules = scheduleService.deleteSameSchedule(movie.getSchedules());
		ModelAndView model = new ModelAndView();
		model.setViewName("user-getDate");
		model.addObject("movie", movie);
		model.addObject("schedules", notSameSchedules);
		return model;
	}
	
	// chọn giờ chiếu
	@RequestMapping(value = "/chooseHour", method = RequestMethod.POST)
	public ModelAndView chooseHour(@RequestParam("movieId") Long movieId,  @RequestParam("date") String dateString) {
		Movie movie = movieService.getMovieById(movieId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date date_sql = new java.sql.Date(date.getTime());
		
		List<Schedule> schedules = scheduleService.getSchedulesByDate(date_sql,movie);

		ModelAndView model = new ModelAndView();
		model.setViewName("user-getHour");
		model.addObject("movie", movie);
		model.addObject("date_sql", date_sql);
		model.addObject("schedules", schedules);

		return model;
	}
	
	// chọn chỗ ngồi và loại vé
	@RequestMapping(value = "/chooseSeatAndCatID", method = RequestMethod.POST)
	public ModelAndView chooseSeatAndCatID(@RequestParam("movieId") Long movieId, 
			@RequestParam("scheduleId") Long scheduleId) {
		 
		Movie movie = movieService.getMovieById(movieId);
		Schedule schedule = scheduleService.getScheduleById(scheduleId);
		List<Seat> activeSeats = seatService.getAllActiveSeats(schedule);
		List<Seat> inactiveSeats = seatService.getAllInactiveSeats(schedule);
		
		Set<Character> rows = new HashSet<Character>();
		for(char i='A';i<='H';i++) {
			rows.add(i);
		}
		Set<Integer> columns = new HashSet<Integer>();
		for (int i=1;i<=20;i++) {
			columns.add(i);
		}
 		
		List<TicketCategory> ticketCategories = ticketCategoryService.getAllTicketCategories();
		ModelAndView model = new ModelAndView();
		model.setViewName("user-bookedTicket");
		model.addObject("movie", movie);
		model.addObject("schedule", schedule);

		model.addObject("activeSeats", activeSeats);
		model.addObject("inactiveSeats",inactiveSeats);
		model.addObject("ticketCategories", ticketCategories);
		model.addObject("rows", rows);
		model.addObject("columns", columns);

		return model;
	}
		
	@RequestMapping(value = "/viewBookedTicketInfo", method = RequestMethod.POST)
	public ModelAndView viewBookedTicketInfo(@RequestParam("movieId") Long movieId, 
			@RequestParam("scheduleId") Long scheduleId, @RequestParam("chair") String chair,
			@RequestParam("catId") Long catId, HttpServletRequest request) {
		Seat seat = seatService.getSeatByChair(chair);
		Ticket ticket = ticketService.newBookTicket(request, movieId, scheduleId, seat.getSeatId(), catId);
		String hour = scheduleService.getHour(scheduleId);
		TicketCategory ticketCategory = ticketCategoryService.getTicketCategoryById(catId);
		Price price = priceService.getPriceByTicketCategoryAndHour(hour, ticketCategory);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("user-viewBookedTicket");
		model.addObject("ticket", ticket);
		model.addObject("price", price);
		return model;
	}
	
	@RequestMapping(value = "/searchMovie", method = RequestMethod.POST)
	public String searchMovieOfUser(@RequestParam("search") String searchValue,Model model) {
		List<Movie> movies = movieService.searchMovieByName(searchValue);
		List<Poster> posters = posterRepository.findAll();
		Poster poster1 = posters.get(0);
		Poster poster2 = posters.get(1);
		Poster poster3 = posters.get(2);
		model.addAttribute("poster1",poster1);
		model.addAttribute("poster2",poster2);
		model.addAttribute("poster3",poster3);
		model.addAttribute("movies", movies);
		model.addAttribute("searchValue", searchValue);
		return "user-searchMovie";	
	}
}
