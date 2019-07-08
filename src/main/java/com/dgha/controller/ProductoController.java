package com.dgha.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Producto;
import com.dgha.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> leerPorId(@PathVariable("id") Integer id) {
		Producto obj = service.leerPorId(id);
		if(obj==null) throw new ModelNotFoundException("ID no encontrado: "+id);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto request) {
		Producto obj = service.registrar(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Producto request) {
		service.modificar(request);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Producto obj = service.leerPorId(id);
		if (obj == null)
			throw new ModelNotFoundException("ID no encontrado: " + id);
		else
			service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
