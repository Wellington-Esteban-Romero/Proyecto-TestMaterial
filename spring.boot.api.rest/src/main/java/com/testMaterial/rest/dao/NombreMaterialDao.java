package com.testMaterial.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testMaterial.rest.model.NombreMaterial;

public interface NombreMaterialDao  extends JpaRepository<NombreMaterial, Long>  {

}
