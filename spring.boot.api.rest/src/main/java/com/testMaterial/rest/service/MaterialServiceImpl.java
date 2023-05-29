package com.testMaterial.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testMaterial.rest.dao.MaterialDao;
import com.testMaterial.rest.dao.NombreMaterialDao;
import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.NombreMaterial;


@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	private NombreMaterialDao nombreMatereialDao;

	@Override
	@Transactional(readOnly = true)
	public List<Material> findAll() {
		return materialDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Material> findAll(Pageable pageable) {
		return materialDao.findAll(pageable);
	}

	@Override
	@Transactional
	public Material findById(Long id) {
		return materialDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Material save(Material material) {
		return materialDao.save(material);
	}

	@Override
	@Transactional
	public Material update(Material material) {
		return materialDao.save(material);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		materialDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<NombreMaterial> findAllNombresMateriales() {
		return nombreMatereialDao.findAll();
	}

}
