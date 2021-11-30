package com.greenacademy.websidepj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.repository.DiscountRepository;
import com.greenacademy.websidepj.service.DiscountService;

@Controller
@RequestMapping(value = "/admin")
public class DiscountController {

	@Autowired
	DiscountService discountService;

	@Autowired
	DiscountRepository discountRepository;

	@RequestMapping(value = "/newDiscount", method = RequestMethod.GET)
	public ModelAndView getNewDiscountView() {

		Discount discount = new Discount();
		ModelAndView model = new ModelAndView();
		model.setViewName("newDiscount");
		model.addObject("discount", discount);

		return model;
	}

	@RequestMapping(value = "/discountList", method = RequestMethod.GET)
	public ModelAndView getDiscountListView() {
		List<Discount> discounts = discountService.getAllDiscount();
		ModelAndView model = new ModelAndView();
		model.setViewName("discountList");
		model.addObject("discounts", discounts);
		return model;
	}

	@RequestMapping(value = "/editDiscount", method = RequestMethod.GET)
	public ModelAndView getEditDiscountView(@RequestParam("discountId") Long discountId) {
		Discount discount = discountService.getDiscountDetail(discountId);

		ModelAndView model = new ModelAndView();
		model.setViewName("editDiscount");
		model.addObject("discount", discount);

		return model;
	}

	@RequestMapping(value = "/deleteDiscount", method = RequestMethod.GET)
	public ModelAndView deleteDiscount(@RequestParam("discountId") Long discountId) {
		discountService.deleteDiscount(discountId);
		List<Discount> discounts = discountService.getAllDiscount();
		ModelAndView model = new ModelAndView();
		model.setViewName("discountList");
		model.addObject("discounts", discounts);
		return model;
	}

	@RequestMapping(value = "/saveDiscount", method = RequestMethod.POST)
	public String saveNewDiscount(@ModelAttribute("discount") Discount discountModel, Model model) {
		String errorMessage;
		if (discountModel.getDiscountId() == null) {
			errorMessage = discountService.saveDiscountInfo(discountModel);
			if (errorMessage != null) {
				model.addAttribute("errorMessage", errorMessage);
				return "newDiscount";
			}
		} else {
			errorMessage = discountService.updateDiscountInfo(discountModel);
			if (errorMessage != null) {
				model.addAttribute("errorMessage", errorMessage);
				return "editDiscount";
			}
		}

		Discount discount = discountRepository.findByPercen(discountModel.getPercentage());

		model.addAttribute("discount", discount);

		return "redirect:/admin/discountList";
	}
}
