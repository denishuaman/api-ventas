package com.dgha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgha.model.Producto;
import com.dgha.repo.IProductoRepo;
import com.dgha.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepo repo;
	
	@Override
	public Producto registrar(Producto t) {
		return repo.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return repo.save(t);
	}

	@Override
	public Producto leerPorId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
