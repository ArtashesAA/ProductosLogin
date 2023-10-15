package com.controlador.bbdd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.modelo.BaseDatosUsuario;
import com.servicio.bbdd.BaseDatosUsuarioRepository;
import com.servicio.bbdd.DataSourceConfig;
import com.servicio.bbdd.ProductoUsuarioRepository;
import com.servicio.bbdd.impl.DatabaseConnectionPoolHikari;

/**
 * Servlet implementation class AgregarCarrito
 */
public class AgregarCarrito extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoUsuarioRepository productoUsuarioRepository;
    private BaseDatosUsuarioRepository usuarioRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa los repositorios necesarios.
        this.productoUsuarioRepository = new ProductoUsuarioRepository(createDataSourceConfig());
        this.usuarioRepository = new BaseDatosUsuarioRepository(createDataSourceConfig());
    }

    // Método para configurar la fuente de datos (DataSource). Reemplaza esto con tu implementación real.
    private DataSourceConfig createDataSourceConfig() {
        return new DatabaseConnectionPoolHikari();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el ID del producto que se va a agregar al carrito.
        String productoIdParam = request.getParameter("productoId");
             
        int productoId;
        if (productoIdParam != null && !productoIdParam.isEmpty()) {
            productoId = Integer.parseInt(productoIdParam);

            // El usuario actualmente autenticado desde la sesión.
            HttpSession session = request.getSession();
            BaseDatosUsuario usuario = (BaseDatosUsuario) session.getAttribute("usuario");

            if (usuario != null) {
                // Usuario autenticado, ahora puedes agregar el producto al carrito.
                boolean productoAgregado = productoUsuarioRepository.agregarProductoAlCarrito(usuario.getId(), productoId);

                if (productoAgregado) {
                    // Producto agregado exitosamente, realiza alguna acción o redirige a una página de éxito.
                    response.sendRedirect("JSP/usuario/carrito.jsp");
                } else {
                    // Hubo un error al agregar el producto al carrito, maneja el error apropiadamente.
                    response.sendRedirect("JSP/mensajes/error.jsp");
                }
            } else {
                // Si no hay usuario autenticado, redirige al usuario para que inicie sesión.
                response.sendRedirect("JSP/home.jsp");
            }
        } else {
            // Manejo de errores: productoIdParam es nulo o una cadena vacía.
            // Puedes redirigir a una página de error o realizar alguna otra acción apropiada.
            response.sendRedirect("JSP/mensajes/error.jsp");
        }
    }
}