package com.testMaterial.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testMaterial.rest.dao.UsuarioDao;
import com.testMaterial.rest.model.Usuario;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user =  usuarioDao.findByUsername(username);

		if (user == null) {
			logger.error("No existe el usuario " + username +" en el sistema");
			throw new UsernameNotFoundException("No existe el usuario " + username +" en el sistema");
		}

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		System.out.println(authorities);
		return new User(user.getUsername(), user.getPassword(), user.getEnable(), true, true, true, authorities);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

}
