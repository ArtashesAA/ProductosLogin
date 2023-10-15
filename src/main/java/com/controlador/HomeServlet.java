package com.controlador;

import java.io.IOException;
import com.modelo.BaseDatosUsuario;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.ProductoRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private ProductoRepository productoRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
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


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Verificar si hay un usuario autenticado
	    HttpSession session = request.getSession();
	    BaseDatosUsuario usuarioAutenticado = (BaseDatosUsuario) session.getAttribute("usuario");
	    if (usuarioAutenticado != null) {
	        // Hay un usuario autenticado, redirige a la página de inicio de acuerdo a su rol
	        int rol = usuarioAutenticado.getRol();
	        String homePage = getHomePageForRole(rol);
	        if (homePage != null) {
	            response.sendRedirect(request.getContextPath() + "/" + homePage);
	        } else {
	            // Redirigir a una página de error si el rol no se reconoce
	            response.sendRedirect(request.getContextPath() + "/JSP/mensajes/error.jsp");
	        }
	    } else {
	        // No hay un usuario autenticado, muestra un mensaje de error
	        response.sendRedirect(request.getContextPath() + "/JSP/mensajes/error.jsp");
	    }
	}

	private String getHomePageForRole(int rol) {
	    switch (rol) {
	        case 1:
	            return "JSP/roles/usuario/homeUsuario.jsp";
	        case 2:
	            return "JSP/roles/admin/homeAdmin.jsp";
	        case 3:
	            return "JSP/roles/invitado/homeInvitado.jsp";
	        default:
	            return null;
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
