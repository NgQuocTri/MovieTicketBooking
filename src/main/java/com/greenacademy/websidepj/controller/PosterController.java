package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.greenacademy.websidepj.entity.Poster;
import com.greenacademy.websidepj.repository.PosterRepository;
import com.greenacademy.websidepj.service.ImageService;
import com.greenacademy.websidepj.service.PosterService;

@Controller
@RequestMapping(value="/admin")
public class PosterController {
	
	@Autowired
	private PosterRepository posterRepository;
	@Autowired
	private PosterService posterService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/posterList", method = RequestMethod.GET)
	public String posterListView(Model model) {
		List<Poster> posters = posterRepository.findAll();
		model.addAttribute("posterNumber",posters.size());
		model.addAttribute("posters",posters);
		return "admin-posterList";
	}
	
	@RequestMapping(value="/deletePoster", method = RequestMethod.GET)
	public String deletePoster(@RequestParam("posterId") Long posterId,Model model) {
		Poster poster = posterService.getPosterById(posterId);
		posterService.deletePoster(poster);
		List<Poster> posters = posterRepository.findAll();
		model.addAttribute("posterNumber",posters.size());
		model.addAttribute("posters",posters);
		return "admin-posterList";
	}
	
	@RequestMapping(value="/addPoster", method = RequestMethod.GET)
	public String addPoster(Model model) {
		model.addAttribute("imageFile",new Poster().getImageFile());
		return "admin-newPoster";
	}
	
	@RequestMapping(value="/savePoster", method = RequestMethod.POST)
	public String savePoster(MultipartFile imageFile,HttpServletRequest request,Model model) {
		String uploadPath = request.getServletContext().getRealPath("uploads/cinemaPoster");
		String imageName = imageService.uploadFile(uploadPath, imageFile);
		if (imageName != null && !imageName.isEmpty()) {
			posterService.savePoster(imageName);
		}
		List<Poster> posters = posterRepository.findAll();
		model.addAttribute("posterNumber",posters.size());
		model.addAttribute("posters",posters);
		return "admin-posterList";
	}
}
