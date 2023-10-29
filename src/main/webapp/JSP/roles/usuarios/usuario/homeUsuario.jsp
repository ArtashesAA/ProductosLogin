<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="../../../../CSS/estilo.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>

	<div class="container my-3">
		<h1>APP Productos</h1>
		<c:choose>
			<c:when test="${sessionScope.tipo eq 'BaseDatosUsuario'}">
				<!-- Código para mostrar información del UsuarioCredenciales -->
				<h2>Hola ${sessionScope.usuario.nombre}
					${sessionScope.usuario.apellido}</h2>
			</c:when>
			<c:when test="${sessionScope.tipo eq 'GitHubUsuario'}">
				<!-- Código para mostrar información del GitHubUsuario -->
				<h1>GitHubUsuario</h1>
				<h2>Hola ${sessionScope.usuario.name}
					(${sessionScope.usuario.login})</h2>
			</c:when>
		</c:choose>

		<h2>Lista de Productos</h2>

		<form action="/Productos_JSTL/ListarProducto" method="get">
			<table>
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Peso</th>
						<th>Stock</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="producto" items="${PRODUCTOS}">
						<tr>
							<td>${producto.nombre}</td>
							<td>${producto.descripcion}</td>
							<td>${producto.peso}</td>
							<td><jsp:include page="../../../elementos/botonAgregarCarrito.jsp"></jsp:include></td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>

		</form>
		
		<br>
			<a href="<%=request.getContextPath()%>/logout">Cerrar Sesión</a>
		</div>
	
	

	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>