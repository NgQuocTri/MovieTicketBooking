package com.greenacademy.websidepj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.entity.Movie;
import com.greenacademy.websidepj.repository.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> getAllDiscount(){
		return discountRepository.findAll();
	}
	
	public Discount getMovieByName(double percen) {
		return discountRepository.findByPercen(percen);
	}
	
	public Discount getDiscountDetail(Long discountId) {
		Optional<Discount> discount = discountRepository.findById(discountId);
		Discount d = discount.get();
		return d;
	}
	
	
	public boolean deleteDiscount(Long id) {
		boolean result = false;
		if (discountRepository.findById(id) != null) {
			discountRepository.deleteById(id);
			result = true;
		}
		return result;
	}
	
	public String saveDiscountInfo(Discount discountModel) {
		
		if (discountModel.getPercentage() == 0) {
			return "Vui lòng điền tỷ lệ giảm giá";
		}
		if (discountModel.getStartDate() == null) {
			return "Vui lòng điền ngày bắt đầu.";
		}
		if (discountModel.getEndDate() == null) {
			return "Vui lòng điền ngày kết thúc";
		}
		
		discountRepository.save(discountModel);
		return null;
	}
	
	public String updateDiscountInfo(Discount discountModel) {
		Optional<Discount> discount =discountRepository.findById(discountModel.getDiscountId());
		Discount discountEntity=discount.get();
		
		if (discountModel.getPercentage() == 0) {
			return "Vui lòng điền tỷ lệ giảm giá";
		}
		if (discountModel.getStartDate() == null) {
			return "Vui lòng điền ngày bắt đầu.";
		}
		if (discountModel.getEndDate() == null) {
			return "Vui lòng điền ngày kết thúc";
		}
		
		discountEntity.setPercentage(discountModel.getPercentage());
		discountEntity.setStartDate(discountModel.getStartDate()); 
		discountEntity.setEndDate(discountModel.getEndDate());
		discountRepository.save(discountEntity);
		return null;
	}
	
}
