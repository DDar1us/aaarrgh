$(document).ready(function(){
	
	jQuery("abbr.timeago").timeago();
	
		$(".seguir").click(function seguir(event){
			event.preventDefault();
		      var current = $(this);		 
			 $.get("/aaarrgh/seguimiento/seguir.do",
					  {
					   idusuario:jQuery(current).attr("name"),
					  }, function(response){
						  $(current).html( response );
						  $.post("/aaarrgh/seguimiento/contador.do", function(response){
							  $("#columnaDerecha").html( response );
						  });
						  
					  });
		   });
	
	
		$(".dejarDeseguir").click(function dejarDeSeguir(event){
			event.preventDefault();
		      var current = $(this);
			 $.get("/aaarrgh/seguimiento/dejardeseguir.do",
					  {
					   idusuario:jQuery(current).attr("name"),
					  }, function(response){
						  $(current).html( response );
						  $.post("/aaarrgh/seguimiento/contador.do", function(response){
							  $("#columnaDerecha").html( response );
						  });
					  });
		   });
		
		$(".volverAseguir").click(function dejarDeSeguir(event){
			event.preventDefault();
		      var current = $(this);
			 $.post("/aaarrgh/seguimiento/volveraseguir.do",
					  {
					   idusuario:jQuery(current).attr("name"),
					  }, function(response){
						  $(current).html( response );
						  
						  $.post("/aaarrgh/seguimiento/contador.do", function(response){
									  $("#columnaDerecha").html( response );
								  });
						  
					  });
		   });
		
		
		contenido_textarea = ""; 
			num_caracteres_permitidos = 140; 

			function valida_longitud(){ 
			   num_caracteres = document.forms[0].texto.value.length; 

			   if (num_caracteres > num_caracteres_permitidos){ 
			      document.forms[0].texto.value = contenido_textarea; 
			   }else{ 
			      contenido_textarea = document.forms[0].texto.value;	
			   } 

			   if (num_caracteres >= num_caracteres_permitidos){ 
			      document.forms[0].caracteres.style.color="#ff0000"; 
			   }else{ 
			      document.forms[0].caracteres.style.color="#000000"; 
			   } 

			   cuenta(); 
			} 
			function cuenta(){ 
			   document.forms[0].caracteres.value=document.forms[0].texto.value.length; 
			} 
	
});