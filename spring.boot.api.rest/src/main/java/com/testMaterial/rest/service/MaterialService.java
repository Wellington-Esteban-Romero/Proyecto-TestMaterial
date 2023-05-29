package com.testMaterial.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.NombreMaterial;

public interface MaterialService {
	List<Material> findAll();
	Page<Material> findAll(Pageable pageable);
	Material findById(Long id);
	Material save(Material material);
	Material update(Material material);
	void delete(Long id);
	List<NombreMaterial> findAllNombresMateriales();
}
