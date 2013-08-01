package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Persona;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.RegistroService;

@Controller
@RequestMapping("/registro")
public class RegistroController {
	
	RegistroService RegistroService = new RegistroService();
	Persona persona = new Persona();
	
	@RequestMapping("/usuario")
	
	public ModelAndView insertarTweet( 
			@RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("apellido") String apellido,
			@RequestParam("edad") String edad
			) throws PersistenceException{
		
		ModelAndView dispatch = null;
		
		persona.setApellido(apellido);
		persona.setNombre(username);
		persona.setEdad(Integer.parseInt(edad));
		persona.setPassword(password);
		
		RegistroService.registro(persona);
			
		dispatch = new ModelAndView("../../index", "message", "registrado");
		
		return dispatch;
	}
}