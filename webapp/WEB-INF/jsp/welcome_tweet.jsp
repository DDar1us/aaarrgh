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

	String idusuario = (String) session.getAttribute("id");//Recoge la session
	String nombre = (String) session.getAttribute("nombre");//Recoge la session
   String apellido = (String) session.getAttribute("apellido");//Recoge la session
   String edad = (String) session.getAttribute("edad");//Recoge la session
   
   PersonaDao dao = DaoFactory.getPersonaDao();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aaarrgh Twitter 2.0</title>
<link href="/aaarrgh/js/estilo.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
	
	$("#perfil").click(
			function(){
				
				$(".contentEspecial").empty();
				$(".contentEspecial").html("<h2>Nombre:"+<%=nombre%>"</h2><h2>Apellido:"+<%=apellido %>"</h2><h2>Edad:"+<%=edad %>"</h2>");
				
			});
});
</script>


</head>

<body>
<div class="menuTodo">
<div class="menu">
<ul>
<li><a href="../index.jsp">Inicio</a></li>
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
	<a href="#" class="miPerfil">A quien sigo</a>
	<a href="#" class="miPerfil">Quienes me siguen</a>
</div>

<h2>${message}</h2>  

<form id="formtweets" action="../tweet/insertar.do" method="post">
<textarea name="tweet" class="campoArea" id="escribirTweet" placeholder="Que hay de nuevo marinero?">
</textarea>
<input type="text" name="idusuario" value="<%=idusuario%>" style="display: none;"/>
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
<p>© 2013 Copyright Quevedo Lucas. All Rights Reserved.</p></div>
<div class="derecha"> <p> Webmasters : Quevedo Lucas Ignacio y Cabañas Matias Jorge</p></div>
</div>
</div>
<script type="text/javascript" src="/aaarrgh/js/jquery.js"></script>
</body>
</html>