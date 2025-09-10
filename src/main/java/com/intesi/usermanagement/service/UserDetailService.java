package com.intesi.usermanagement.service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intesi.usermanagement.domain.UserDetail;
import com.intesi.usermanagement.exception.NotFoundException;
import com.intesi.usermanagement.mapper.UserDetailMapper;
import com.intesi.usermanagement.repository.UserDetailsRepository;
import com.intesi.usermanagement.repository.UserRoleRepository;
import com.intesi.usermanagement.service.dto.UserDetailDTO;
import com.intesi.usermanagement.service.dto.UserDetailUpdateDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Service
@Transactional
public class UserDetailService {

	private final Logger log = LoggerFactory.getLogger(UserDetailService.class);
	
	@Autowired
	private UserDetailsRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserDetailMapper userDetailMapper;


	/**
	 * find by id
	 *
	 * @return user with the given id
	 */
	@Transactional
	public Optional<UserDetailDTO> findById(Long id) {
		Optional<UserDetail> result = userRepository.findById(id);
		return userDetailMapper.toDto(result);
	}
	/**
	 * find all
	 *
	 * @return users
	 */
	@Transactional
	public List<UserDetailDTO> list() {
		List<UserDetail> result = userRepository.findAll();
		return userDetailMapper.toDto(result);
		
	}

   

	/**
	 * insert a new user
	 * 
	 * @param user the user to save
	 * @return the user that has been saved
	 */
	@Transactional
	public ResponseEntity<Boolean> saveUser(@Valid UserDetailDTO req) {
		UserDetail usr=new UserDetail();
		usr.setUsername(req.getUsername());
		usr.setEmail(req.getEmail());
		usr.setCodiceFiscale(req.getCodiceFiscale().toUpperCase());
		usr.setNome(req.getNome());
		usr.setCognome(req.getCognome());
		usr.setCreatedDate(Instant.now());
		usr.setLastModifiedDate(Instant.now());
		usr.setRoles(req.getRoles());
		try {
			userRepository.save(usr);
			return ResponseEntity.ok().body(Boolean.TRUE);
		} catch (RuntimeException ex) {
			log.error("Error while creating new user {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE);
		}
	}

	/**
	 * performs a delete of a user_id
	 *
	 * @param user the user to delete
	 */
	@Transactional
	public void deleteUser(Long id) {
		userRoleRepository.removeRolesByIdUser(id);
		userRepository.deleteById(id);
	}

	
	/**
	 * update a user
	 * 
	 * @param id
	 * @param req
	 */
	@Transactional
	public UserDetailDTO updateUser(Long id, @Valid UserDetailUpdateDTO req) {
		UserDetail usr=new UserDetail();
		usr.setId(id);
		usr.setUsername(req.getUsername());
		usr.setEmail(req.getEmail());
		usr.setCodiceFiscale(req.getCodiceFiscale().toUpperCase());
		usr.setNome(req.getNome());
		usr.setCognome(req.getCognome());
		usr.setCreatedDate(req.getCreatedDate());
		usr.setLastModifiedDate(Instant.now());
		usr.setRoles(req.getRoles());
		try {
			return userDetailMapper.toDto(userRepository.save(usr)); 
		} catch (RuntimeException ex) {
			log.error("Error while updating user {}", ex.getMessage());
			return null;
		}	
	}





}
