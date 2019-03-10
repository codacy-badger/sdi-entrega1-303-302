package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.services.CartService;
import com.uniovi.services.OffersService;
@Controller
public class CartController {
	private final CartService cartService;
	private final OffersService offerService;
	
    @Autowired
    public CartController(CartService cartService, OffersService offersService) {
        this.cartService = cartService;
        this.offerService = offersService;
    }
    
    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable Long id) {
    	cartService.removeOffer(id);
    	return "redirect:/cart/list";
    }
    
    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Long id) {
    	cartService.addOffer(id);
    	return "redirect:/cart/list";
    }
    @RequestMapping("/cart/list")
    public String getOffers(Model model) {
    	model.addAttribute("cartList", cartService.getOffers());
    	return "cart/list";
    }
    
    @RequestMapping("/cart/checkout")
    public String checkOut(Model model) {
    	cartService.checkout();
    	return "cart/final";
    }
    
}
