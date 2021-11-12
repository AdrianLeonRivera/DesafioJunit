package com.bootcamp.junit.services;

import java.util.List;

import com.bootcamp.junit.model.Articulo;

public interface CarritoCompraServiceI {
	
	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public Integer getNumArticulo();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calcularDescuento(Double precio, Double descuento);
	
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje);
	
	public Integer addArticuloById(Articulo articulo);
	
	
}
