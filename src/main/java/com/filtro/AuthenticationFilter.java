package com.filtro;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/home/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		if (session == null || session.getAttribute("usuario") == null) {
			httpResponse.sendRedirect("index.jsp");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Aquí puedes realizar la limpieza del filtro si es necesario
	}
}
