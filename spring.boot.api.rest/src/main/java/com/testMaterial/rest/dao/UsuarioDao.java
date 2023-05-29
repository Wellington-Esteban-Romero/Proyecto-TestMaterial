package com.testMaterial.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testMaterial.rest.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);
}
