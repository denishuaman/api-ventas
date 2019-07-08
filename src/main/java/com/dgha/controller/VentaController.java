package com.dgha.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Venta;
import com.dgha.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;

	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta) {
		Venta obj = service.registrarVentaConDetalle(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> leerPorId(@PathVariable("id") Integer id) {
		Venta obj = service.leerPorId(id);
		if (obj == null)
			throw new ModelNotFoundException("ID no encontrado: " + id);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
}
