<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

   String nombre = (String) session.getAttribute("nombre");//Recoge la session
   String apellido = (String) session.getAttribute("apellido");//Recoge la session
   String edad = (String) session.getAttribute("edad");//Recoge la session

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
<li><a href="index.html">Inicio</a></li>
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
<div class="columnaDerecha">
	<a href="#" class="miPerfil">Mi Perfil</a>
	<a href="#" class="miPerfil">A quien sigo</a>
	<a href="#" class="miPerfil">Quienes me siguen</a>
</div>
<h2>${message}</h2>  

<h1><%=nombre%></h1>

<h1><%=apellido%></h1>

<h1><%=edad%></h1>
  
<form id="formtweets" action="enviar" method="post">
<textarea class="campoArea" id="escribirTweet">

</textarea>

</form>    

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
