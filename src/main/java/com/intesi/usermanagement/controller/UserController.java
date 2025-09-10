package com.intesi.usermanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.intesi.usermanagement.exception.NotFoundException;
import com.intesi.usermanagement.service.UserDetailService;
import com.intesi.usermanagement.service.dto.UserDetailDTO;
import com.intesi.usermanagement.service.dto.UserDetailUpdateDTO;

import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
@Autowired
private UserDetailService service;


public UserController(UserDetailService service) { 
	this.service = service; 
}


@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Operation(summary = "Crea un nuovo utente")
public ResponseEntity<Boolean> create(@Valid @RequestBody UserDetailDTO request) {
	log.debug("REST request to create a Customer : {}", request);
	return service.saveUser(request);
}


@GetMapping("/{id}")
@Operation(summary = "Recupera utente per id")
public ResponseEntity<Optional<UserDetailDTO>> get(@PathVariable Long id) { 
	if (id < 1 || id > Long.MAX_VALUE)
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
	Optional<UserDetailDTO> usr= service.findById(id);
	if (usr.isPresent())
		return ResponseEntity.ok().body(usr);
	else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
}


@GetMapping
@Operation(summary = "Lista utenti")
public ResponseEntity<List<UserDetailDTO>> list() { 
	List<UserDetailDTO> results = service.list();
	return ResponseEntity.ok().body(results);
}


@PutMapping(value ="update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Operation(summary = "Aggiorna un utente (email non modificabile)")
public ResponseEntity<UserDetailDTO> update(@PathVariable Long id, @Valid @RequestBody  UserDetailUpdateDTO request) {
	if (id < 1 || id > Long.MAX_VALUE)
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	Optional<UserDetailDTO> usr = service.findById(id);
	if (usr.isPresent()) {
		request.setEmail(usr.get().getEmail());
		request.setCreatedDate(usr.get().getCreatedDate());
		UserDetailDTO updateUser = service.updateUser(id, request);
		if ((updateUser != null))
			return ResponseEntity.ok().body(updateUser);
		else
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	} else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
}


@DeleteMapping(value = "/delete/{id}")
@Operation(summary = "Cancella utente")
public ResponseEntity<?> delete(@PathVariable Long id)  {	
	if (id < 1 || id > Long.MAX_VALUE)
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	Optional<UserDetailDTO> usr = service.findById(id);
	if (usr.isPresent()) {
		try {
			service.deleteUser(id);
			return ResponseEntity.ok().body(true);
		} catch (RuntimeException e) {
			log.error("User not found", e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

}