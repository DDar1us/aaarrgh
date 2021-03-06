<%@page import="java.util.List"%>
<%@page import="aaarrgh.model.Tweet"%>
<%@page import="aaarrgh.model.Persona"%>
<%@page import="aaarrgh.persistence.PersonaDao"%>
<%@page import="aaarrgh.persistence.DaoFactory"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String cantidadSeguidores = (String) session.getAttribute("cantidadSeguidores");//Recoge la session
	String cantidadQueSigo = (String) session.getAttribute("cantidadQueSigo");//Recoge la session
   
   PersonaDao dao = DaoFactory.getPersonaDao();

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
<li><a href="/aaarrgh/index.jsp">Inicio</a></li>
<li><a href="privacidad">Nosotros</a></li>
<li><a href="privacidad">Privacidad</a></li>
<li><a href="privacidad">Soporte</a></li>
<li><a href="privacidad">Contacto</a></li>
<li><a href="../logout/out.do">Cerrar Sesion</a></li>
</ul>
<div class="logo"> <img src="/aaarrgh/img/web/twitter.png" width="48" height="56" alt="logo" />
</div>
</div>
</div>
<div class="container">
<div class="contentEspecial">

<div class="columnaDerecha">
	<a href="../mi_perfil.jsp" class="miPerfil">Mi Perfil</a>
	<a href="#" class="miPerfil">A quien sigo <%=cantidadQueSigo%></a>
	<a href="#" class="miPerfil">Quienes me siguen <%=cantidadSeguidores%></a>
	<a href="/aaarrgh/marineros/tweets.do" class="miPerfil">Marineros</a>
</div>

<h2>${message}</h2> 
 
<form id="formtweets" action="/aaarrgh/tweet/insertar.do" method="post">
<textarea name="tweet" class="campoArea" id="escribirTweet" placeholder="Que hay de nuevo marinero?">
</textarea>
<input type="submit" id="enviar" value="Enviar">
</form>   
 
<div class="clear"></div>
<h1>Tweets</h1>
<ul class="listadoTweets">
	 <% 
    for ( Iterator iterador = ( (List<Tweet>) request.getAttribute("tweets")).listIterator(); iterador.hasNext(); ) 
    {
        
    	Persona usuario = new Persona();
    	
    	Tweet tweets = (Tweet) iterador.next();
    	
    	usuario = dao.findById(tweets.getIdusuario());
        %>
        <li><%=tweets.getTweet()%>
        <p>@<%=usuario.getNombre()%></p>
        </li>
         <%
	}
    %>
</ul>
 
</div>
</div>
<div class="footerTodo">
<div class="footer">
<div class="izquierda">
<p>� 2013 Copyright Quevedo Lucas. All Rights Reserved.</p></div>
<div class="derecha"> <p> Webmasters : Quevedo Lucas Ignacio y Caba�as Matias Jorge</p></div>
</div>
</div>
<script type="text/javascript" src="/aaarrgh/js/jquery.js"></script>
</body>
</html>