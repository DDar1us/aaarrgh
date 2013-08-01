<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String cantidadSeguidores = (String) session.getAttribute("cantidadSeguidores");//Recoge la session
	String cantidadQueSigo = (String) session.getAttribute("cantidadQueSigo");//Recoge la session
%>

    ${mensaje}