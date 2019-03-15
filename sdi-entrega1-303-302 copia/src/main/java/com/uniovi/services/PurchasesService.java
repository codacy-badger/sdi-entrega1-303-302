package com.uniovi.services;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.PurchasesRepository;

@Service
public class PurchasesService {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private PurchasesRepository purchasesRepository;

	
	public Page<Purchase> getPurchasesForUser(Pageable pageable, User user) {
		Page<Purchase> purchases = new PageImpl<Purchase>(new LinkedList<Purchase>());
		purchases = purchasesRepository.findAllByUser(pageable, user);
		return purchases;
	}
	
	public void addPurchase(Purchase purch) {
		purchasesRepository.save(purch);
	}
}
