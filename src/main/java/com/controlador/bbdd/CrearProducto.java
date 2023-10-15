package com.controlador.bbdd;

import java.io.IOException;

import com.modelo.Producto;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.ProductoRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearProducto
 */
public class CrearProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductoRepository productoRepository;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// Utilizar el método para crear la configuración y pasarla al repositorio.
			this.productoRepository = new ProductoRepository(createDataSourceConfig());
		} catch (Exception e) {
			throw new ServletException("Error initializing ProductoRepository", e);
		}
	}

	private DataSourceConfig createDataSourceConfig() {

		return new DatabaseConnectionPoolHikari();
	}

	// Método post
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// sysout para comprobar en consola que funciona
		System.out.println("CrearProducto.doPost");

		// Resto del código para procesar la creación del producto
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String pesoStr = request.getParameter("peso");
		String stockStr = request.getParameter("stock");

		try {
			// Pasa el string recibido en el formulario a double e integer
			Double peso = Double.parseDouble(pesoStr);
			Integer stock = Integer.parseInt(stockStr);

			// Almacena el producto del formulario en nuevoProducto
			Producto nuevoProducto = new Producto(nombre, descripcion, peso, stock);

			// Manda al servicio a que cree un producto
			productoRepository.createProducto(nuevoProducto);

			// Si funciona correctamente, se envía al exito.jsp
			response.sendRedirect("JSP/mensajes/exito.jsp");
		} catch (NumberFormatException e) {
			// Si da error de formato de número, se envía al error.jsp
			e.printStackTrace();
			response.sendRedirect("JSP/mensajes/error.jsp");
		} catch (Exception e) {
			// Si da otro error cualquiera, se envía al error.jsp
			e.printStackTrace();
			response.sendRedirect("JSP/mensajes/error.jsp");
		}

	}
}
