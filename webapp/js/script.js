$(document).ready(function(){
	
	jQuery("abbr.timeago").timeago();
	
	 $(".seguir").click(function(evento){
	      evento.preventDefault();
	      
	      var current = $(this);
	     
//		 var idusuario = jQuery(".seguir").attr("name");
//		 
//		 $.ajax({
//				  type: 'POST',
//				  url: '/aaarrgh/seguimiento/seguir.do',
//				  data: idusuario,
//				  success: function(){
//					$('.seguir').html('siguiendo');
//				  }
//				});
//		 
		 $.post("/aaarrgh/seguimiento/seguir.do",
				  {
				   idusuario: jQuery(current).attr("name"),
				  }, function(response){
					  $(current).html( response );
				  });
		 
		 		
	   });
	
	 	
});