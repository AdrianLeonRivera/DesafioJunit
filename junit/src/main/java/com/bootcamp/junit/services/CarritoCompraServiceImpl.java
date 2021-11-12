package com.bootcamp.junit.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.junit.bbdd.BaseDatosServiceImpl;
import com.bootcamp.junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI{

	@Autowired
	private BaseDatosServiceImpl baseDatos;
	
	List<Articulo>cesta=new ArrayList<>();
	@Override
	public void limpiarCesta() {
		
		cesta.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		cesta.add(articulo);
		
	}

	@Override
	public Integer getNumArticulo() {
		
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return cesta;
	}

	@Override
	public Double totalPrice() {

		Double total = 0D;
		
		for(Articulo articulo:cesta)
		{
			total=total+articulo.getPrecio();
		}
		
		return total;
	}

	@Override
	public Double calcularDescuento(Double precio, Double descuento) {
		return precio=precio*descuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Articulo articulo=baseDatos.findArticuloById(idArticulo);
		Double resultado=null;
		if(articulo !=null)
		{
			resultado=articulo.getPrecio()-calcularDescuento(articulo.getPrecio(), porcentaje);
		}
		else
		{
			System.out.println("No se ha encontrado ningun articulo con el Id insertado");
		}
		return resultado;
	}

	@Override
	public Integer addArticuloById(Articulo articulo) {
		int id=cesta.size();
		return baseDatos.insertarArticuloById(articulo, id);
	}

}
