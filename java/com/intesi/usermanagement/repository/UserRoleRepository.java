package com.intesi.usermanagement.repository;

import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UserRoleRepository {


	@PersistenceContext
	private EntityManager em;
	
	/**
	* Rimuove i ruoli dell'utente. 
	*/
	@Transactional
	public int removeRolesByIdUser(@Parameter Long userId) {
		return em.createNativeQuery("delete from user_role where user_id=:uid")
		.setParameter("uid", userId)
		.executeUpdate();
	}

}
