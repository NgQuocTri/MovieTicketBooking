package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.TicketCategory;
import com.greenacademy.websidepj.repository.TicketCategoryRepository;


@Service
@Transactional
public class TicketCategoryService {
	@Autowired
	private TicketCategoryRepository ticketCategoryRepository;
	
	public List<TicketCategory> getAllTicketCategories(){
		return ticketCategoryRepository.findAll();
	}
	
	public TicketCategory getTicketId(Long catId) {
		Optional<TicketCategory> cat = ticketCategoryRepository.findById(catId);
		TicketCategory catTick = cat.get();
		return catTick;
	}
	
	public TicketCategory saveTicketCategory(TicketCategory ticketCatModel) {
		TicketCategory ticketCatEntity = new TicketCategory();
		ticketCatEntity.setCatName(ticketCatModel.getCatName());
		ticketCategoryRepository.save(ticketCatEntity);
		
		return ticketCatEntity;
	}
	
	public TicketCategory updateTicketCategory(TicketCategory ticketCatModel) {
		Optional<TicketCategory> ticket = ticketCategoryRepository.findById(ticketCatModel.getCatId());
		TicketCategory ticketCatEntity = ticket.get();
		ticketCatEntity.setCatName(ticketCatModel.getCatName());
		ticketCategoryRepository.save(ticketCatEntity);
		
		return ticketCatEntity;
	}
	
	public boolean deleteTicketCategory(Long id) {
		boolean result = false;
		if (ticketCategoryRepository.findById(id) != null) {
			ticketCategoryRepository.deleteById(id);
			result = true;
		}
		return result;
	}
	
	public TicketCategory getTicketCategoryById(Long ticketCategoryId) {
		Optional<TicketCategory> ticketCategory=ticketCategoryRepository.findById(ticketCategoryId);
		TicketCategory ticketCategoryEntity=ticketCategory.get();
		return ticketCategoryEntity;
	}
	
	public void createTicketCategory() {
		TicketCategory children_elderly=new TicketCategory();
		children_elderly.setCatName("Trẻ em & Người cao tuổi");
		ticketCategoryRepository.save(children_elderly);
		
		TicketCategory student_u22=new TicketCategory();
		student_u22.setCatName("Học sinh & U22");
		ticketCategoryRepository.save(student_u22);
		
		TicketCategory adult=new TicketCategory();
		adult.setCatName("Người lớn");
		ticketCategoryRepository.save(adult);
	}
}
