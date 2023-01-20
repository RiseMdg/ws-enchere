package com.ws.crud.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.crud.exception.ResourceNotFoundException;
import com.ws.crud.model.Rechargement;
import com.ws.crud.model.Users;
import com.ws.crud.payload.request.Recharger;
import com.ws.crud.repository.RechargementRepository;
import com.ws.crud.repository.UsersRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/enchere/")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RechargementRepository rechargementRepository;

	public Date toDate(Date assurance) {
		return assurance;
	}

	@PostMapping("/solde")
	public String rechargerCompte(HttpServletRequest request, @RequestBody Recharger recharger) {
		// long int_user_id = (long) request.getSession().getAttribute("user_id");
		// int user_id = (int) int_user_id;
		// System.out.println("User Id : " + user_id);
		Rechargement rechargement = new Rechargement(recharger.getSolde(),0 , recharger.getUser_id());
		System.out.println("Montant : " + recharger.getSolde());
		rechargementRepository.save(rechargement);
		return "Successfull";
	}

	// get userss

	@GetMapping("userss")
	public List<Users> getAllUsers(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("user_id"));
		return this.usersRepository.findAll();
	}

	// get users by id

	@GetMapping("/userss/{id}")
	public ResponseEntity<Users> getUsersById(@PathVariable(value = "id") Long usersId)
			throws ResourceNotFoundException {
		Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));
		return ResponseEntity.ok().body(users);
	}

	// save users

	@PostMapping("/userss")
	public Users createUsers(@RequestBody Users users) {
		return usersRepository.save(users);
	}

	// update users

	@PutMapping("/userss/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Long usersId, @RequestBody Users usersDetails)
			throws ResourceNotFoundException {
		Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));

		users.setId(usersDetails.getId());
		users.setUsername(usersDetails.getUsername());
		users.setEmail(usersDetails.getEmail());
		users.setPassword(usersDetails.getPassword());
		users.setSolde(usersDetails.getSolde());
		final Users updatedUsers = usersRepository.save(users);
		return ResponseEntity.ok(updatedUsers);
	}

	// delete users

	@DeleteMapping("/userss/{id}")
	public Map<String, Boolean> deleteUsers(@PathVariable(value = "id") Long usersId) throws ResourceNotFoundException {
		Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));

		usersRepository.delete(users);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
