package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Chat chat;
	@Transient
	private User from;
	@Transient
	private User to;
	private String message;
	
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public Message(long id, Chat chat, User from, User to, String message) {
		super();
		this.id = id;
		this.chat = chat;
		this.from = from;
		this.to = to;
		this.message = message;
	}
	public Message() {
	}
	
}
