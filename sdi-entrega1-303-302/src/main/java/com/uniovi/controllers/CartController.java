package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.CartService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
@Controller
public class CartController {
	@Autowired
	private final CartService cartService;
	@Autowired
	private final OffersService offerService;
	
	@Autowired
	private UsersService usersService;
	
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
    public String checkOut(Principal principal, Model model) {
    	String Email = principal.getName(); 
    	User user = usersService.getUserByEmail(Email);
    	cartService.checkout(user);
    	return "cart/final";
    }
    
    
}
