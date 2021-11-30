package com.greenacademy.websidepj.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.entity.Genre;
import com.greenacademy.websidepj.repository.DiscountRepository;
import com.greenacademy.websidepj.repository.GenreRepository;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private DiscountRepository discountRepository;

	public boolean isUserLogged() {
		try {
			return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
		} catch (Exception e) {
			return false;
		}
	}

//	@Override
//	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object object) throws Exception {
//		if (genreRepository != null) {
//			List<Genre> genres = genreRepository.findAll();
//			request.getSession().setAttribute("genres", genres);
//		}
//		return true;
//	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		List<Genre> genres = genreRepository.findAll();
		List<Discount> discounts = discountRepository.findAll();
		Discount discount = new Discount();
		if (discounts.size() != 0) {
			for (Discount disco : discounts) {
				discount = disco;
			}
			java.util.Date date = new java.util.Date(System.currentTimeMillis());
			java.sql.Date datesql = new java.sql.Date(date.getTime());
			if (!discount.getStartDate().after(datesql) && !discount.getEndDate().before(datesql)) {
				request.getSession().setAttribute("discount", discount);
			}
		}
		request.getSession().setAttribute("genres", genres);

	}
}
