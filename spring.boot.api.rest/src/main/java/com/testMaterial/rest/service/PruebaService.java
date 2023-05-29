package com.testMaterial.rest.service;

import java.util.List;

import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.Prueba;
import com.testMaterial.rest.model.TipoPrueba;

public interface PruebaService {
	
	List<Prueba> findAll();
	List<Prueba> findAllByMaterial(Material material);
	Prueba save(Prueba prueba);
	List<TipoPrueba>  findAllByTipoPrueba();
}
