package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Poster;
import com.greenacademy.websidepj.repository.PosterRepository;


@Service
@Transactional
public class PosterService {
	
	@Autowired
	private PosterRepository posterRepository;
	
	public List<Poster> getAllPosters(){
		return posterRepository.findAll();
	}
	
	public Poster getPosterById(Long posterId) {
		Optional<Poster> posterEntity = posterRepository.findById(posterId);
		return posterEntity.get();
	}
	
	public void deletePoster(Poster poster) {
		posterRepository.delete(poster);
	}
	
	public void savePoster(String imageName) {
		Poster poster = new Poster();
		poster.setImageName(imageName);
		posterRepository.save(poster);
	}
	
	public void createDefaultPosters() {
		Poster poster1 = new Poster();
		poster1.setImageName("giam-gia.jpg");
		posterRepository.save(poster1);
		
		Poster poster2 = new Poster();
		poster2.setImageName("loi-nguyen.jpg");
		posterRepository.save(poster2);
		
		Poster poster3 = new Poster();
		poster3.setImageName("viet-han.jpg");
		posterRepository.save(poster3);
	}
}
