package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.repositories.ChatRepository;

@Service
public class ChatService {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	public void sendMessage(User user, User user2, String message) {
		if(chatRepository.findByExistingByUsers(user, user2) != null && chatRepository.findByExistingByUsers(user,user2).size() ==0 ) {
			Chat chat = new Chat();
			chat.setUser(user);
			chat.setDestine(user2);
			Message ms = new Message();
			ms.setChat(chat);
			ms.setFrom(user);
			ms.setTo(user2);
			ms.setMessage(message);
			chat.getMessages().add(ms);
			chatRepository.save(chat);
		}
		else {
			Chat chat = chatRepository.findByExistingByUsers(user, user2).get(0);
			Message ms = new Message();
			ms.setChat(chat);
			ms.setFrom(user);
			ms.setTo(user2);
			ms.setMessage(message);
			chat.getMessages().add(ms);
			chatRepository.save(chat);
		}
	}

	public List<Chat> getChats(User user) {
		return chatRepository.getList(user);
	}

	public Chat getChat(User user, User user2) {
		return chatRepository.findByExistingByUsers(user, user2).get(0);
	}
	
	public void add(Chat chat) {
		chatRepository.save(chat);
	}
	public void delete(Long id) {
		chatRepository.deleteById(id);
	}
}
