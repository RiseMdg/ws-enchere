package com.ws.crud;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ws.crud.security.JWTAuthorizationFilter;
import com.ws.crud.service.FilesStorageService;

@SpringBootApplication
@EnableScheduling
public class WsAvionApplication implements CommandLineRunner {

	@Resource
  	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(WsAvionApplication.class, args);
	}

	@Override
  	public void run(String... arg) throws Exception {
    storageService.deleteAll();
    storageService.init();
  }

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.PUT, "/**").permitAll()
					.antMatchers(HttpMethod.POST, "/**").permitAll()
					.antMatchers(HttpMethod.GET, "/**").permitAll().anyRequest()
					.authenticated();
		}
	}

}
