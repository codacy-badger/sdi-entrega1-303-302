package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	@Autowired
	private OffersService marksService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private OffersService offerService;

	@PostConstruct
	public void init() {
		ArrayList<User> u= new ArrayList<User>();
		User user1 = new User("pruebadeemail@prueba.com", "Pedro", "Díaz");
		user1.setPassword("123456");

		u.add(user1);
		usersService.addUser(user1);
		User user2 = new User("prueba@prueba.com", "Carlos", "Castro");
		user2.setPassword("123456");
		
		u.add(user2);
		usersService.addUser(user2);
		User user3 = new User("123@prueba.com", "Federico", "Gomez");
		user3.setPassword("123456");
		u.add(user3);
		usersService.addUser(user3);
		User user4 = new User("456@prueba.com", "Romina", "Lopez");
		user4.setPassword("123456");
		u.add(user4);
		usersService.addUser(user4);
		User user5 = new User("789@prueba.com", "Indiana", "Gimenez");
		user5.setPassword("123456");
		u.add(user5);
		usersService.addUser(user5);
		User user6 = new User("123456@prueba.com", "Pedrito", "Lillo");
		user6.setPassword("123456");
		u.add(user6);
		usersService.addUser(user6);
		User admin = new User("admin@email.com", "Admin", "delSistema");
		admin.setPassword("admin");
		usersService.addUser(admin);
		Long i =(long) 10;
		for(User us:u) {
			for(int k=0;k<5;k++) {
				Offer o= new Offer();
				o.setId(i);
				o.setUser(us);
				o.setPicture("https://www.google.com/search?q=opel&rlz=1C1GCEU_enES835ES835&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjQ5v2T6IPhAhVROhoKHZ7YBHQQ_AUIDygC&biw=1280&bih=913#imgrc=YeGRiTKDST2K1M:");
				o.setTitle("OPEL "+i);
				o.setPrice((double) 40);
				o.setDescription("OPEL "+i);
				o.setSpecial(false);
				offerService.addOffer(o);
				i=i+1;;
			}
		}

	}
}