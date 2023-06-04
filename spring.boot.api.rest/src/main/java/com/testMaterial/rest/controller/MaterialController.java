package com.testMaterial.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testMaterial.rest.model.Material;
import com.testMaterial.rest.model.NombreMaterial;
import com.testMaterial.rest.service.MaterialService;

import jakarta.validation.Valid;


@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("api/rest")
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	private static final int SIZE = 10;

	@GetMapping("/materiales")
	public List<Material> findAll() {
		return materialService.findAll();
	}

	@GetMapping("/materiales/page/{page}")
	public Page<Material> findAll(@PathVariable Integer page) {
		return materialService.findAll(PageRequest.of(page, SIZE));
	}

	@GetMapping("/material/{id}")
	public Material findById (@PathVariable Long id) {
		return materialService.findById(id);
	}

	@PostMapping("/material")
	public ResponseEntity<?> createMaterial (@Valid @RequestBody final Material materialObj, BindingResult result) {

		Material material = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map( error -> "El campo " + error.getField() + " contiene el error " + error.getDefaultMessage() ).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			material = materialService.save(materialObj);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el material");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("success", "El material se ha creado con éxito!");
		response.put("material", material);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/material/{id}")
	public ResponseEntity<?> updateMaterial(@Valid @RequestBody final Material materialObj, @PathVariable Long id, BindingResult result) {

		Material material = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map( error -> "El campo " + error.getField() + " contiene el error " + error.getDefaultMessage() ).collect(Collectors.toList());
			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}


		try {
			material = materialService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener el material en - updateMaterial");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		if (material == null) {
			response.put("mensaje", "El material actual con ID: ".concat(id.toString()).concat(" no existe material, no se puede modificar"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			material.setTipo(materialObj.getTipo());
			material.setFabricante(materialObj.getFabricante());
			material.setFechaAdquisicion(materialObj.getFechaAdquisicion());
			material.setVidaUtil(materialObj.getVidaUtil());
			material = materialService.update(material);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al modificar el material");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("success", "El material se ha modificado con éxito!");
		response.put("material", material);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/material/{id}")
	public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			materialService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el material");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("success", "El material se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/material/tiposMateriales")
	public List<NombreMaterial> listaNombresMateriales() {
		return materialService.findAllNombresMateriales();
	}

}