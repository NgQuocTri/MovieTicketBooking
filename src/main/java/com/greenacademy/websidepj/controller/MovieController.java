package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Genre;
import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.entity.MovieAge;
import com.greenacademy.websidepj.service.GenreService;
import com.greenacademy.websidepj.service.ImageService;
import com.greenacademy.websidepj.service.MovieAgeService;
import com.greenacademy.websidepj.service.MovieService;
import com.greenacademy.websidepj.service.ScheduleService;

@Controller
@RequestMapping(value="/admin")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	MovieAgeService movieAgeService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value="/{movieId}", method = RequestMethod.GET)
	public ModelAndView movieDetail(@PathVariable("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		ModelAndView model=new ModelAndView();
		model.setViewName("movieDetail");
		model.addObject("movie",movie);
		return model;
	}
	
	@RequestMapping(value="/newMovie", method=RequestMethod.GET)
	public ModelAndView getNewMovieView() {
		Movie movie=new Movie();
		List<Genre> genres = genreService.getAllGenres();
		List<MovieAge> movieAges = movieAgeService.getAllMovieAges();
		ModelAndView model = new ModelAndView();
		model.setViewName("newMovie");
		model.addObject("movie",movie);
		model.addObject("genres",genres);
		model.addObject("movieAges",movieAges);
	
		return model;
	}
	
	@RequestMapping(value="/movieList", method=RequestMethod.GET)
	public ModelAndView getMovieListView() {
		List<Movie> movies=movieService.getAllMovies();
		ModelAndView model=new ModelAndView();
		model.setViewName("movieList");
		model.addObject("movies",movies);
		return model;
	}
	
	@RequestMapping(value="/editMovie", method=RequestMethod.GET)
	public ModelAndView getEditMovieView(@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		List<Genre> genres = genreService.getAllGenres();
		List<MovieAge> movieAges = movieAgeService.getAllMovieAges();
		ModelAndView model = new ModelAndView();
		model.setViewName("editMovie");
		model.addObject("movie",movie);
		model.addObject("genres",genres);
		model.addObject("movieAges",movieAges);
		return model;
	}
	
	@RequestMapping(value="/deleteMovie", method=RequestMethod.GET)
	public ModelAndView deleteMovie(@RequestParam("movieId") Long movieId) {
		movieService.deleteMovie(movieId);
		List<Movie> movies=movieService.getAllMovies();
		ModelAndView model=new ModelAndView();
		model.setViewName("movieList");
		model.addObject("movies",movies);
		return model;
	}
	
	@RequestMapping(value="/saveMovie", method=RequestMethod.POST)
	public String saveNewMovie(@ModelAttribute("movie") Movie movieModel,HttpServletRequest request, Model model) {
		String uploadPath = request.getServletContext().getRealPath("uploads/moviePoster");
		String imageName = imageService.uploadFile(uploadPath,movieModel.getImageFile());
		String errorMessage;
		
		if(imageName != null && !imageName.isEmpty()) {
			movieModel.setImageName(imageName);
		}
		if(movieModel.getMovieId() == null) {
			errorMessage = movieService.saveMovieInfo(movieModel);
			if(errorMessage != null) {
				List<Genre> genres = genreService.getAllGenres();
				List<MovieAge> movieAges = movieAgeService.getAllMovieAges();
				model.addAttribute("errorMessage", errorMessage);
				model.addAttribute("genres",genres);
				model.addAttribute("movieAges",movieAges);
				return "newMovie";
			}
		}
		else {
			errorMessage = movieService.updateMovieInfo(movieModel);
			if(errorMessage != null) {
				List<Genre> genres = genreService.getAllGenres();
				List<MovieAge> movieAges = movieAgeService.getAllMovieAges();
				model.addAttribute("errorMessage", errorMessage);
				model.addAttribute("genres",genres);
				model.addAttribute("movieAges",movieAges);
				return "editMovie";
			}
		}
		Movie movie = movieService.getMovieByName(movieModel.getMovieName());
		
		model.addAttribute("movie",movie);
		return "scheduleList";
	}
	
	@RequestMapping(value = "/findMovie", method = RequestMethod.POST)
	public String searchMovie(@RequestParam("search") String searchValue ,HttpServletRequest request, Model model) {
		List<Movie> movies = movieService.searchMovieByName(searchValue);
		model.addAttribute("search", true);
		model.addAttribute("movies", movies);
		model.addAttribute("searchValue", searchValue);
		return "movieList";	
	}
	
	@RequestMapping(value="/createNewSchedule", method=RequestMethod.GET)
	public ModelAndView createNewDateAndTime(@RequestParam("movieId") Long movieId) {
		Movie movie=movieService.getMovieById(movieId);
		ModelAndView model=new ModelAndView();
		model.setViewName("newSchedule");
		model.addObject("movie",movie);
		return model;
	}
	
//	@RequestMapping(value="/saveSchedule", method=RequestMethod.POST)
//	public ModelAndView saveNewDateAndTime(@ModelAttribute("movie") Movie movieModel) {
//		movieService.saveSchedule(movieModel.getMovieId(),movieModel.getTransientDate(),movieModel.getTransientHour());
//		Movie movie=movieService.getMovieById(movieModel.getMovieId());
//		ModelAndView model=new ModelAndView();
//		model.setViewName("scheduleList");
//		model.addObject("movie",movie);
//		model.addObject("schedules",movie.getSchedules());
//		return model;
//	}
	
	@RequestMapping(value="/saveSchedule", method=RequestMethod.POST)
	public String saveNewDateAndTime(@ModelAttribute("movie") Movie movieModel, Model model) {
		String errorMessage = movieService.saveSchedule(movieModel.getMovieId(),movieModel.getTransientDate(),movieModel.getTransientHour());
		Movie movie=movieService.getMovieById(movieModel.getMovieId());
		
		if(errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("movie",movie);
			return "newSchedule";
		}
		model.addAttribute("movie",movie);
		return "scheduleList";
	}
	
	@RequestMapping(value="/scheduleList", method = RequestMethod.GET)
	public ModelAndView getDateAndTimeListView(@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		ModelAndView model=new ModelAndView();
		model.setViewName("scheduleList");
		model.addObject("movie",movie);
		return model;
	}
	
	@RequestMapping(value="/deleteSchedule", method=RequestMethod.GET)
	public ModelAndView deleteSchedule(@RequestParam("scheduleId") Long scheduleId,@RequestParam("movieId") Long movieId) {
		Movie movie = movieService.getMovieById(movieId);
		movieService.deleteSchedule(movie,scheduleId);
		ModelAndView model=new ModelAndView();
		model.setViewName("scheduleList");
		model.addObject("movie",movie);
		return model;
	}
}
