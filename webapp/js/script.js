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
					  });
		   });
	
});