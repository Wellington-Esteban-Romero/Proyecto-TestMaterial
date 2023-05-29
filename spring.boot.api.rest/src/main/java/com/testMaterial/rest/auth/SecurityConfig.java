package com.testMaterial.rest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


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

	//	@Autowired
	//	private AuthenticationManager authenticationManager;

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

		//		AuthenticationManagerBuilder build = http.getSharedObject(AuthenticationManagerBuilder.class);
		//		build.userDetailsService(usuarioDetailsService).passwordEncoder(bCryptPasswordEncoder());
		//		authenticationManager = build.build();
		//		http.authenticationManager(authenticationManager);

		http.csrf().disable()
		.cors()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthEntryPoint)
		.and()
		.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/rest/login").permitAll())
		//.authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET,"/api/rest/**").permitAll())
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

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
//		configuration.setAllowCredentials(true);
//		configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));//permitir caceberas
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	//registrar un filtro, en la prioridad más alta en los filtros de escri, en los servidores de autorizacion, cuando enviamos el token
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);//prioridad alta
//		return bean;
//		//si es bajo el orden mayor es la presedencia
//
//	}

	//	@Bean
	//	public JwtAuthentication jwtAuthenticationFilter () {
	//		return new JwtAuthentication();
	//	}

}
