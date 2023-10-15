<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Producto</title>
</head>
<body>
    
    <form  action="/Productos_JSTL/CrearProducto" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="descripcion">Descripcion:</label>
        <input type="text" id="descripcion" name="descripcion" required><br>
        
        <label for="peso">Peso:</label>
        <input type="text" id="peso" name="peso" required><br>
        
        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" required><br>
        
        <input type="submit" value="Crear">
        
        <br><br>
        
        <a href="index.jsp">volver</a>
    </form>
   
    <!-- Scripts de Bootstrap y dependencias -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> 
</body>
</html>
