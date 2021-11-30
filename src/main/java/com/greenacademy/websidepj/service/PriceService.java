package com.greenacademy.websidepj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Price;
import com.greenacademy.websidepj.entity.TicketCategory;
import com.greenacademy.websidepj.repository.PriceRepository;

@Service
@Transactional
public class PriceService {
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	TicketCategoryService ticketCategoryService;
	
	public List<Price> getAllPrices() {
		return priceRepository.findAll();
	}
	
	public List<Price> searchByTicketCat(String catName){
		return priceRepository.searchByTicketCat(catName);
	}
	
	public Price getPriceById(Long priceId) {
		Optional<Price> price=priceRepository.findById(priceId);
		Price priceEntity=price.get();
		return priceEntity;
	}
	
	/*
	 * public Price getPrice(Long catId) { return priceRepository.getPrice(catId); }
	 */
	
	public Price savePrice(Price priceModel) {
		Price priceEntity=new Price();
		priceEntity.setUnitPrice(priceModel.getUnitPrice());
		priceEntity.setFromHour(priceModel.getFromHour());
		priceEntity.setToHour(priceModel.getToHour());
		priceEntity.setTicketCategory(priceModel.getTicketCategory());
		priceRepository.save(priceEntity);
		return priceEntity;
	}
	
	public Price updatePrice(Price priceModel) {
		Price priceEntity=new Price();
		priceEntity.setPriceId(priceModel.getPriceId());
		priceEntity.setUnitPrice(priceModel.getUnitPrice());
		priceEntity.setFromHour(priceModel.getFromHour());
		priceEntity.setToHour(priceModel.getToHour());
		priceEntity.setTicketCategory(priceModel.getTicketCategory());
		priceRepository.save(priceEntity);
		return priceEntity;
	}
	
	public Price deletePrice(Long priceId) {
		Price priceEntity=new Price();
		priceEntity.setPriceId(priceId);
		priceRepository.delete(priceEntity);
		return priceEntity;
	}
	
	public Price getPriceByTicketCategoryAndHour(String hour,TicketCategory ticketCategory){
		List<Price> availablePrices=getAllPrices();
		List<Price> lookedPrices=new ArrayList<Price>();
		for (Price availablePrice : availablePrices) {
			if(availablePrice.getTicketCategory()==ticketCategory) lookedPrices.add(availablePrice);
		}
		
		String hStr=hour.split(":")[0];
		int h=Integer.parseInt(hStr);
		String mStr=hour.split(":")[1];
		int m=Integer.parseInt(mStr);
		int movieSeconds=h*3600+m*60;
		
		for(Price lookedPrice : lookedPrices) {
			if(lookedPrice.getToHour()-lookedPrice.getFromHour()>0 &&
					movieSeconds>=lookedPrice.getFromHour()*3600 &&	movieSeconds<lookedPrice.getToHour()*3600
				) {
				return lookedPrice;
			}
			if(		lookedPrice.getToHour()-lookedPrice.getFromHour()<0 &&
					(movieSeconds>=lookedPrice.getToHour()*3600 ||	movieSeconds<lookedPrice.getFromHour()*3600)
				) {
				return lookedPrice;
			}
		}
		return null;
	}
	
	public void createPrice() {
		//Create ticket Category 1
		Price children_elderly_AM=new Price();
		children_elderly_AM.setFromHour(5);
		children_elderly_AM.setToHour(17);
		TicketCategory ticketCategoryId1=ticketCategoryService.getTicketCategoryById((long) 1);
		children_elderly_AM.setTicketCategory(ticketCategoryId1);
		children_elderly_AM.setUnitPrice((long) 50000);
		priceRepository.save(children_elderly_AM);
		
		Price children_elderly_PM=new Price();
		children_elderly_PM.setFromHour(17);
		children_elderly_PM.setToHour(5);
		children_elderly_PM.setTicketCategory(ticketCategoryId1);
		children_elderly_PM.setUnitPrice((long) 70000);
		priceRepository.save(children_elderly_PM);
		
		//Create ticket category 2
		Price student_AM=new Price();
		student_AM.setFromHour(5);
		student_AM.setToHour(17);
		TicketCategory ticketCategoryId2=ticketCategoryService.getTicketCategoryById((long) 2);
		student_AM.setTicketCategory(ticketCategoryId2);
		student_AM.setUnitPrice((long) 45000);
		priceRepository.save(student_AM);
		
		Price student_PM=new Price();
		student_PM.setFromHour(17);
		student_PM.setToHour(5);
		student_PM.setTicketCategory(ticketCategoryId2);
		student_PM.setUnitPrice((long) 70000);
		priceRepository.save(student_PM);
		
		//Create ticket category 3
		Price adult_AM=new Price();
		adult_AM.setFromHour(5);
		adult_AM.setToHour(17);
		TicketCategory ticketCategoryId3=ticketCategoryService.getTicketCategoryById((long) 3);
		adult_AM.setTicketCategory(ticketCategoryId3);
		adult_AM.setUnitPrice((long) 100000);
		priceRepository.save(adult_AM);
		
		Price adult_PM=new Price();
		adult_PM.setFromHour(17);
		adult_PM.setToHour(5);
		adult_PM.setTicketCategory(ticketCategoryId3);
		adult_PM.setUnitPrice((long) 120000);
		priceRepository.save(adult_PM);
	}
}
