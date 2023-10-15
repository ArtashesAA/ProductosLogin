package com.controlador.bbdd;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ListarProducto
 */
public class ListarProducto extends HttpServlet {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Comprobación de que se realiza el get
		System.out.println("ListarProducto.doGET");

		String rol = "usuario";  // Cambia esto según tu lógica para obtener el rol del usuario

	    // Determina la página de inicio según el rol
	    String homePage;
	    if ("2".equals(rol)) {
	        homePage = "/JSP/roles/usuarios/admin/homeAdmin.jsp";
	    } else if ("1".equals(rol)) {
	        homePage = "/JSP/roles/usuarios/usuario/homeUsuario.jsp";
	    } else{
	        homePage = "/JSP/roles/usuarios/invitado/homeInvitado.jsp";
	    } 

	    // Se guarda en una lista todos los productos que recibe el servicio
	    List<Producto> productos = productoRepository.getAllProductos();
	    request.setAttribute("PRODUCTOS", productos);

	    // Redirige a la página de inicio correspondiente
	    RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Comprobación de que se realiza el post
		System.out.println("ListarProducto.doPost");

		String rol = "usuario";  // Cambia esto según tu lógica para obtener el rol del usuario

	    // Determina la página de inicio según el rol
	    String homePage;
	    if ("2".equals(rol)) {
	        homePage = "/JSP/roles/usuarios/admin/homeAdmin.jsp";
	    } else if ("1".equals(rol)) {
	        homePage = "/JSP/roles/usuarios/usuario/homeUsuario.jsp";
	    } else {
	        homePage = "/JSP/roles/usuarios/invitado/homeInvitado.jsp";
	    }

	    // Se guarda en una lista todos los productos que recibe el servicio
	    List<Producto> productos = productoRepository.getAllProductos();
	    request.setAttribute("PRODUCTOS", productos);

	    // Redirige a la página de inicio correspondiente
	    RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
	    dispatcher.forward(request, response);
	}

}
