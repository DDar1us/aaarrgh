<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String nombre = (String) session.getAttribute("nombre");//Recoge la session	
	String cantidadSeguidores = (String) session.getAttribute("cantidadSeguidores");//Recoge la session
	String cantidadQueSigo = (String) session.getAttribute("cantidadQueSigo");//Recoge la session
	
	if(nombre != null)
		response.sendRedirect("/aaarrgh/login/sesion.do");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
</ul>
<div class="logo"> <img src="/aaarrgh/img/web/twitter.png" width="48" height="56" alt="logo" />
</div>
</div>
</div>
<div class="container">
<div class="contentEspecial">
<h1>Identificate Marinero</h1>    
<form action="/aaarrgh/login/auth.do" id="ingreso" method="post">
<p>Marinero</p>
<p>
  <input type="text" name="username" id="nombre" class="campoTexto" />
</p>
<p>Palabra Secreta</p>
<p>
  <input type="password" name="password" id="nombre" class="campoTexto" />
</p>
<p><input type="submit" class="campoEnviar" value="" /><input type="reset" class="campoBorrar" value="" /></p>

</form>    
<h1>${message}</h1>
</div>
</div>
<div class="footerTodo">
<div class="footer">
<div class="izquierda">
<p>© 2013 Copyright Quevedo Lucas. All Rights Reserved.</p></div>
<div class="derecha"> <p> Webmasters : Quevedo Lucas Ignacio y Cabañas Matias Jorge</p></div>
</div>
</div>
<script type="text/javascript" src="/aaarrgh/js/jquery.css"></script>
</body>
</html>
