<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Productos</title>
</head>
<body>
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
					<c:choose>	
						<c:when test="${sessionScope.usuario.rol eq '2'}">	
							<th>Acciones</th>
						</c:when>
				    </c:choose>
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
					
					<c:choose>	
						<c:when test="${sessionScope.usuario.rol eq '2'}">	
							<td><jsp:include page="../elementos/botonModificar.jsp">
								<jsp:param name="id" value="${producto.id}" />
							</jsp:include> <jsp:include page="../elementos/botonEliminar.jsp">
								<jsp:param name="id" value="${producto.id}" />
							</jsp:include></td>
						</c:when>
						<c:when test="${sessionScope.usuario.rol eq '1'}">
		                    <c:choose>
		                        <c:when test="${sessionScope.tipo ne 'Invitado'}">
		                            <td>
		                                <jsp:include page="../elementos/botonAgregarCarrito.jsp"></jsp:include>
		                            </td>
		                        </c:when>
		                    </c:choose>
		                </c:when>
				    </c:choose>
						
					</tr>
				</c:forEach>
								
			</tbody>
			<c:choose>	
				<c:when test="${sessionScope.usuario.rol eq '2'}">
					<a href="JSP/producto/crearProducto.jsp">Crear Producto</a>
				</c:when>
			</c:choose>
			
		</table>
		
		<a href="index.jsp">volver</a>
		
	</form>
</body>
</html>
