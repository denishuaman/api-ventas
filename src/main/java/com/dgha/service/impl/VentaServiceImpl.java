package com.dgha.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgha.model.Venta;
import com.dgha.repo.IDetalleVentaRepo;
import com.dgha.repo.IVentaRepo;
import com.dgha.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	@Autowired
	private IDetalleVentaRepo dvRepo;

	@Override
	public Venta registrar(Venta t) {
		return repo.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		return null;
	}

	@Override
	public Venta leerPorId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		return null;
	}

	@Override
	public void eliminar(Integer id) {
	}

	@Transactional
	@Override
	public Venta registrarVentaConDetalle(Venta venta) {
		venta.getDetalleVenta().forEach(detalle -> {
			detalle.setVenta(venta);
		});
		repo.save(venta);
		venta.getDetalleVenta().forEach(det -> {
			dvRepo.save(det);
		});
		return venta;
	}

}
