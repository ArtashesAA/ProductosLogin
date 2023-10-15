<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo</title>
</head>
<body>
	<%!String cadena = "IES ALIXAR";%>
	<h1>
		<%="Fecha y Hora Actual:"%><%=new Date()%>
	</h1>
	<h3><%=cadena%></h3>
</body>
</html>