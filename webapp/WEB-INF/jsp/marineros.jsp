<%@page import="java.util.List"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aaarrgh.model.TweetUser"%>
<%@page import="aaarrgh.persistence.PersonaDao"%>
<%@page import="aaarrgh.persistence.DaoFactory"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String cantidadSeguidores = (String) session.getAttribute("cantidadSeguidores");//Recoge la session
	String cantidadQueSigo = (String) session.getAttribute("cantidadQueSigo");//Recoge la session
	String cambio = (String) session.getAttribute("bandera");//Recoge la session
	String titulo = (String) session.getAttribute("titulo");//Recoge la session
	String idusuario = (String) session.getAttribute("id");//Recoge la session
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aaarrgh Twitter 2.0</title>
<link href="/aaarrgh/js/estilo.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/aaarrgh/js/jquery.js"></script>
<script type="text/javascript" src="/aaarrgh/js/script.js"></script>

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
<li><a href="../login/out.do">Cerrar Sesion</a></li>
</ul>
<div class="logo"> <img src="/aaarrgh/img/web/twitter.png" width="48" height="56" alt="logo" />
</div>
</div>
</div>
<div class="container">
<div class="contentEspecial">

<div class="columnaDerecha">
	<a href="/aaarrgh/perfil/usuario.do?name=<%=idusuario%>" class="miPerfil">Mi Perfil</a>
	<% 
		if(Integer.parseInt(cantidadQueSigo) > 0){%>
			<a href="/aaarrgh/seguimiento/aquiensigo.do" class="miPerfil">A quien sigo <%=cantidadQueSigo%></a>
		<%}else{%>
			<a class="miPerfil">A quien sigo <%=cantidadQueSigo%></a>
		<%}
	%>
	
	<% 
		if(Integer.parseInt(cantidadSeguidores) > 0){%>
			<a href="/aaarrgh/seguimiento/quinesmesiguen.do" class="miPerfil">Quienes me siguen <%=cantidadSeguidores%></a>
		<%}else{%>
			<a class="miPerfil">Quienes me Siguen <%=cantidadSeguidores%></a>
		<%}
	%>
	
	<% 
		if(Integer.parseInt(cambio) > 0){%>
			<a href="/aaarrgh/seguimiento/marineros.do" class="miPerfil">A quien Seguir?</a>
		<%}else{%>
			<a href="/aaarrgh/seguimiento/marineros.do" class="miPerfil" style="display:none">A quien Seguir?</a>
		<%}
	%>
</div>

<h2>${message}</h2> 
 
<form id="formtweets" action="/aaarrgh/tweet/insertar.do" method="post">
<textarea name="tweet" class="campoArea" id="escribirTweet" placeholder="Que hay de nuevo marinero?">
</textarea>
<input type="submit" id="enviar" value="Enviar">
</form> 

<h2><%=titulo%></h2>  
 
<div class="clear"></div>
<div class="listadoTweets">
	 <% 
    for ( Iterator iterador = ( (List<TweetUser>) request.getAttribute("tweetUser")).listIterator(); iterador.hasNext(); ) 
    {
    	TweetUser tweets = (TweetUser) iterador.next();
    	
    	Date now = new Date();
    	
    	now.setTime(tweets.getTiempo().getTime());
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
    	df.setTimeZone(TimeZone.getTimeZone("UTC"));
    	String my8601formattedDate = df.format(now);
    	
    		%><div class="tweet">
	        <p><%=tweets.getTweet()%></p>
	        <div class="info_usuario">
	        <abbr class="timeago" title="<%=my8601formattedDate%>"></abbr>
	        <a class="usuario" href="/aaarrgh/perfil/usuario.do?name=<%=tweets.getIdusuario()%>">@<%=tweets.getUsuario()%></a>
	        
	        <%
	       	 	if(tweets.getEstado().equals("seguir")){%>
		    		<a class="seguir" href="javascript: void(0)" name="<%=tweets.getIdusuario()%>"><%=tweets.getEstado()%></a>
		    	<%}else{
		    		if(tweets.getEstado().equals("siguiendo..")){%>
		    			<a class="seguir" name="<%=tweets.getIdusuario()%>"><%=tweets.getEstado()%></a>
		    		<%}else{
		    			if(tweets.getEstado().equals("dejar de seguir")){%>
		    				<a class="dejarDeseguir" href="javascript: void(0)" name="<%=tweets.getIdusuario()%>"><%=tweets.getEstado()%></a>
		    			<%}else{%>
		    				<a class="volverAseguir" href="javascript: void(0)" name="<%=tweets.getIdusuario()%>"><%=tweets.getEstado()%></a>
		    			<%}
		    		}
	    		
	    		}
	    	%>
    			<%-- <c:choose>
					<c:when test="${tweets.getMensaje eq 'Seguir'}"> <a class="seguir" href="javascript: seguir()" name="<%=tweets.getTweet().getIdusuario()%>"><%=tweets.getMensaje()%></a> </c:when>
					<c:when test="${tweets.getMensaje eq 'siguiendo..'}"><a class="seguir" name="<%=tweets.getTweet().getIdusuario()%>"><%=tweets.getMensaje()%></a></c:when>
					<c:when test="${tweets.getMensaje eq 'Dejar de Seguir'}"><a class="seguir" href="javascript: dejarDeSeguir()" name="<%=tweets.getTweet().getIdusuario()%>"><%=tweets.getMensaje()%></a></c:when>
					<c:otherwise><a class="seguir" href="javascript: volverASeguir()" name="<%=tweets.getTweet().getIdusuario()%>"><%=tweets.getMensaje()%></a></c:otherwise>
				</c:choose> --%>
    			
	        </div>
	        <div class="clearfix">&nbsp;</div> <!--Limpiador de floats-->
        </div>
         <% 
	}
    %>

</div>
 
 <div class="clearfix">&nbsp;</div> <!--Limpiador de floats-->
 
</div>
</div>
<div class="footerTodo">
<div class="footer">
<div class="izquierda">
<p>© 2013 Copyright Quevedo Lucas. All Rights Reserved.</p></div>
<div class="derecha"> <p> Webmasters : Quevedo Lucas Ignacio y Cabañas Matias Jorge</p></div>
</div>
</div>

<script type="text/javascript" src="/aaarrgh/js/jquery.timeago.js"></script>
<script type="text/javascript" src="/aaarrgh/js/jquery.timeago.es.js"></script>

</body>
</html>