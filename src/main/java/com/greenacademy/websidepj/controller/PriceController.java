package com.greenacademy.websidepj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.websidepj.entity.Price;
import com.greenacademy.websidepj.entity.TicketCategory;
import com.greenacademy.websidepj.repository.PriceRepository;
import com.greenacademy.websidepj.service.PriceService;
import com.greenacademy.websidepj.service.TicketCategoryService;

@Controller
@RequestMapping(value="/admin")
public class PriceController {
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	TicketCategoryService ticketCategoryService;
	
	@RequestMapping(value="/newPrice", method=RequestMethod.GET)
	public ModelAndView getNewPriceView() {
		Price price=new Price();
		List<TicketCategory> ticketCategories=ticketCategoryService.getAllTicketCategories();
		ModelAndView model=new ModelAndView();
		model.setViewName("newPrice");
		model.addObject("price",price);
		model.addObject("ticketCategories",ticketCategories);
		return model;
	}
	
	@RequestMapping(value="/savePrice", method=RequestMethod.POST)
	public ModelAndView savePrice(@ModelAttribute("price") Price priceModel) {
		if(priceModel.getPriceId()==null) {
			priceService.savePrice(priceModel);
		}
		else {
			priceService.updatePrice(priceModel);
		}
		
		List<Price> prices=priceService.getAllPrices();
		ModelAndView model=new ModelAndView();
		model.setViewName("priceList");
		model.addObject("prices",prices);
		return model;
	}
	
	@RequestMapping(value="/priceList", method=RequestMethod.GET)
	public ModelAndView getPriceListView() {
		List<Price> prices=priceService.getAllPrices();
		ModelAndView model=new ModelAndView();
		model.setViewName("priceList");
		model.addObject("prices",prices);
		return model;
	}
	
	@RequestMapping(value="/editPrice", method=RequestMethod.GET)
	public ModelAndView getEditPriceView(@RequestParam("priceId") Long priceId) {
		Price price=priceService.getPriceById(priceId);
		List<TicketCategory> ticketCategories=ticketCategoryService.getAllTicketCategories();
		ModelAndView model=new ModelAndView();
		model.setViewName("editPrice");
		model.addObject("price",price);
		model.addObject("ticketCategories",ticketCategories);
		return model;
	}
	
	@RequestMapping(value="/deletePrice", method=RequestMethod.GET)
	public ModelAndView deletePrice(@RequestParam("priceId") Long priceId) {
		priceService.deletePrice(priceId);
		List<Price> prices=priceService.getAllPrices();
		ModelAndView model=new ModelAndView();
		model.setViewName("priceList");
		model.addObject("prices",prices);
		return model;
	}
	
	@RequestMapping(value = "/searchPrice", method = RequestMethod.POST)
	public String searchPrice(@RequestParam("search") String searchValue ,HttpServletRequest request, Model model) {
		List<Price> prices=priceService.searchByTicketCat(searchValue);
		model.addAttribute("prices",prices);
		model.addAttribute("search", true);
		model.addAttribute("searchValue", searchValue);
		return "priceList";	
	}
}
