package com.bootcamp.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bootcamp.junit.bbdd.BaseDatosServiceI;
import com.bootcamp.junit.bbdd.BaseDatosServiceImpl;
import com.bootcamp.junit.model.Articulo;

@SpringBootApplication
public class JunitApplication implements CommandLineRunner{

	@Autowired
	BaseDatosServiceImpl baseDatosService;
	
	public static void main(String[] args) {
		SpringApplication.run(JunitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		baseDatosService.initBD();
		Articulo articulo=new Articulo("Calcetines", 5.95);
		baseDatosService.insertarArticulo(articulo);
		baseDatosService.findArticuloById(9);
	}

}
