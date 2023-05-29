package com.testMaterial.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testMaterial.rest.model.Material;

@Repository
public interface MaterialDao extends JpaRepository<Material, Long> {
	
}
