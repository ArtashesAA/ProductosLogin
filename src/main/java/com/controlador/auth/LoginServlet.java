package com.controlador.auth;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import com.modelo.BaseDatosUsuario;
import com.servicio.bbdd.BaseDatosUsuarioRepository;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BaseDatosUsuarioRepository usuarioRepository;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// Utilizar el método para crear la configuración y pasarla al repositorio.
			this.usuarioRepository = new BaseDatosUsuarioRepository(createDataSourceConfig());
		} catch (Exception e) {
			throw new ServletException("Error initializing PersonaRepository", e);
		}
	}

	private DataSourceConfig createDataSourceConfig() {
		return new DatabaseConnectionPoolHikari();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("username");
		String clave = request.getParameter("password");

		try {
			Optional<BaseDatosUsuario> usuarioOpt = usuarioRepository.findUsuarioByCredentials(usuario, clave);
			if (usuarioOpt.isPresent()) {
				BaseDatosUsuario usuarioAutenticado = usuarioOpt.get();

				// Guardar el rol del usuario en la sesión
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuarioAutenticado);
				session.setAttribute("rol", obtenerTipoUsuario(usuarioAutenticado.getRol())); // Obtener el tipo de
																								// usuario

				// Redirigir al usuario a la página correspondiente
				String targetPage = redirigirSegunRol(usuarioAutenticado.getRol());
				response.sendRedirect(request.getContextPath() + targetPage);
			} else {
				throw new Exception("Autenticación fallida");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Usar un logger en producción.
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	// Método para obtener el tipo de usuario basado en el rol
	private String obtenerTipoUsuario(int rol) {
		switch (rol) {
		case 1:
			return "usuario";
		case 2:
			return "admin";
		default:
			return "invitado";
		}
	}

	// Método para redirigir según el rol
	private String redirigirSegunRol(int rol) {
		switch (rol) {
		case 1:
			return "/JSP/roles/usuarios/usuario/homeUsuario.jsp"; // Ruta de la página para usuarios
		case 2:
			return "/JSP/roles/usuarios/admin/homeAdmin.jsp"; // Ruta de la página para administradores
		default:
			return "/JSP/roles/usuarios/invitado/homeInvitado.jsp"; // Ruta de la página para invitados
		}
	}

}