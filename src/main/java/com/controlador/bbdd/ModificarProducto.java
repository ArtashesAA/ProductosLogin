package com.controlador.bbdd;

import java.io.IOException;

import com.modelo.Producto;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.ProductoRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarProducto
 */
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoRepository productoRepository;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// Utilizar el método para crear la configuración y pasarla al repositorio.
			this.productoRepository = new ProductoRepository(createDataSourceConfig());
		} catch (Exception e) {
			throw new ServletException("Error initializing PersonaRepository", e);
		}
	}

	private DataSourceConfig createDataSourceConfig() {

		return new DatabaseConnectionPoolHikari();
	}

	/***
	 * Método post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Comprobación de que se realiza el post
		System.out.println("ModificarProducto.doPost");

		try {
			// Recibe parámetros del producto
			Integer id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			Double peso = Double.parseDouble(request.getParameter("peso"));
			Integer stock = Integer.parseInt(request.getParameter("stock"));

			// Se crea un producto para posteriormente almacenar los datos en ella
			Producto nuevoProducto;
			nuevoProducto = new Producto(id, nombre, descripcion, peso, stock);

			// Escribe en consola el producto
			System.out.println(nuevoProducto.toString());

			// Proceso de actualización y comprobación de esta
			boolean actualizado = productoRepository.updateProducto(nuevoProducto);
			if (!actualizado) {
				// Error al actualizar; manejar error
				response.sendRedirect("JSP/mensajes/error.jsp"); // Cambiar a tu página de error
				return;
			}

			response.sendRedirect("JSP/mensajes/exito.jsp"); // Cambiar a tu página de éxito o listado

		} catch (NumberFormatException e) {
			// ID inválido; manejar error
			response.sendRedirect("JSP/mensajes/error.jsp"); // Cambiar a tu página de error
		}
	}

	/***
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Escribe en consola que se realiza la modificación get
		System.out.println("ModificarProducto.doGet");

		try {
			// Recoge el id del producto para recoger los datos de este
			Integer id = Integer.parseInt(request.getParameter("id"));
			Producto producto = productoRepository.readProducto(id);

			// Si el producto esta nulo, envía a página de error
			if (producto == null) {
				response.sendRedirect("error.jsp");
				return;
			}

			// Request de producto
			request.setAttribute("producto", producto);
			RequestDispatcher dispacher = request.getRequestDispatcher("JSP/producto/editarProducto.jsp");
			dispacher.forward(request, response);

		} catch (NumberFormatException e) {
			// Si hay error por introducir un numero en formato no valido, envía a error.jsp
			response.sendRedirect("error.jsp");
		}
	}

}