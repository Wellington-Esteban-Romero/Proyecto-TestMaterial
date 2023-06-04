package com.testMaterial.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="materiales")
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descripcion")
	private String descripcion;

	@NotNull
	@Column(name = "fabricante", nullable = false)
	private String fabricante;
	
	@NotNull
	@Column(name = "fecha_adquisicion", nullable = false)
	private Date fechaAdquisicion;

	@NotNull
	@Column(name = "vida_util", nullable = false)
	private Long vidaUtil;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) // se crea un proxy, y generar otros atributos
	@JoinColumn(name = "nombrematerial_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private NombreMaterial tipo;

	@OneToMany(mappedBy = "material", orphanRemoval = true)
	@JsonIgnoreProperties(value = "material", allowSetters = true)
	private List<Prueba> pruebas;

	public Material() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NombreMaterial getTipo() {
		return tipo;
	}

	public void setTipo(NombreMaterial tipo) {
		this.tipo = tipo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Long getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Long vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public List<Prueba> getPruebas() {
		return pruebas;
	}

	public void setPruebas(List<Prueba> pruebas) {
		this.pruebas = pruebas;
	}
}
