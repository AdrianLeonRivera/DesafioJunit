package com.bootcamp.junit.bbdd;

import com.bootcamp.junit.model.Articulo;

public interface BaseDatosServiceI {

	public void initBD();
	
	public Articulo findArticuloById(Integer id);
	
	public String insertarArticulo(Articulo articulo);
	
	public Integer insertarArticuloById(Articulo articulo, Integer id);
}
