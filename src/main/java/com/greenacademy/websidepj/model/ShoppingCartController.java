package com.greenacademy.websidepj.model;

import java.security.Principal;
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

import com.greenacademy.websidepj.entity.Discount;
import com.greenacademy.websidepj.entity.Price;
import com.greenacademy.websidepj.entity.Ticket;
import com.greenacademy.websidepj.entity.User;
import com.greenacademy.websidepj.interceptor.UserInterceptor;
import com.greenacademy.websidepj.service.DiscountService;
import com.greenacademy.websidepj.service.OrderService;
import com.greenacademy.websidepj.service.PriceService;
import com.greenacademy.websidepj.service.TicketService;
import com.greenacademy.websidepj.service.UserService;
import com.greenacademy.websidepj.utils.Utils;

@Controller
@RequestMapping(value = "/shop")
public class ShoppingCartController {

	@Autowired
	PriceService priceService;

	@Autowired
	TicketService ticketService;

	@Autowired
	OrderService orderService;

	@Autowired
	UserInterceptor userInterceptor;

	@Autowired
	UserService userService;

	@Autowired
	DiscountService discountService;

	@RequestMapping({ "/buyTicket" })
	public String buyTicket(HttpServletRequest request, Model model, @RequestParam("priceId") Long priceId) {
		Price price = priceService.getPriceById(priceId);
		Ticket ticket = null;
		ticket = Utils.getTicketInfoInSession(request);
		if (ticket != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);

			BookedTicketInfo bookedTicketInfo = new BookedTicketInfo(ticket, price);
			cartInfo.addBookedTicket(bookedTicketInfo, 1);
		}

		return "redirect:/shop/shoppingCart";
	}

	@RequestMapping({ "/shoppingCartRemoveBookedTicket" })
	public String removeBookedTicket(HttpServletRequest request, Model model, @RequestParam("priceId") Long priceId) {
		Ticket ticket = null;
		Price price = priceService.getPriceById(priceId);

		ticket = Utils.getTicketInfoInSession(request);

		if (ticket != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			BookedTicketInfo bookedTicketInfo = new BookedTicketInfo(ticket, price);
			cartInfo.removeBookedTicket(bookedTicketInfo);
		}
		return "redirect:/shop/shoppingCart";
	}

	// Update quantity shopping cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
	public String shoppingCartUpdateQuantity(HttpServletRequest request, Model model,
			@ModelAttribute("cartForm") CartInfo cartForm) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);
		String errorMessage = "";
		List<CartLineInfo> cartLines = cartInfo.getCartLines();
		for (CartLineInfo cartLine : cartLines) {
			if (cartLine.getErroMessage() != null) {
				errorMessage += cartLine.getErroMessage() + "/<br>";
			}
		}
		if (errorMessage.isEmpty() == false) {
			model.addAttribute("errorMessage", errorMessage);
			CartInfo myCart = Utils.getCartInSession(request);
			model.addAttribute("cartForm", myCart);
			return "shop-shoppingCart";
		}
		return "redirect:/shop/shoppingCart";
	}

	// Show cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String showShoppingCart(HttpServletRequest request, Model model) {
		CartInfo myCart = Utils.getCartInSession(request);
		model.addAttribute("cartForm", myCart);
		return "shop-shoppingCart";
	}

	// Enter customer information
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public ModelAndView shoppingCartCustomerForm(HttpServletRequest request, Principal principal) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		Discount discount = Utils.getDiscountInSession(request);
		if (discount != null) {
			cartInfo.setDiscountToIndex(discount);
		}
		ModelAndView model = new ModelAndView();
		if (userInterceptor.isUserLogged()) {
			User user = userService.getByUserName(principal.getName());
			cartInfo.setUser(user);
			model.setViewName("shop-shoppingCartConfirmationUser");
		} else {
			CustomerInfo customerInfo = cartInfo.getCustomerInfo();
			if (customerInfo == null) {
				customerInfo = new CustomerInfo();
			}
			model.addObject("customerInfo", customerInfo);
			model.setViewName("user-checkout");
		}

		return model;
	}

	// Save customer information
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public String shoppingCartCustomerSave(HttpServletRequest request, Model model,
			@ModelAttribute("customerInfo") CustomerInfo customerInfo) {
		String errorMessage = "";
		if (customerInfo.getCustomerName().isEmpty()) {
			errorMessage += "Vui lòng nhập tên của bạn!" + "<br/>";
		}
		if (customerInfo.getCustomerAddress().isEmpty()) {
			errorMessage += "Vui lòng nhập địa chỉ của bạn!" + "<br/>";
		}
		if (customerInfo.getCustomerEmail().isEmpty()) {
			errorMessage += "Vui lòng nhập Email của bạn!" + "<br/>";
		}
		if (customerInfo.getCustomerPhone().isEmpty()) {
			errorMessage += "Vui lòng nhập số điện thoại của bạn!" + "<br/>";
		}
		if (errorMessage != "") {
			request.setAttribute("errorMessage", errorMessage);
			return "user-checkout";
		}
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.setCustomerInfo(customerInfo);
		return "redirect:/shop/shoppingCartConfirmation";
	}

	// GET: Show information to confirm
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo == null || cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("myCart", cartInfo);
		return "shop-shoppingCartConfirmation";
	}

	// Submit Cart (Save)
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
		try {
			if (userInterceptor.isUserLogged()) {
				orderService.addOrderUser(request, cartInfo);
			} else {
				orderService.addOrder(request, cartInfo);
			}
			Utils.removeTicketInfoInSession(request);

		} catch (Exception e) {
			return "shop-shoppingCartInformation";
		}

		// Remove cart from Session
		Utils.removeCartInSession(request);
		// Save last cart
		Utils.savedLastOrderedCartInSession(request, cartInfo);

		return "redirect:/shop/shoppingCartFinalize";
	}

	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {
		CartInfo lastestOrderedCart = Utils.getLastOrderedCartInSession(request);
		if (lastestOrderedCart == null) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("lastestOrderedCart", lastestOrderedCart);
		return "shop-shoppingCartFinalize";
	}

	@RequestMapping(value = { "/categorizePayment" }, method = RequestMethod.GET)
	public String categorizePayment(HttpServletRequest request, Model model, char payment) {
		switch (payment) {
		case 'i':
			return "shop-internationalPayment";
		case 'a':
			return "shop-atmCard";
		default:
			return "redirect:/shop/paymentCategory";
		}
	}

}
