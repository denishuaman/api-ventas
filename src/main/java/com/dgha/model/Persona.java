package com.dgha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información de la persona")
@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;

	@ApiModelProperty(notes = "Nombres debe tener mínimo 2 caracteres y máximo 100 caracteres")
	@Size(min = 2, max = 100, message = "Nombres debe tener mínimo 2 caracteres y máximo 100 caracteres")
	@Column(name = "nombres", nullable = false, length = 100)
	private String nombres;

	@ApiModelProperty(notes = "Apellidos debe tener mínimo 2 caracteres y máximo 100 caracteres")
	@Size(min = 2, max = 100, message = "Apellidos debe tener mínimo 2 caracteres y máximo 100 caracteres")
	@Column(name = "apellidos", nullable = false, length = 100)
	private String apellidos;

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}
