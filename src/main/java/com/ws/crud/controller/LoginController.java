package com.ws.crud.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.crud.exception.ResourceNotFoundException;
import com.ws.crud.model.Users;
import com.ws.crud.payload.request.Login;
import com.ws.crud.payload.response.User;
import com.ws.crud.repository.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000" })
@RestController
@RequestMapping("/enchere/")
public class LoginController {

	@Autowired
	private UsersRepository usersRepository;

	@PostMapping("login")
	public User login(@RequestBody Login login, HttpServletRequest request)
			throws ResourceNotFoundException {
		String email = login.getEmail();
		String password = login.getPassword();
		usersRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User" + email + " Not Found"));
		Users session = usersRepository.findByPassword(password)
				.orElseThrow(() -> new ResourceNotFoundException("Wrong Password"));
		String token = getJWTToken(email);
		User user = new User(session.getId(), session.getUsername(), session.getEmail(), token);
		request.getSession().setAttribute("user_id", session.getId());
		return user;

	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes())
				.compact();

		return "Bearer " + token;
	}
}