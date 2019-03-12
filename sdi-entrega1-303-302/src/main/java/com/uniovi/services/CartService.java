package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.PurchasesRepository;
import com.uniovi.repositories.UsersRepository;

@Service
@Transactional
public class CartService {
	@Autowired
	private OffersRepository offersRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private PurchasesService purchasesService;

	private List<Offer> articles = new ArrayList<>();

	public void addOffer(Long id) {
		Offer offer = offersRepository.findById(id).get();
		if (offer != null) {
			articles.add(offer);
		}
	}

	public void removeOffer(Long id) {
		Optional<Offer> findById = offersRepository.findById(id);
		if (articles.contains(findById)) {
			articles.remove(articles.indexOf(findById));
		}
	}

	public List<Offer> getOffers() {
		return new ArrayList<Offer>(articles);
	}

	public void checkout(User user) {
		double price = 0.0;
		for (Offer offer : articles) {
			price += offer.getPrice();
		}
		if (user.getBalance() < price) {
			// throw new runtimeexception
		} else {
			for (Offer offer : articles) {
				offersRepository.deleteById(offer.getId());
				user.setBalance(user.getBalance() - offer.getPrice());
				Purchase purchase = new Purchase();
				purchase.setTitle(offer.getTitle());
				purchase.setDescription(offer.getDescription());
				purchase.setPrice(offer.getPrice());
				purchase.setPicture(offer.getPicture());
				purchase.setUser(user);
				purchasesService.addPurchase(purchase);
				User vendedor = offer.getUser();
				vendedor.setBalance(vendedor.getBalance() + offer.getPrice());
			}

		}
		articles.clear();
	}

	public double getPrice() {
		double total = 0;
		for (Offer offer : articles) {
			total += offer.getPrice();
		}
		return total;
	}
}