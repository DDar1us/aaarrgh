package aaarrgh.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Persona;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	LoginService loginService = new LoginService();
	Persona usuario = new Persona();

	@RequestMapping("/auth")
	public ModelAndView authenticate( 
			@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request) throws PersistenceException, ServletException {

		ModelAndView dispatch = null;
		
		usuario = loginService.authenticate(username, password);
		List<TweetUser> tweetUser = new LinkedList<TweetUser>();
		
		if (usuario != null) {
			
			HttpSession session = request.getSession(true);

		     //Obtenemos los obejtos a guardar en session
			 session.setAttribute("id", Integer.toString(this.usuario.getId()));
			 session.setAttribute("nombre", this.usuario.getNombre());
		     session.setAttribute("apellido", this.usuario.getApellido());
		     session.setAttribute("edad", Integer.toString(this.usuario.getEdad()));
		     
		     session.setAttribute("cantidadSeguidores", Integer.toString(loginService.meSiguen(this.usuario.getId())));
		     session.setAttribute("cantidadQueSigo", Integer.toString(loginService.sigo(this.usuario.getId())));
		     
		     tweetUser = loginService.finAllUser(Integer.toString(this.usuario.getId()));
		     
			dispatch = new ModelAndView("welcome", "tweetUser", tweetUser); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}

}
