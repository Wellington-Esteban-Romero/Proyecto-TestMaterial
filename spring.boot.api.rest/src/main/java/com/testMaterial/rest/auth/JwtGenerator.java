package com.testMaterial.rest.auth;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtGenerator {

	@Value("${jwt.secret}")
	private String secret; 

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken (UserDetails authentication) {

		Map<String, Object> claims = new HashMap<>();

		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(authentication.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}


	public String getUserNameFromJWT (String token) {
		Claims clamis =  Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();

		return clamis.getSubject();
	}

	public boolean validateToken (String token) {

		try {
			Jwts.parser().setSigningKey(secret)
			.parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			throw new AuthenticationCredentialsNotFoundException("JWT incorrect");
		}catch (MalformedJwtException e) {
			throw new AuthenticationCredentialsNotFoundException("JWT MalformedJwtException");
		} catch (SignatureException e) {
			throw new AuthenticationCredentialsNotFoundException("JWT SignatureException");
		} catch (ExpiredJwtException e) {
			throw new AuthenticationCredentialsNotFoundException("JWT ExpiredJwtException");
		} catch (IllegalArgumentException e) {
			throw new AuthenticationCredentialsNotFoundException("JWT IllegalArgumentException");
		}
	}


}
