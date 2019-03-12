package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class HomeController {
	@Autowired // Inyectar el servicio
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String Email = principal.getName();
		User user = usersService.getUserByEmail(Email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		offers = offersService.getOffersByOthers(pageable, user);

		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "/home";

	}
}
