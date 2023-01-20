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
import com.ws.crud.model.Category;
import com.ws.crud.repository.CategoryRepository;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/enchere/")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	public Date toDate(Date assurance) {
		return assurance;
	}

	// get categorys

	@GetMapping("categories")
	public List<Category> getAllCategory(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("user_id"));
		return this.categoryRepository.findAll();
	}

	// get category by id

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
		return ResponseEntity.ok().body(category);
	}

	// save category

	@PostMapping("/categories")
	public Category createCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	// update category

	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody Category categoryDetails)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
		category.setId(categoryId);
		category.setNom(categoryDetails.getNom());
		final Category updatedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(updatedCategory);
	}

	// delete category

	@DeleteMapping("/categories/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		categoryRepository.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
