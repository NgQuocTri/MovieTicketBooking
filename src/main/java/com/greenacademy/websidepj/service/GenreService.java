package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Genre;
import com.greenacademy.websidepj.repository.GenreRepository;

@Transactional
@Service
public class GenreService {
	
	@Autowired
	public GenreRepository genreRepository;
	
	public List<Genre> getAllGenres(){
		return genreRepository.findAll();
	}
	
	public Genre getGenreById(Long genreId) {
		Optional<Genre> genre=genreRepository.findById(genreId);
		Genre genreEntity=genre.get();
		return genreEntity;
	}
	
	public void createGenre() {
		Genre action=new Genre();
		action.setGenreName("Hành động");
		genreRepository.save(action);
		
		Genre war=new Genre();
		war.setGenreName("Chiến tranh");
		genreRepository.save(war);
		
		Genre sci_Fi=new Genre();
		sci_Fi.setGenreName("Khoa học viễn tưởng");
		genreRepository.save(sci_Fi);
		
		Genre historicalDrama=new Genre();
		historicalDrama.setGenreName("Lịch sử");
		genreRepository.save(historicalDrama);
		
		Genre adventure=new Genre();
		adventure.setGenreName("Phiêu lưu");
		genreRepository.save(adventure);
		
		Genre comedy=new Genre();
		comedy.setGenreName("Phim hài");
		genreRepository.save(comedy);
		
		Genre horror=new Genre();
		horror.setGenreName("Kinh dị");
		genreRepository.save(horror);
		
		Genre fantasy=new Genre();
		fantasy.setGenreName("Kì ảo");
		genreRepository.save(fantasy);
		
		Genre romance=new Genre();
		romance.setGenreName("Tình cảm");
		genreRepository.save(romance);
		
		Genre cartoon=new Genre();
		cartoon.setGenreName("Hoạt hình");
		genreRepository.save(cartoon);
		
	}
}
