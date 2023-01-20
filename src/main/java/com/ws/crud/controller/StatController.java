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
import com.ws.crud.payload.response.InterfaceStatistique;
import com.ws.crud.payload.response.Statistique;
import com.ws.crud.repository.EnchereRepository;
import com.ws.crud.repository.MiseRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/admin/stat/")
public class StatController {

	@Autowired
	private MiseRepository miseRepository;
	@Autowired
	private EnchereRepository enchereRepository;


	public Date toDate(Date assurance) {
		return assurance;
	}


	@GetMapping("produitBeEnchere")
	public List<InterfaceStatistique> produitBeEnchere(HttpServletRequest request) {
		return this.enchereRepository.produitBeEnchere();
	}

	@GetMapping("categoryBeEnchere")
	public List<InterfaceStatistique> categoryBeEnchere(HttpServletRequest request) {
		return this.enchereRepository.categoryBeEnchere();
	}

	@GetMapping("produitBeMise")
	public List<InterfaceStatistique> produitBeMise(HttpServletRequest request) {
		return this.enchereRepository.produitBeMise();
	}

	@GetMapping("categoryBeMise")
	public List<InterfaceStatistique> categoryBeMise(HttpServletRequest request) {
		return this.enchereRepository.categoryBeMise();
	}
}
