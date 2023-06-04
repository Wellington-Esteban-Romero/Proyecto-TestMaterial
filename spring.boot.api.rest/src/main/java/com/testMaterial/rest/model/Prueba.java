package com.testMaterial.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pruebas")
public class Prueba implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "no puede estar vacio")
	@NotNull
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NotNull
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@NotNull
	@Column(name = "tiempo_ejecucion", nullable = false)
	private Long tiempoEjecucion;

	@NotNull
	@Column(name = "coste", nullable = false)
	private BigDecimal coste;

	@NotNull
	@Column(name = "persona_realiza_prueba", nullable = false)
	private String personaRealizaPrueba;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) // se crea un proxy, y generar otros atributos
	@JoinColumn(name = "tipoprueba_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoPrueba tipoPrueba;

	@ManyToOne
	@JoinColumn(name = "material_id")
	@JsonIgnoreProperties(value = "material_id", allowSetters = true)
	private Material material;
	
	@PrePersist
	public void prePersit () { //le asigna la fecha antes de que se persista a base de datos
		fechaCreacion = new Date();
	}

	public Prueba() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(Long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public BigDecimal getCoste() {
		return coste;
	}

	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}

	public String getPersonaRealizaPrueba() {
		return personaRealizaPrueba;
	}

	public void setPersonaRealizaPrueba(String personaRealizaPrueba) {
		this.personaRealizaPrueba = personaRealizaPrueba;
	}

	
	public TipoPrueba getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(TipoPrueba tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
