$(document).ready(function(){
	
	$("#perfil").click(
			function(){
				
				$(".contentEspecial").empty();
				$(".contentEspecial").html("<h2>Nombre: <%=nombre%></h2><h2>Apellido: <%=apellido %></h2><h2>Edad: <%=edad %></h2>");
				
			});
});