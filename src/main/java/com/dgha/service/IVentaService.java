package com.dgha.service;

import com.dgha.model.Venta;

public interface IVentaService extends ICRUD<Venta> {

	Venta registrarVentaConDetalle(Venta venta);
}
