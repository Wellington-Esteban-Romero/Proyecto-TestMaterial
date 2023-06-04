package com.testMaterial.rest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;

	@Autowired
	private JwtAuthentication jwtAuthentication;

	@Autowired
	private UserDetailsService usuarioDetailsService;

	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

		http.csrf().disable()
		.cors()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthEntryPoint)
		.and()
		.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/rest/login").permitAll())
		.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated().and()
				).httpBasic(Customizer.withDefaults())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //desabilitar el estado, porque es una rest, y no una aplicacion con estado

		http.addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class);
		return http.build();	
	}

	@Autowired
	public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(usuarioDetailsService)
		.passwordEncoder(bCryptPasswordEncoder());
	}
}
