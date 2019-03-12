package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private String picture;
	private Boolean resend = false;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Offer(Long id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Offer(String description, Double score, User user) {
		super();
		this.description = description;
		this.price = score;
		this.user = user;
	}

	public Offer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override

	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + price + "]";

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getResend() {
		return resend;
	}

	public void setResend(Boolean resend) {
		this.resend = resend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
