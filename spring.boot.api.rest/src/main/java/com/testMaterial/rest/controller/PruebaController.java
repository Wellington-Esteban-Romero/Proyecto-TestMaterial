package com.testMaterial.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testMaterial.rest.model.NombreMaterial;
import com.testMaterial.rest.model.Prueba;
import com.testMaterial.rest.model.TipoPrueba;
import com.testMaterial.rest.service.PruebaService;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("api/rest")
public class PruebaController {
	
	@Autowired
	private PruebaService pruebaService;
	
	@GetMapping("/pruebas/{id}")
	public List<Prueba> findAll(@PathVariable Long id) {
		return pruebaService.findAll().stream()
				.filter(m -> m.getMaterial().getId().longValue() == id)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/prueba")
	public ResponseEntity<?> createPrueba (@Validated @RequestBody Prueba pruebaObj, BindingResult result) {

		Prueba prueba = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map( error -> "El campo " + error.getField() + " contiene el error " + error.getDefaultMessage() ).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			prueba = pruebaService.save(pruebaObj);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el prueba");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("success", "la prueba se ha creado con éxito!");
		response.put("prueba", prueba);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/material/tiposPruebas")
	public List<TipoPrueba> getTiposPruebas() {
		return pruebaService.findAllByTipoPrueba();
	}


}
