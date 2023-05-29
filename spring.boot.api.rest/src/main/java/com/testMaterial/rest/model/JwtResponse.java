package com.testMaterial.rest.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {

	private String token;

	private Collection<? extends GrantedAuthority> authorities;

	private String username;

	public JwtResponse(String token, Collection<? extends GrantedAuthority> authorities, String username) {
		super();
		this.token = token;
		this.authorities = authorities;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
