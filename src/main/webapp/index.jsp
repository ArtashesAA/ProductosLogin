<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Aplicación</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="CSS/estilo.css" rel="stylesheet">
</head>
<body>

  <div class="overlay">
    <div class="container">
        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
            <a class="navbar-brand" href="#">Productos APP</a>
        </nav>
        
        <div class="row">
            <div class="col-md-6">
                <!-- Incustra Login para GitHub -->
                <jsp:include page="JSP/elementos/loginGitHub.jsp" />
            </div>
             
            <div class="col-md-6">
                <!-- Incustra Login normal -->
                <jsp:include page="JSP/elementos/login.jsp" />
            </div> 
            
            <div class="col-md-6">
                <!-- Acceso como invitado -->
                <jsp:include page="JSP/elementos/accesoInvitado.jsp" />
                o<a href="JSP/usuario/registro.jsp"> Registrarse</a>
            </div> 
            
        </div>
    </div>
    </div>
    <!-- Scripts de Bootstrap y dependencias -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>


