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

@ApiModel(description="Información del producto")
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;

	@ApiModelProperty(notes = "El nombre del producto debe tener por lo menos 2 caracteres y no más de 100")
	@Size(min = 2, max = 100, message = "El nombre del producto debe tener por lo menos 2 caracteres y no más de 100")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@ApiModelProperty(notes = "La marca del producto debe tener por lo menos 2 caracteres y no más de 100")
	@Size(min = 2, max = 100, message = "La marca del producto debe tener por lo menos 2 caracteres y no más de 100")
	@Column(name = "marca", nullable = false, length = 100)
	private String marca;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
