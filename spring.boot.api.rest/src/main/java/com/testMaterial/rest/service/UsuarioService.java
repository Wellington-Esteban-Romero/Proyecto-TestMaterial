package com.testMaterial.rest.service;

import com.testMaterial.rest.model.Usuario;

public interface UsuarioService {
	
	Usuario findByUsername(String username);

}
