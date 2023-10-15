package com.controlador.bbdd;

import java.io.IOException;

import com.servicio.bbdd.BaseDatosUsuarioRepository;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.ProductoRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class EliminarProducto
 */
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoRepository productoRepository;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarProducto() {
		super();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recibe el id del producto seleccionado
		String id = request.getParameter("id");

		// Comprobación en consola del producto que se va a eliminar
		System.out.println("EliminarProducto.doPost id: " + id);

		try {

			if (productoRepository.deleteProducto(Integer.parseInt(id))) {
				// Si se borra correctamente, se envía a exito.jsp
				response.sendRedirect("JSP/mensajes/exito.jsp");
			} else {
				// Sino, se envía a error.jsp
				response.sendRedirect("JSP/mensajes/error.jsp");
			}

		} catch (Exception e) {
			// Si da erroro, se envía al error.jsp
			System.out.println(e.toString());
			response.sendRedirect("JSP/mensajes/error.jsp");
		}
	}

}
