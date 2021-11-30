package com.greenacademy.websidepj.utils;

import javax.servlet.http.HttpServletRequest;

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.model.CartInfo;

public class Utils {
	public static CartInfo getCartInSession(HttpServletRequest request) {
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
		if (cartInfo == null) {
			cartInfo = new CartInfo();
			request.getSession().setAttribute("myCart", cartInfo);
		}
		return cartInfo;
	}

	public static void removeCartInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCart");
	}

	public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastestOrderCart");
	}

	public static Discount getDiscountInSession(HttpServletRequest request) {
		Discount discount = (Discount) request.getSession().getAttribute("discount");
		return discount;
	}

	public static Ticket getTicketInfoInSession(HttpServletRequest request) {
		return (Ticket) request.getSession().getAttribute("ticketInfo");
	}

	public static void removeTicketInfoInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("ticketInfo");
	}

	public static void storeTicketInfoInSession(HttpServletRequest request, Ticket ticket) {
		request.getSession().setAttribute("ticketInfo", ticket);
	}

	public static void savedLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
		request.getSession().setAttribute("lastestOrderCart", cartInfo);
	}

}
