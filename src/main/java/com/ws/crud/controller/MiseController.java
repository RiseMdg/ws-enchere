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
import com.ws.crud.model.Mise;
import com.ws.crud.payload.request.MiseByUserId;
import com.ws.crud.payload.request.Rencherir;
import com.ws.crud.repository.MiseRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/enchere/")
public class MiseController {

	@Autowired
	private MiseRepository miseRepository;

	public Date toDate(Date assurance) {
		return assurance;
	}


	// save mise

	@PostMapping("/rencherir")
	public Mise rencherir(@RequestBody Rencherir rencherir) {
		Mise mise = new Mise(rencherir.getPrixmise(), rencherir.getUser_id(), rencherir.getEnchere_id());
		return miseRepository.save(mise);
	}

	// get mises by user_id

	@GetMapping("misesbyuserid")
	public List<Mise> getAllMiseByUserId(HttpServletRequest request, @RequestBody MiseByUserId miseByUserId) {
		return this.miseRepository.miseByUser_id(miseByUserId.getUser_id());
	}

	// get mises

	@GetMapping("mises")
	public List<Mise> getAllMise(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("user_id"));
		return this.miseRepository.findAll();
	}

	// get mise by id

	@GetMapping("/mises/{id}")
	public ResponseEntity<Mise> getMiseById(@PathVariable(value = "id") Long miseId)
			throws ResourceNotFoundException {
		Mise mise = miseRepository.findById(miseId)
				.orElseThrow(() -> new ResourceNotFoundException("Mise not found for this id :: " + miseId));
		return ResponseEntity.ok().body(mise);
	}

	// save mise

	@PostMapping("/mises")
	public Mise createMise(@RequestBody Mise mise) {
		return miseRepository.save(mise);
	}

	// update mise

	@PutMapping("/mises/{id}")
	public ResponseEntity<Mise> updateMise(@PathVariable(value = "id") Long miseId, @RequestBody Mise miseDetails)
			throws ResourceNotFoundException {
		Mise mise = miseRepository.findById(miseId)
				.orElseThrow(() -> new ResourceNotFoundException("Mise not found for this id :: " + miseId));

		mise.setId(miseDetails.getId());
		mise.setPrixmise(miseDetails.getPrixmise());
		mise.setUser_id(miseDetails.getUser_id());
		final Mise updatedMise = miseRepository.save(mise);
		return ResponseEntity.ok(updatedMise);
	}

	// delete mise

	@DeleteMapping("/mises/{id}")
	public Map<String, Boolean> deleteMise(@PathVariable(value = "id") Long miseId) throws ResourceNotFoundException {
		Mise mise = miseRepository.findById(miseId)
				.orElseThrow(() -> new ResourceNotFoundException("Mise not found for this id :: " + miseId));

		miseRepository.delete(mise);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
