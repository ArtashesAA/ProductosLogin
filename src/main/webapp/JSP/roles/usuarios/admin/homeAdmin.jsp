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
			<c:otherwise>
				<!-- Código para cualquier otro caso, si es necesario -->
			</c:otherwise>
		</c:choose>

		<a href="https://cdn-icons-png.flaticon.com/512/107/107831.png"></a>

		<h2>Lista de Productos</h2>

		<form action="ListarProducto" method="get">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Peso</th>
						<th>Stock</th>
						<th>Acciones</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="producto" items="${PRODUCTOS}">
						<tr>
							<td>${producto.id}</td>
							<td>${producto.nombre}</td>
							<td>${producto.descripcion}</td>
							<td>${producto.peso}</td>
							<td>${producto.stock}</td>

							<td><jsp:include
									page="../../../elementos/botonModificar.jsp">
									<jsp:param name="id" value="${producto.id}" />
								</jsp:include> <jsp:include page="../../../elementos/botonEliminar.jsp">
									<jsp:param name="id" value="${producto.id}" />
								</jsp:include></td>
						</tr>
					</c:forEach>

				</tbody>
				<a href="crearProducto.jsp">Crear Producto</a>

			</table>
		</form>

		<a class="btn btn-secondary" href="../../../elementos/logout.jsp">Cerrar Sesión</a>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>