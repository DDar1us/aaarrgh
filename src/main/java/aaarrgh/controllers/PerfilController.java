package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Persona;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.PerfilService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	PerfilService perfilService = new PerfilService();
	
	@RequestMapping("/usuario")
	
	public ModelAndView insertarTweet( 
			@RequestParam("name") String idusuario
			) throws PersistenceException{
		
		ModelAndView dispatch = null;
		
		Persona persona;
		
		persona = perfilService.perfil(Integer.parseInt(idusuario));
			
		dispatch = new ModelAndView("mi_perfil", "persona", persona);
		
		return dispatch;
	}

}