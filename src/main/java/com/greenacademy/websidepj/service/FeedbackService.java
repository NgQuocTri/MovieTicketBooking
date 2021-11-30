package com.greenacademy.websidepj.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.websidepj.entity.Feedback;
import com.greenacademy.websidepj.repository.FeedbackRepository;

@Transactional
@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public List<Feedback> getAllFeedbacks(){
		return feedbackRepository.findAll();
	}
	
	public List<Feedback> searchByCustomer(String customerName){
		return feedbackRepository.searchByCustomer(customerName);
	}
	
	public String saveFeedback(Feedback feedback) {
		if (feedback.getCustomerName() == null || feedback.getCustomerName().isEmpty()) return "Vui lòng nhập tên";
		if (feedback.getCustomerEmail() == null || feedback.getCustomerEmail().isEmpty()) return "Vui lòng nhập Email";
		if (feedback.getFeedbackContent() == null || feedback.getFeedbackContent().isEmpty()) return "Vui lòng nhập nội dung";
		if (feedback.getCustomerPhone() == null || feedback.getCustomerPhone().isEmpty()) return "Vui lòng nhập số điện thoại";
		Date date = new Date();
		feedback.setDate(date);
		feedbackRepository.save(feedback);
		return null;
	}
	
	public void deleteFeedback(Long feedbackId) {
		Optional<Feedback> feedbackEntity = feedbackRepository.findById(feedbackId);
		Feedback feedback = feedbackEntity.get();
		feedbackRepository.delete(feedback);
	}
	
	public void createDefaultFeedback() {
		Feedback feedback1 = new Feedback();
		feedback1.setCustomerName("Trần Thị Khánh");
		feedback1.setCustomerEmail("abc@gmail.com");
		feedback1.setCustomerPhone("0344803075");
		feedback1.setDate(new Date());
		feedback1.setFeedbackContent("Tôi nhận thấy rạp nhiều muỗi quá, yêu cầu rạp bật máy lạnh mạnh hơn");
		feedbackRepository.save(feedback1);
		
		Feedback feedback2 = new Feedback();
		feedback2.setCustomerName("Nguyễn Trọng Thiện");
		feedback2.setCustomerEmail("green@gmail.com");
		feedback2.setCustomerPhone("0344103075");
		feedback2.setDate(new Date());
		feedback2.setFeedbackContent("Đề nghị rạp mở thêm 1 quán ăn nhỏ trong rạp");
		feedbackRepository.save(feedback2);
	}
}
