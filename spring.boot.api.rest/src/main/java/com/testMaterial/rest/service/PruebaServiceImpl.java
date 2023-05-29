package com.testMaterial.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testMaterial.rest.dao.PruebaDao;
import com.testMaterial.rest.dao.TipoPruebaDao;
import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.Prueba;
import com.testMaterial.rest.model.TipoPrueba;

@Service
public class PruebaServiceImpl implements PruebaService {
	
	@Autowired
	private PruebaDao pruebaDao;
	
	@Autowired
	private TipoPruebaDao tipoPruebaDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> findAll() {
		return pruebaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Prueba> findAllByMaterial(Material material) {
		return pruebaDao.findByMaterial(material);
	}

	@Override
	public Prueba save(Prueba prueba) {
		return pruebaDao.save(prueba);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoPrueba> findAllByTipoPrueba() {
		return tipoPruebaDao.findAll();
	}

}
