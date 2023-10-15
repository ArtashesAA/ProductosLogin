package com.controlador.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.modelo.GitHubUsuario;
import com.servicio.auth.impl.GitHubAppAuthenticationService;


/**
 * Servlet implementation class CallbackServlet
 */
@WebServlet("/callback")

public class CallbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
  
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code"); // Obtén el código de autenticación del request
            System.out.println("CallbackServlet.doGet.code:"+code);
           
            if (code == null || code.isBlank()) throw new ServletException("Código de autorización no válido.");
 
            String token = GitHubAppAuthenticationService.getInstance().getAccessToken(code);
            System.out.println("CallbackServlet.doGet.token:"+token);
   
            GitHubUsuario usuarioAutenticado =  GitHubAppAuthenticationService.getInstance().getUserGitHubUsuario(token);

            System.out.println("### Sesion:"+usuarioAutenticado.toString());

            // Crear sesión y establecer el usuario como atributo de sesión
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuarioAutenticado);
            session.setAttribute("tipo", "GitHubUsuario");

            // Redirigir al usuario a la página principal
            response.sendRedirect(request.getContextPath() + "/JSP/roles/usuarios/usuario/homeUsuario.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Autenticación fallida");
        }
    }

}

