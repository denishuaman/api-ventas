package com.dgha.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgha.exception.EmptyObjectException;
import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Persona;
import com.dgha.model.Producto;
import com.dgha.model.Venta;
import com.dgha.repo.IDetalleVentaRepo;
import com.dgha.repo.IPersonaRepo;
import com.dgha.repo.IProductoRepo;
import com.dgha.repo.IVentaRepo;
import com.dgha.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	@Autowired
	private IDetalleVentaRepo dvRepo;
	@Autowired
	private IPersonaRepo personaRepo;
	@Autowired
	private IProductoRepo productoRepo;

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

	private void validarDatosDeVenta(Venta venta) {
		if (venta.getPersona() == null) {
			throw new EmptyObjectException("Debe indicar la persona que realizó la venta");
		}
		if (venta.getPersona().getIdPersona() == null) {
			throw new ModelNotFoundException("Debe indicar el código de la persona que realizó la venta");
		}
		Persona persona = personaRepo.findOne(venta.getPersona().getIdPersona());
		if (persona == null) {
			throw new ModelNotFoundException("La persona que realizó la venta no está registrada");
		}
		if (venta.getDetalleVenta() == null || venta.getDetalleVenta().isEmpty()) {
			throw new EmptyObjectException("Debe indicar el detalle de la venta");
		}
		venta.getDetalleVenta().forEach(det -> {
			if (det.getProducto() == null) {
				throw new EmptyObjectException("Debe indicar el producto vendido");
			}
			if (det.getProducto().getIdProducto() == null) {
				throw new ModelNotFoundException("Debe indicar el código del producto vendido");
			}
			Producto producto = productoRepo.findOne(det.getProducto().getIdProducto());
			if (producto == null) {
				throw new ModelNotFoundException("El producto a vender no está registrado");
			}
		});
	}

	@Transactional
	@Override
	public Venta registrarVentaConDetalle(Venta venta) {
		validarDatosDeVenta(venta);
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
