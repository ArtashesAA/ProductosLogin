<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="CSS/estilo.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>

	<div>
		<h3>Carrito:</h3>
		<table>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
			</tr>
			<c:forEach var="producto" items="${PRODUCTOS}">
				<tr>
					<td>${producto.nombre}</td>
					<td>${producto.descripcion}</td>
					<td><jsp:include page="../elementos/botonEliminarCarrito.jsp"></jsp:include>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>