<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Aplicación</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../CSS/estilo.css" rel="stylesheet">
</head>
<body>

  <div class="overlay">
	 <form  action="/Productos_JSTL/CrearUsuario" method="post">
	 	<h2>Registro</h2>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required><br>
        
        <label for="contrasena">Contraseña:</label>
        <input type="text" id="contrasena" name="contrasena" required><br>
        
        <input type="submit" value="Registrarme">
        
        <br><br>
        
        <a href="../../index.jsp">volver</a>
    </form>
  </div>
    <!-- Scripts de Bootstrap y dependencias -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>


