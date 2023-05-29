package com.testMaterial.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.Prueba;

@Repository
public interface PruebaDao extends JpaRepository<Prueba, Long> {
	List<Prueba> findByMaterial(Material material);
}
