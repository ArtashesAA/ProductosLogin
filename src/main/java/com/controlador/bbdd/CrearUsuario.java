package com.controlador.bbdd;

import java.io.IOException;

import com.modelo.BaseDatosUsuario;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.UsuarioRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearProducto
 */
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// Utilizar el método para crear la configuración y pasarla al repositorio.
			this.usuarioRepository = new UsuarioRepository(createDataSourceConfig());
		} catch (Exception e) {
			throw new ServletException("Error initializing PersonaRepository", e);
		}
	}

	private DataSourceConfig createDataSourceConfig() {

		return new DatabaseConnectionPoolHikari();
	}

	// Método post
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// sysout para comprobar en consola que funciona
		System.out.println("CrearUsuario.doPost");

		// Resto del código para procesar la creación del producto
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");

		try {
			// Almacena el producto del formulario en nuevoProducto
			BaseDatosUsuario nuevoUsuario = new BaseDatosUsuario(nombre, apellido, usuario, contrasena);

			// Manda al servicio a que cree un producto
			usuarioRepository.createUsuario(nuevoUsuario);

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
