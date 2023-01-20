package com.ws.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.crud.model.Users;
import com.ws.crud.payload.request.Inscription;
import com.ws.crud.repository.UsersRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/enchere/")
public class InscriptionController {

	@Autowired
	private UsersRepository usersRepository;

	// save users

	@PostMapping("/inscription")
	public Users createUsers(@RequestBody Inscription inscription) {
        Users user = new Users(inscription.getUsername(), inscription.getEmail(), inscription.getPassword(),0);
		return usersRepository.save(user);
	}
}
