package com.ws.crud.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.model.Field;
import com.ws.crud.Image.UploadImage;
import com.ws.crud.exception.ResourceNotFoundException;
import com.ws.crud.model.Enchere;
import com.ws.crud.payload.request.AjouterEnchere;
import com.ws.crud.payload.request.EnchereByUserId;
import com.ws.crud.repository.EnchereRepository;
import com.ws.crud.repository.ImageRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000",
		"https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/enchere/")
public class EnchereController {

	@Autowired
	private EnchereRepository enchereRepository;

	@Autowired
	private ImageRepository imageRepository;

	private UploadImage uploadImage; 

	public Date toDate(Date assurance) {
		return assurance;
	}

	// save enchere

	@PostMapping("/test")
	public Enchere ajouterEnchere(@RequestParam("file") MultipartFile file, @RequestParam AjouterEnchere ajouterEnchere)throws Exception {
		// long int_user_id = (long) request.getSession().getAttribute("user_id");
		// int user_id = 1;
		// System.out.println("User Id : " + user_id);
		String filename = uploadImage.uploadImage(file);
		Enchere enchere = new Enchere(ajouterEnchere.getDatedebut(), ajouterEnchere.getDateFin(), ajouterEnchere.getProduit(), ajouterEnchere.getPrixminimal(), ajouterEnchere.getDescription(), ajouterEnchere.getCategory_id(), ajouterEnchere.getUser_id(),0);
		return enchereRepository.save(enchere);
	}

	// get encheres

	@GetMapping("encheres")
	public List<Enchere> getAllEnchere(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("user_id"));
		List<Enchere> encheres = enchereRepository.findAll();
		List<Enchere> resultat = new ArrayList<>();
		for (Enchere result: encheres){
			long enchere_id = result.getId();
			result.setImages(imageRepository.images(enchere_id));
			resultat.add(result);
		}
		return resultat;
	}
	// get enchere by user_id

	@GetMapping("encheresbyuserid")
	public List<Enchere> getAllEnchereByUserId(HttpServletRequest request,  @RequestBody EnchereByUserId enchereByUserId) {
		List<Enchere> encheres = enchereRepository.enchereByUser_id(enchereByUserId.getUser_id());
		List<Enchere> resultat = new ArrayList<>();
		for (Enchere result: encheres){
			long enchere_id = result.getId();
			result.setImages(imageRepository.images(enchere_id));
			resultat.add(result);
		}
		return resultat;
	}

	// get enchere by id

	@GetMapping("/encheres/{id}")
	public ResponseEntity<Enchere> getEnchereById(@PathVariable(value = "id") long user_id)
			throws ResourceNotFoundException {
		Enchere enchere = enchereRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("Enchere not found for this id :: " + user_id));
		long enchere_id = enchere.getId();
		enchere.setImages(imageRepository.images(enchere_id));
		return ResponseEntity.ok().body(enchere);
	}

	// update enchere

	@PutMapping("/encheres/{id}")
	public ResponseEntity<Enchere> updateEnchere(@PathVariable(value = "id") Long enchereId,
			@RequestBody Enchere enchereDetails)
			throws ResourceNotFoundException {
		Enchere enchere = enchereRepository.findById(enchereId)
				.orElseThrow(() -> new ResourceNotFoundException("Enchere not found for this id :: " + enchereId));

		enchere.setId(enchereDetails.getId());
		enchere.setDatedebut(enchereDetails.getDatedebut());
		enchere.setDatefin(enchereDetails.getDatefin());
		enchere.setProduit(enchereDetails.getProduit());
		enchere.setPrixminimal(enchereDetails.getPrixminimal());
		enchere.setDescription(enchereDetails.getDescription());
		enchere.setCategory_id(enchereDetails.getCategory_id());
		enchere.setUser_id(enchereDetails.getUser_id());
		final Enchere updatedEnchere = enchereRepository.save(enchere);
		return ResponseEntity.ok(updatedEnchere);
	}

	// delete enchere

	@DeleteMapping("/encheres/{id}")
	public Map<String, Boolean> deleteEnchere(@PathVariable(value = "id") Long enchereId)
			throws ResourceNotFoundException {
		Enchere enchere = enchereRepository.findById(enchereId)
				.orElseThrow(() -> new ResourceNotFoundException("Enchere not found for this id :: " + enchereId));

		enchereRepository.delete(enchere);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
