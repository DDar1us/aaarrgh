<%@page import="aaarrgh.model.Persona"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

	Persona usuario = (Persona) request.getAttribute("persona");
	
	


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aaarrgh Twitter 2.0</title>
<link href="/aaarrgh/js/estilo.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="menuTodo">
<div class="menu">
<ul>
<li><a href="index.jsp">Inicio</a></li>
<li><a href="privacidad">Nosotros</a></li>
<li><a href="privacidad">Privacidad</a></li>
<li><a href="privacidad">Soporte</a></li>
<li><a href="privacidad">Contacto</a></li>
<li><a href="logout/out.do">Cerrar Sesion</a></li>
</ul>
<div class="logo"> <img src="/aaarrgh/img/web/twitter.png" width="48" height="56" alt="logo" />
</div>
</div>
</div>
<div class="container">
<div class="contentEspecial">

<div class="clear"></div>

<h2>@<%=usuario.getNombre()%></h2>

<h2>Perfil</h2>

<ul>
	<li>Nombre: <%=usuario.getNombre()%></li>
	<li>Apellido: <%=usuario.getApellido()%></li>
	<li>Edad: <%=usuario.getEdad()%></li>
</ul>
 
</div>

</div>
<div class="footerTodo">
<div class="footer">
<div class="izquierda">
<p>© 2013 Copyright Quevedo Lucas. All Rights Reserved.</p></div>
<div class="derecha"> <p> Webmasters : Quevedo Lucas Ignacio y Cabañas Matias Jorge</p></div>
</div>
</div>
<script type="text/javascript" src="/aaarrgh/js/jquery.js"></script>
</body>
</html>