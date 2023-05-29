package com.testMaterial.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testMaterial.rest.model.TipoPrueba;

public interface TipoPruebaDao extends JpaRepository<TipoPrueba, Long>  {
	
}
