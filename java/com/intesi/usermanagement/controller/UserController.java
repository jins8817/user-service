package com.intesi.usermanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

@Autowired
private UserDetailService service;

public UserController(UserDetailService service) { 
	this.service = service; 
}


@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Operation(summary = "Crea un nuovo utente")
public ResponseEntity<Boolean> create(@Valid @RequestBody UserDetailDTO request) {
	return service.saveUser(request);
}


@GetMapping("/{id}")
@Operation(summary = "Recupera utente per id")
public Optional<UserDetailDTO> get(@PathVariable Long id) { 
	return service.findById(id); }


@GetMapping
@Operation(summary = "Lista utenti")
public List<UserDetailDTO> list() { 
	return service.list();
}


@PutMapping(value ="update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Operation(summary = "Aggiorna un utente (email non modificabile)")
public ResponseEntity<UserDetailDTO> update(@PathVariable Long id, @Valid @RequestBody  UserDetailUpdateDTO request) {
	return service.updateUser(id, request);
}


@DeleteMapping(value = "/delete/{id}")
@Operation(summary = "Cancella utente")
public ResponseEntity<Boolean> delete(@PathVariable Long id) throws Exception {	
	return service.deleteUser(id);
}
}