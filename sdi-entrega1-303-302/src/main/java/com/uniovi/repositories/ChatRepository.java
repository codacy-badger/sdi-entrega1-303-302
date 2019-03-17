package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.User;

public interface ChatRepository extends CrudRepository<Chat, Long> {

	@Query("SELECT r FROM Chat r WHERE (r.user = ?1 and r.destine = ?2 ) or (r.user = ?2 and r.destine = ?1 )")
	List<Chat> findByExistingByUsers(User user, User destine);
	@Query("SELECT r FROM Chat r WHERE r.user = ?1 or r.destine = ?1")
	List<Chat> getList(User user);
	@Query("SELECT r.messages FROM Chat r WHERE r.user = ?1 and r.destine = ?2")
	List<Message> getMessages(User user, User user2);

}
