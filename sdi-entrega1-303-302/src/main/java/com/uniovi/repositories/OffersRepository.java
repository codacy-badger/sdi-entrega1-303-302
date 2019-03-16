package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer, Long> {
	@Query("SELECT r FROM Offer r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1))")
	Page<Offer> searchByDescriptionAndName(Pageable pageable, String seachtext);

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1) OR LOWER(r.title) LIKE LOWER(?1)) AND r.user <> ?2 ")
	Page<Offer> searchByDescriptionNameAndUser(Pageable pageable, String seachtext, User user);
	
	@Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1)) AND r.user <> ?2 ")
	Page<Offer> searchByTitle(Pageable pageable, String seachtext, User user);

	
	@Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Offer> findAllByUser(Pageable pageable, User user);
	
	@Query("SELECT r FROM Offer r WHERE r.user <> ?1 ORDER BY r.id ASC ")
	Page<Offer> findAllByOthers(Pageable pageable, User user);
	
	@Query("SELECT r FROM Offer r WHERE r.user <> ?1 and r.special<>0 ORDER BY r.id ASC ")
	Page<Offer> findSpecialsByOthers(Pageable pageable, User user);


	@Query("SELECT r FROM Offer r")
	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1))")
	List<Offer> searchByDescriptionAndName(String seachtext);

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user = ?2 ")
	List<Offer> searchByDescriptionNameAndUser(String seachtext, User user);

	@Modifying
	@Transactional
	@Query("UPDATE Offer SET resend = ?1 WHERE id = ?2")
	void updateResend(Boolean resend, Long id);

	@Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Offer> findAllByUser(User user);
	
	@Modifying
	@Transactional
	@Query("UPDATE Offer SET special=?1 where id=?2")
	void setSpecial(Boolean bool, Long id);
}