package com.bootcamp.junit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.junit.bbdd.BaseDatosServiceImpl;
import com.bootcamp.junit.model.Articulo;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {

	
	Articulo articulo=new Articulo("Gorrra", 12.95);
	Articulo articulo2=new Articulo("Pantalones", 20.95);
	
	@InjectMocks
	CarritoCompraServiceImpl carrito=new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosServiceImpl baseDatos;
	
	List<Articulo>listaArticulo=new ArrayList<>();
	
	/*@BeforeAll
	void beforeInicializarCarrito()
	{
		
		System.out.println("Inicializando el carrito de compra");
	}*/
	
	@Test
	@Order(5)
	void testLimpiarCesta() {
		carrito.cesta=new ArrayList<>();
		assertEquals(0, carrito.cesta.size());
	}
	
	@AfterEach
	void afterInicializarCarrito()
	{
		System.out.println("Inicializado el carrito de compra");
	}

	@Test
	@Order(1)
	void testAddArticulo() {
		//listaArticulo=new ArrayList<>();
		carrito.addArticulo(articulo);
		carrito.addArticulo(articulo2);
		assertFalse(carrito.cesta.isEmpty());
	}

	@Test
	@Order(4)
	void testGetNumArticulo() {
		assertEquals(2, carrito.getArticulos().size());
	}

	@Test
	void testGetArticulos() {
		List<Articulo>cesta=new ArrayList<>();
		Articulo articulo=new Articulo("Gorrra", 12.95);
		Articulo articulo2=new Articulo("Pantalones", 20.95);
		cesta.add(articulo);
		cesta.add(articulo2);
		assertTrue(cesta.containsAll(carrito.cesta));
	}

	@Test
	@Order(2)
	void testTotalPrice() {
		Double total=0D;
		for (Articulo articulo: carrito.cesta) {
			total=total+articulo.getPrecio();
		}
		assertTrue(total>=0);
	}

	@Test
	@Order(3)
	void testCalcularDescuento() {
		Double precio=10D, descuento=10D;
		precio=precio*descuento/100;
		assertEquals(precio, 1);
	}

	@Test
	void testAplicarDescuento() {
		Articulo articulo=new Articulo("Pantalon", 10.00);
		when(baseDatos.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res=carrito.aplicarDescuento(1, 12D);
		assertEquals(8.8D, res);
		verify(baseDatos, times(1)).findArticuloById(any(Integer.class));
	}
	
	@Test
	void insertarArticuloById()
	{
		int id=0;
		Articulo articulo=new Articulo("Sombrero", 12.95);
		when(baseDatos.insertarArticuloById(any(Articulo.class), any(Integer.class))).thenReturn(8);
		id=carrito.addArticuloById(articulo);
		assertEquals(id, 8);
		carrito.cesta.add(articulo);
		assertTrue(carrito.cesta.contains(articulo));
		verify(baseDatos, times(1)).insertarArticuloById(any(Articulo.class), any(Integer.class));
		
	}
}
