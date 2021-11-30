package com.greenacademy.websidepj.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Genre;
import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.entity.MovieAge;
import com.greenacademy.websidepj.entity.Schedule;
import com.greenacademy.websidepj.repository.MovieRepository;
import com.greenacademy.websidepj.repository.ScheduleRepository;


@Service
@Transactional
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	MovieAgeService movieAgeService;
	
	@Autowired
	TicketService ticketService;
	
	public Movie getMovieByName(String movieName) {
		return movieRepository.findByMovieName(movieName);
	}
	
	public Movie getMovieById(Long movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		Movie movieEntity = movie.get();
		return movieEntity;
	}
	
	public List<Movie> getAllMovies(){
		return movieRepository.findAll();
	}
	
	public List<Movie> getUnfinishedMovies(){
		List<Movie> allMovies = movieRepository.findAll();
		List<Movie> unfinisedMovies = new ArrayList<Movie>();
		for (Movie movie : allMovies) {
			for (Schedule schedule : movie.getSchedules()) {
				if (schedule.getTransientStatus().equalsIgnoreCase("Đã chiếu") == false) {
					unfinisedMovies.add(movie);
					break;
				}
			}
		}
		return unfinisedMovies;
	}
	
	public List<Movie> getMovieByGenre(Long genreId) {
		List<Movie> movies = this.getUnfinishedMovies();
		List<Movie> MoviesByGenre = new ArrayList<Movie>();
		for(Movie movie : movies) {
			if(movie.getGenre().getGenreId().equals(genreId)) MoviesByGenre.add(movie);
		}
		return MoviesByGenre;
	}
	
	public List<Movie> getMoviesByAge(Long ageId){
		List<Movie> allMovies = this.getUnfinishedMovies();
		List<Movie> moviesByAge = new ArrayList<Movie>();
		for (Movie movie : allMovies) {
			if (movie.getMovieAge().getAgeId() == ageId)  moviesByAge.add(movie);
		}
		return moviesByAge;
	}
	
	public List<Movie> getMovieByDate(Date date){
		List<Movie> allMovies = this.getUnfinishedMovies();
		List<Movie> thisDateMovies = new ArrayList<Movie>();
		for (Movie movie : allMovies) {
			for(Schedule schedule : movie.getSchedules()) {
				if (schedule.getDate().toString().equals(date.toString())) {
					thisDateMovies.add(movie);
					break;
				}
			}
		}
		return thisDateMovies;
	}
	
	public List<Movie> searchMovieByName(String movieName){
		return movieRepository.searchByName(movieName);
	}
	
	public String saveMovieInfo(Movie movieModel) {
		
		if (movieModel.getMovieName().isEmpty()) {
			return "Vui lòng điền tên phim.";
		}
		if (movieModel.getDescription() == null) {
			return "Vui lòng điền phần mô tả.";
		}
		if (movieRepository.findByMovieName(movieModel.getMovieName()) != null) {
			return "Tên phim này đã tồn tại.";
		}
		if (movieModel.getTrailerLink().isEmpty()) {
			return "Vui lòng điền đường dẫn trailer.";
		}
		
		movieRepository.save(movieModel);
		return null;
	}
	
	public String updateMovieInfo(Movie movieModel) {
		Optional<Movie> movie=movieRepository.findById(movieModel.getMovieId());
		Movie movieEntity=movie.get();
		
		if(movieModel.getMovieName().isEmpty()) {
			return "Vui lòng điền tên phim.";
		}
		if(movieModel.getDescription() == null) {
			return "Vui lòng điền phần mô tả.";
		}
		if (movieModel.getTrailerLink().isEmpty()) {
			return "Vui lòng điền đường dẫn trailer.";
		}
					
		if(movieModel.getGenre()!=null) {
			Genre genre=movieModel.getGenre();
			movieEntity.setGenre(genre);
		}
		if(movieModel.getMovieAge()!=null) {
			MovieAge movieAge=movieModel.getMovieAge();
			movieEntity.setMovieAge(movieAge);
		}
		movieEntity.setMovieName(movieModel.getMovieName());
		movieEntity.setImageName(movieModel.getImageName()); 
		movieEntity.setDescription(movieModel.getDescription());
		movieEntity.setTrailerLink(movieModel.getTrailerLink());
		movieRepository.save(movieEntity);
		return null;
	}
	
	public String saveSchedule(Long movieId, Date transientDate,String transientHour) {
		
		if(transientDate == null) {
			return "Vui lòng chọn ngày!";
		}
		if(transientHour == null) {
			return "Vui lòng chọn giờ!";
		}
		
		Optional<Movie> movie=movieRepository.findById(movieId);
		Movie movieEntity=movie.get();
		Schedule schedule=scheduleService.saveSchedule(transientDate, transientHour);
		schedule.setMovie(movieEntity);
		movieEntity.getSchedules().add(schedule);
		movieRepository.save(movieEntity);
		return null;
	}
	
	public Movie deleteSchedule(Movie movieEntity,Long scheduleId) {
		List<Schedule> schedules=movieEntity.getSchedules();
		List<Schedule> newSchedules=new ArrayList<Schedule>();
		for (Schedule schedule : schedules) {
			if(schedule.getScheduleId() != scheduleId) {
				newSchedules.add(schedule);
			}
			else {
				scheduleRepository.delete(schedule);
			}
		}
		movieEntity.setSchedules(newSchedules);
		movieRepository.save(movieEntity);
		return movieEntity;
	}
	
	public Movie deleteMovie(Long movieId) {
		Movie movieEntity = this.getMovieById(movieId);
		for (Schedule schedule : movieEntity.getSchedules()) scheduleRepository.delete(schedule);
		movieRepository.delete(movieEntity);
		return movieEntity;
	}
	
	public void createDefaultMovies() {
		
		//Kinh di
		//Movie1
		Movie theWitch = new Movie();
		theWitch.setMovieName("Phù thủy");
		theWitch.setGenre(genreService.getGenreById((long) 7));
		theWitch.setMovieAge(movieAgeService.getMovieAgeById((long) 4));
		theWitch.setImageName("TheWitch.jpg");
		theWitch.setDescription("Một gia đình đạo Tin Lành chạm chán với thế lực ma quỷ trong khu rừng gần trang trại ở vùng New England.");
		theWitch.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/iQXmlf3Sefg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(theWitch);
		
		//Movie2
		Movie hamCaMap = new Movie();
		hamCaMap.setMovieName("Hàm cá mập");
		hamCaMap.setGenre(genreService.getGenreById((long) 7));
		hamCaMap.setMovieAge(movieAgeService.getMovieAgeById((long) 4));
		hamCaMap.setImageName("HamCaMap.jpg");
		hamCaMap.setDescription("Nội dung kể về cuộc tấn công người của một giống cá mập trắng khổng lồ ở bãi biển New England (Mỹ).");
		hamCaMap.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/U1fu_sA7XhE\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(hamCaMap);
		
		//Hanh dong
		//Movie3
		Movie avengersEndgame = new Movie();
		avengersEndgame.setMovieName("Avengers: Hồi kết");
		avengersEndgame.setGenre(genreService.getGenreById((long) 1));
		avengersEndgame.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		avengersEndgame.setImageName("AvengersEndgame.jpg");
		avengersEndgame.setDescription("Trong phim, các thành viên còn sống sót của nhóm Avengers và các đồng minh của họ hợp tác với nhau để đảo ngược thiệt hại do Thanos gây ra trong Avengers: Cuộc chiến vô cực");
		avengersEndgame.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/TcMBFSGVi1c\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(avengersEndgame);
		
		//Movie4
		Movie madMax = new Movie();
		madMax.setMovieName("Max điên cuồng");
		madMax.setGenre(genreService.getGenreById((long) 1));
		madMax.setMovieAge(movieAgeService.getMovieAgeById((long) 3));
		madMax.setImageName("MadMax.jpg");
		madMax.setDescription("Phim mới lấy bối cảnh thế giới ở thời kỳ văn minh nhân loại đã sụp đổ, con người trở nên mất nhân tính và cuồng điên trong những trận chiến để giành giật sự sống, các nhu yếu phẩm như nước và xăng.");
		madMax.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/hEJnMQG9ev8\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(madMax);
		
		//Chien tranh
		//Movie5
		Movie midway = new Movie();
		midway.setMovieName("Trận chiến Midway");
		midway.setGenre(genreService.getGenreById((long) 2));
		midway.setMovieAge(movieAgeService.getMovieAgeById((long) 2));
		midway.setImageName("Midway.jpg");
		midway.setDescription("Bộ phim Midway tham vọng tái hiện trận Midway lừng lẫy giữa quân Mỹ và Nhật trong Thế chiến 2 với độ chân thực lịch sử tối đa.");
		midway.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/BfTYY_pac8o\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(midway);
		
		//Movie6
		Movie movie1917 = new Movie();
		movie1917.setMovieName("1917");
		movie1917.setGenre(genreService.getGenreById((long) 2));
		movie1917.setMovieAge(movieAgeService.getMovieAgeById((long) 2));
		movie1917.setImageName("1917.jpg");
		movie1917.setDescription("1917 dựa trên một câu chuyện có thật do ông nội Alfred Mendes kể cho cháu Sam Mendes,[8] theo chân hai chàng lính trẻ người Anh ở đỉnh điểm Thế chiến I được giao nhiệm vụ chuyển một thông điệp đến Tiểu đoàn 2, với nội dung cảnh báo về cuộc phục kích sắp tới của quân đội Đức nếu họ thực hiện một cuộc tấn công đã lên kế hoạch từ trước.");
		movie1917.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/YqNYrYUiMfg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(movie1917);
		
		//Khoa hoc vien tuong
		//Movie7
		Movie starWar = new Movie();
		starWar.setMovieName("Chiến tranh giữa các vì sao");
		starWar.setGenre(genreService.getGenreById((long) 3));
		starWar.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		starWar.setImageName("StarWar.jpg");
		starWar.setDescription("Loạt phim kể về những cuộc phiêu lưu của các nhân vật khác nhau tại một thiên hà xa xôi.");
		starWar.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/8Qn_spdM5Zg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(starWar);
		
		//Movie8
		Movie gravity = new Movie();
		gravity.setMovieName("Cuộc chiến không trọng lực");
		gravity.setGenre(genreService.getGenreById((long) 3));
		gravity.setMovieAge(movieAgeService.getMovieAgeById((long) 2));
		gravity.setImageName("Gravity.jpg");
		gravity.setDescription("Ryan Stone (Sandra Bullock) và phi hành gia lão luyện Matt Kowalsky (George Clooney) bị cô độc ngoài không gian vũ trụ tối tăm vì mảnh vỡ từ một vệ tinh khác đã phá hỏng tàu của họ.");
		gravity.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/OiTiKOy59o4\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(gravity);
		
		//Lich su
		//Movie9
		Movie hanoi12NgayDem = new Movie();
		hanoi12NgayDem.setMovieName("Hà Nội 12 ngày đêm");
		hanoi12NgayDem.setGenre(genreService.getGenreById((long) 4));
		hanoi12NgayDem.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		hanoi12NgayDem.setImageName("Ha Noi 12 ngay dem.jpg");
		hanoi12NgayDem.setDescription("Bộ phim lấy đề tài chiến tranh Cách mạng và đã giới thiệu được nghệ thuật làm phim cổ điển Việt Nam.");
		hanoi12NgayDem.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/8dyRfYWF_Dc\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(hanoi12NgayDem);
		
		//Movie10
		Movie giaiPhongSaiGon = new Movie();
		giaiPhongSaiGon.setMovieName("Giải phóng Sài Gòn");
		giaiPhongSaiGon.setGenre(genreService.getGenreById((long) 4));
		giaiPhongSaiGon.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		giaiPhongSaiGon.setImageName("Giai phong Sai Gon.jpg");
		giaiPhongSaiGon.setDescription("Phim ghi lại một số sự kiện lịch sử chính trong tiến trình Quân Giải phóng tiến vào thành phố Sài Gòn.");
		giaiPhongSaiGon.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/eQRWIysR3r0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(giaiPhongSaiGon);
		
		//Phieu luu
		//Movie11
		Movie kingKong = new Movie();
		kingKong.setMovieName("King Kong: Đảo Đầu Lâu");
		kingKong.setGenre(genreService.getGenreById((long) 5));
		kingKong.setMovieAge(movieAgeService.getMovieAgeById((long) 3));
		kingKong.setImageName("kong skull island.jpg");
		kingKong.setDescription("Vào năm 1944, trong Thế chiến II, trên một đảo hoang ở đâu đó giữa Thái Bình Dương, hai phi công một người Mỹ một người Nhật cùng bị rơi xuống đảo hoang.");
		kingKong.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/44LdLqgOpjo\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(kingKong);
		
		//Movie12
		Movie tombRaider = new Movie();
		tombRaider.setMovieName("Tomb Raider: Huyền thoại bắt đầu");
		tombRaider.setGenre(genreService.getGenreById((long) 5));
		tombRaider.setMovieAge(movieAgeService.getMovieAgeById((long) 4));
		tombRaider.setImageName("Tomb Raider.jpg");
		tombRaider.setDescription("Bộ phim có sự tham gia của Alicia Vikander trong vai Lara Croft, trong đó cô dấn thân vào cuộc hành trình nguy hiểm để đến đích cuối cùng của cha cô, hy vọng giải quyết được bí ẩn về sự mất tích của ông.");
		tombRaider.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/8ndhidEmUbI\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(tombRaider);
		
		//Phim hai
		//Movie13
		Movie helloCoBa = new Movie();
		helloCoBa.setMovieName("Hello cô Ba");
		helloCoBa.setGenre(genreService.getGenreById((long) 6));
		helloCoBa.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		helloCoBa.setImageName("Hello co Ba.jpg");
		helloCoBa.setDescription("Đây là bộ phim với chủ đề nông thôn Việt Nam, kể về một câu chuyện ly kỳ tại một vùng quê nhỏ và tạo hình của các nghệ sĩ trong phim đơn giản, gần gũi, đậm chất người nông dân với không khí nhà quê, đồng ruộng.[2]");
		helloCoBa.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/65xjDU20Cr8\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(helloCoBa);
		
		//Movie14
		Movie coDauDaiChien = new Movie();
		coDauDaiChien.setMovieName("Cô dâu đại chiến");
		coDauDaiChien.setGenre(genreService.getGenreById((long) 6));
		coDauDaiChien.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		coDauDaiChien.setImageName("Co dau dai chien.jpg");
		coDauDaiChien.setDescription("Nhân vật nam chính trong phim là một anh chàng giám đốc giàu có tên Thái, là người rất bảnh trai và đào hoa, anh luôn muốn chinh phục tất cả con gái đẹp xuất hiện trước mặt mình.");
		coDauDaiChien.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/CSzrJ6aj1Nc\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(coDauDaiChien);
		
		//Ky ao
		//Movie15
		Movie luaPhat = new Movie();
		luaPhat.setMovieName("Lửa Phật");
		luaPhat.setGenre(genreService.getGenreById((long) 8));
		luaPhat.setMovieAge(movieAgeService.getMovieAgeById((long) 4));
		luaPhat.setImageName("Lua Phat.jpg");
		luaPhat.setDescription("Lửa Phật được khởi chiếu vào ngày 22 tháng 8 năm 2013, đây còn được biết là bộ phim hành động giả tưởng đầu tiên của Việt Nam.");
		luaPhat.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/9srRYC_gwEM\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(luaPhat);
		
		//Movie16
		Movie lastAirbender = new Movie();
		lastAirbender.setMovieName("Tiết khí sư cuối cùng");
		lastAirbender.setGenre(genreService.getGenreById((long) 8));
		lastAirbender.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		lastAirbender.setImageName("Last Airbender.jpg");
		lastAirbender.setDescription("Bộ phim kể về cuộc phiêu lưu của nhân vật chính mười hai tuổi Aang và nhóm bạn, những người phải mang hòa bình và sự thống nhất thế giới khỏi cuộc chiến xâm lược đế quốc của Hỏa Quốc với ba quốc gia khác.");
		lastAirbender.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/-egQ79OrYCs\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(lastAirbender);
		
		//Tinh cam
		//Movie17
		Movie meBeforeYou = new Movie();
		meBeforeYou.setMovieName("Trước ngày em đến");
		meBeforeYou.setGenre(genreService.getGenreById((long) 9));
		meBeforeYou.setMovieAge(movieAgeService.getMovieAgeById((long) 3));
		meBeforeYou.setImageName("Me before you.jpg");
		meBeforeYou.setDescription("Bộ phim mở đầu bằng biến cố kinh hoàng xảy ra với nhân vật nam chính Will Traynor. Từ người thừa kế giàu có, đẹp trai, tài giỏi, yêu thể thao và du lịch, anh bị một chiếc xe môtô tông trúng, khiến phải sống cuộc đời tật nguyền vĩnh viễn.");
		meBeforeYou.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/Eh993__rOxA\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(meBeforeYou);
		
		//Movie18
		Movie matBiec = new Movie();
		matBiec.setMovieName("Bắt biếc");
		matBiec.setGenre(genreService.getGenreById((long) 9));
		matBiec.setMovieAge(movieAgeService.getMovieAgeById((long) 3));
		matBiec.setImageName("Mat biec.jpg");
		matBiec.setDescription("Mắt biếc xoay quanh mối tình đơn phương của Ngạn với Hà Lan, cô bạn gái có cặp mắt hút hồn nhưng cá tính bướng bỉnh.");
		matBiec.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/ITlQ0oU7tDA\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(matBiec);
		
		//Hoat hinh
		//Movie19
		Movie frozen = new Movie();
		frozen.setMovieName("Nữ hoàng băng giá");
		frozen.setGenre(genreService.getGenreById((long) 10));
		frozen.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		frozen.setImageName("Frozen.jpg");
		frozen.setDescription("Lấy cảm hứng từ câu chuyện cổ tích Bà chúa Tuyết của nhà văn Hans Christian Andersen, bộ phim kể về một nàng công chúa dũng cảm lên đường dấn thân vào một cuộc hành trình gian khó với một anh chàng miền núi cường tráng, dễ rung động nhưng ban đầu hơi thô lỗ, cùng chú tuần lộc trung thành của mình và một chàng người tuyết vui nhộn.");
		frozen.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/TbQm5doF_Uc\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(frozen);
		
		//Movie20
		Movie kimiNoNawa = new Movie();
		kimiNoNawa.setMovieName("Tên em là gì?");
		kimiNoNawa.setGenre(genreService.getGenreById((long) 10));
		kimiNoNawa.setMovieAge(movieAgeService.getMovieAgeById((long) 1));
		kimiNoNawa.setImageName("Kimi no Nawa.jpg");
		kimiNoNawa.setDescription("Bộ phim kể về Mitsuha – nữ sinh trung học buồn chán với cuộc sống tẻ nhạt ở vùng thôn quê – và Taki – một chàng trai Tokyo – vì một lý do nào đó bị hoán đổi cơ thể trong khi sao chổi thiên niên kỉ đang đến gần.");
		kimiNoNawa.setTrailerLink("<iframe width=\"900\" height=\"400\" src=\"https://www.youtube.com/embed/NooIc3dMncc\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
		movieRepository.save(kimiNoNawa);
	}
}
