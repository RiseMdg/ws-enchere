package com.ws.crud.controller;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.crud.exception.ResourceNotFoundException;
import com.ws.crud.model.Admins;
import com.ws.crud.model.Rechargement;
import com.ws.crud.mongodb.Mongo;
import com.ws.crud.payload.request.Login;
import com.ws.crud.payload.request.ValiderRechargement;
import com.ws.crud.repository.AdminRepository;
import com.ws.crud.repository.RechargementRepository;
import com.ws.crud.repository.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = { "http://localhost", "http://localhost:3000",
        "https://celebrated-zuccutto-82b407.netlify.app" })
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RechargementRepository rechargementRepository;
    

    @PostMapping("login")
    public String login(@RequestBody Login login, HttpServletRequest request)
            throws ResourceNotFoundException, UnknownHostException {
        Mongo mongo = new Mongo();
        String email = login.getEmail();
        String password = login.getPassword();
        String message = mongo.loginAdmin(email, password);
        String token = getJWTToken(email);
        return message;
    }

    
    @GetMapping("admins")
    public List<Admins> listAdmins(){
        return adminRepository.findAll();
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

    //list Rechargement Pas encore Valide

    @GetMapping("validationRechargement")
    public List<Rechargement> validationRechargement(){
        return rechargementRepository.rechargementsNotAccepted();
    }

    @PostMapping("validerRechargement")
    public String validerRechargement(@RequestBody ValiderRechargement validerRechargement, HttpServletRequest request)
            {
                rechargementRepository.updateEtatRechargement(validerRechargement.getRechargement_id());
                double montant = rechargementRepository.getMontant(validerRechargement.getRechargement_id());
                usersRepository.recharger(montant, validerRechargement.getUser_id());
                return "Successfull Validation";
    }
}