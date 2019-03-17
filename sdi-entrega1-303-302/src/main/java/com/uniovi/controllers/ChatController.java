package com.uniovi.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Chat;
import com.uniovi.entities.User;
import com.uniovi.services.ChatService;
import com.uniovi.services.UsersService;

@Controller
public class ChatController {
	@Autowired
	UsersService usersService;
	@Autowired
	ChatService chatService;

	@RequestMapping(value = "/chat/add", method = RequestMethod.POST)
	public String send(Principal principal, String emailusuario, String content) {
		User user2 = usersService.getUserByEmail(emailusuario);
		String Email = principal.getName();
		User user = usersService.getUserByEmail(Email);
		chatService.sendMessage(user, user2, content);
		return "redirect:/chat/conversation/" + user2.getId();
	}

	@RequestMapping(value = "/chat/list")
	public String getChatList(Principal principal, Model model) {
		String Email = principal.getName();
		User user = usersService.getUserByEmail(Email);
		List<Chat> chats = chatService.getChats(user);
		List<Chat> listaLimpia = new ArrayList<Chat>();
		for(Chat chat : chats) {
			if(chat.getDestine().getId() == user.getId()) {
				Chat chatnuevo = new Chat();
				chatnuevo.setUser(user);
				chatnuevo.setDestine(chat.getUser());
				chatnuevo.setMessages(chat.getMessages());
				chatnuevo.setId(chat.getId());
				listaLimpia.add(chatnuevo);

			}
			else {
				listaLimpia.add(chat);
			}
		}
		model.addAttribute("list", listaLimpia);
		return "chat/list";
	}

	@RequestMapping("/chat/conversation/{id}")
	public String getChat(Principal principal, Model model, @PathVariable Long id) {
		String Email = principal.getName();
		User user = usersService.getUserByEmail(Email);
		User user2 = usersService.getUser(id);
		Chat chat = chatService.getChat(user, user2);
		model.addAttribute("list", chat.getMessages());
		model.addAttribute("user", user2);
		return "chat/conversation";
	}
	
	@RequestMapping("/chat/delete/{id}")
	public String Delete(@PathVariable Long id) {
		chatService.delete(id);
		
		return "chat/list";
	}
}
