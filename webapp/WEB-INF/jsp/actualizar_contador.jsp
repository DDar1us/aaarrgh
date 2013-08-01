<%
	String nombre = (String) session.getAttribute("nombre");//Recoge la session	
	String idusuario = (String) session.getAttribute("id");//Recoge la session
	String cantidadSeguidores = (String) session.getAttribute("cantidadSeguidores");//Recoge la session
	String cantidadQueSigo = (String) session.getAttribute("cantidadQueSigo");//Recoge la session

%>

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
	
	<a href="/aaarrgh/seguimiento/marineros.do" class="miPerfil">A quien seguir?</a>