package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private RolesService rolesService;

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setBalance(100.0);
		if (user.getEmail().equals("admin@email.com")) {
			user.setRole(rolesService.getRoles()[1]);
		} else {
			user.setRole(rolesService.getRoles()[0]);
		}
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public void deleteUser(String email) {
		usersRepository.deleteById(usersRepository.findByEmail(email).getId());
	}

	public User getUserByEmail(String Email) {
		return usersRepository.findByEmail(Email);
	}
}