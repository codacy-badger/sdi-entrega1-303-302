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

import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.services.PurchasesService;
import com.uniovi.services.UsersService;
@Controller
public class PurchasesController {

	@Autowired
	private UsersService usersService;
	@Autowired // Inyectar el servicio
	private PurchasesService purchasesService;
	
	@RequestMapping("/purchase/list")
	public String getMyList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String Email = principal.getName();
		User user = usersService.getUserByEmail(Email);
		Page<Purchase> purchases = new PageImpl<Purchase>(new LinkedList<Purchase>());

		purchases = purchasesService.getPurchasesForUser(pageable, user);
		model.addAttribute("purchaseList", purchases.getContent());
		model.addAttribute("page", purchases);
		return "purchase/list";
	}
}
