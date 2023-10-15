package com.controlador.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.servicio.ConfigLoader;


public class GitHubServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize";

    private ConfigLoader configLoader;
   

    @Override
    public void init() throws ServletException {
        configLoader = ConfigLoader.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String githubAuthUrl = buildGitHubAuthUrl();
            System.out.println("GitHubServlet.doGet --> githubAuthUrl:" + githubAuthUrl);
            response.sendRedirect(githubAuthUrl);
        } catch (MalformedURLException e) {
            throw new ServletException("Error al construir la URL de autorización de GitHub", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private String buildGitHubAuthUrl() throws ServletException {
        String clientId = configLoader.getClientId();
        String redirectUri = configLoader.getRedirectUri();

        // Validación de parámetros nulos
        if (clientId == null || redirectUri == null) {
            throw new ServletException("El ClientId o el RedirectUri no pueden ser nulos");
        }

        // Validación de URL
        try {
            new URL(redirectUri);
        } catch (MalformedURLException e) {
            throw new ServletException("La URL de redirección no es válida", e);
        }

        // Construcción de la URL de autenticación
        try {
            return String.format("%s?client_id=%s&redirect_uri=%s",
                    GITHUB_AUTH_URL,
                    clientId,
                    URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Error al codificar la URL de redirección", e);
        }
    }
}