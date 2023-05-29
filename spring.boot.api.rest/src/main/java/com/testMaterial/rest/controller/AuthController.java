package com.testMaterial.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testMaterial.rest.auth.JwtGenerator;
import com.testMaterial.rest.dto.LoginDto;
import com.testMaterial.rest.model.JwtResponse;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("api/rest")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private UserDetailsService userdetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody LoginDto login) {
		logger.info("Autenticando al usuario {}", login.getUsername());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(),
						login.getPassword()));

		final UserDetails userDetails = userdetailsService.loadUserByUsername(login.getUsername());

		final String token = jwtGenerator.generateToken(userDetails);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails user = (UserDetails) authentication.getPrincipal();

		return new ResponseEntity<JwtResponse>(new JwtResponse(token,user.getAuthorities(), user.getUsername()), HttpStatus.OK);
	}
}