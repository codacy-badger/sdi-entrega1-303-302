package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "chat")
public class Chat {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "destine")
	private User destine;
	
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	Set<Message> messages = new HashSet<>();
	

	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public Chat(long id, User user1, Set<Message> messages) {
		super();
		this.id = id;
		this.user = user1;
		this.messages = messages;
	}
	public Chat() {
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getDestine() {
		return destine;
	}
	public void setDestine(User destine) {
		this.destine = destine;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
