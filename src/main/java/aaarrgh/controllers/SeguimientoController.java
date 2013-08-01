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

import aaarrgh.model.Seguimiento;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.SeguimientoService;

@Controller
@RequestMapping("/seguimiento")
public class SeguimientoController {

	SeguimientoService SeguimientoService = new SeguimientoService();
	
	
	
	@RequestMapping("/seguir")
	
	public ModelAndView seguir(HttpServletRequest request, @RequestParam("idusuario") String idSigo
			) throws PersistenceException{
		
		Seguimiento seguimiento = new Seguimiento();
		String mensaje;
		
		ModelAndView dispatch = null;
		
		String idusuario = (String) request.getSession().getAttribute("id");
		String bandera = (String) request.getSession().getAttribute("bandera");
		
		HttpSession session = request.getSession();
		session.setAttribute("cantidadSeguidores", Integer.toString(SeguimientoService.contarSeguidores(Integer.parseInt(idusuario))));
	    session.setAttribute("cantidadQueSigo", Integer.toString(SeguimientoService.contarSigo(Integer.parseInt(idusuario))));
		
		if(Integer.parseInt(bandera) == 0 || Integer.parseInt(bandera) == 1){
			
			seguimiento.setEstadoSeguidor("dejar de seguir");
			seguimiento.setEstadoSeguido("seguir");
			seguimiento.setIdSeguidor(Integer.parseInt(idusuario));
			seguimiento.setIdSeguido(Integer.parseInt(idSigo));
			
			SeguimientoService.seguimiento(seguimiento);
			
			mensaje = "dejar de seguir";
			
		}else{
			seguimiento.setEstadoSeguidor("dejar de seguir");
			seguimiento.setEstadoSeguido("siguiendo..");
			seguimiento.setIdSeguidor(Integer.parseInt(idusuario));
			seguimiento.setIdSeguido(Integer.parseInt(idSigo));
			
			SeguimientoService.seguimiento(seguimiento);
			
			mensaje = "siguiendo..";
			}
		
		dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
		
		return dispatch;
	}
	
@RequestMapping("/aquiensigo")
	
	public ModelAndView aQuienSigo(HttpServletRequest request
			) throws PersistenceException{
		
		ModelAndView dispatch = null;
	
		List<TweetUser> tweetUser = new LinkedList<TweetUser>();
		
		String idusuario = (String) request.getSession().getAttribute("id");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("bandera", Integer.toString(1));
		
		session.setAttribute("titulo", "A quienes Sigo..");
		
		tweetUser = SeguimientoService.buscarTodosLosTweetsaQuienesSigo(idusuario);
	     
		dispatch = new ModelAndView("marineros", "tweetUser", tweetUser);
		
		return dispatch;
	}

@RequestMapping("/quinesmesiguen")

public ModelAndView quienesMeSiguen(HttpServletRequest request
		) throws PersistenceException{
	
	ModelAndView dispatch = null;

	List<TweetUser> tweetUser = new LinkedList<TweetUser>();
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	
	session.setAttribute("bandera", Integer.toString(2));
	
	session.setAttribute("titulo", "Quienes me siguen..");
	
	tweetUser = SeguimientoService.buscarTodosLosTweetsDeMisSeguidores(idusuario);
     
	dispatch = new ModelAndView("marineros", "tweetUser", tweetUser);
	
	return dispatch;
}

@RequestMapping("/marineros")
public ModelAndView finAllUser(HttpServletRequest request) throws PersistenceException, ServletException {
	
	List<TweetUser> tweetUser = new LinkedList<TweetUser>();
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	
	session.setAttribute("bandera", Integer.toString(0));
	
	session.setAttribute("titulo", "A quien Seguir?");
	
	tweetUser = SeguimientoService.buscarTweetDelosUsuariosMenosElMio(idusuario);
	
	ModelAndView dispatch = null;
	
	dispatch = new ModelAndView("marineros", "tweetUser", tweetUser); 
	
	return dispatch;

}

@RequestMapping("/dejardeseguir")

public ModelAndView dejarDeSeguir(HttpServletRequest request, @RequestParam("idusuario") String idSigo
		) throws PersistenceException{
	
	Seguimiento seguimiento = new Seguimiento();
	
	ModelAndView dispatch = null;
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	session.setAttribute("cantidadSeguidores", Integer.toString(SeguimientoService.contarSeguidores(Integer.parseInt(idusuario))));
    session.setAttribute("cantidadQueSigo", Integer.toString(SeguimientoService.contarSigo(Integer.parseInt(idusuario))));
	
	seguimiento.setEstadoSeguidor("volver a seguir");
	seguimiento.setIdSeguidor(Integer.parseInt(idusuario));
	seguimiento.setIdSeguido(Integer.parseInt(idSigo));
	
	SeguimientoService.actualizarSigo(seguimiento);
		
	String mensaje = "volver a seguir";
	
	dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
	
	return dispatch;
}

@RequestMapping("/volveraseguir")

public ModelAndView volveraseguir(HttpServletRequest request, @RequestParam("idusuario") String idSigo
		) throws PersistenceException{
	
	Seguimiento seguimiento = new Seguimiento();
	
	ModelAndView dispatch = null;
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	session.setAttribute("cantidadSeguidores", Integer.toString(SeguimientoService.contarSeguidores(Integer.parseInt(idusuario))));
    session.setAttribute("cantidadQueSigo", Integer.toString(SeguimientoService.contarSigo(Integer.parseInt(idusuario))));
	
	seguimiento.setEstadoSeguidor("dejar de seguir");
	seguimiento.setIdSeguidor(Integer.parseInt(idusuario));
	seguimiento.setIdSeguido(Integer.parseInt(idSigo));
	
	SeguimientoService.actualizarSigo(seguimiento);
		
	String mensaje = "dejar de seguir";
	
	dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
	
	return dispatch;
}

@RequestMapping("/contador")

public ModelAndView actualizarContador(HttpServletRequest request) throws PersistenceException{
	
	ModelAndView dispatch = null;
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	session.setAttribute("cantidadSeguidores", Integer.toString(SeguimientoService.contarSeguidores(Integer.parseInt(idusuario))));
    session.setAttribute("cantidadQueSigo", Integer.toString(SeguimientoService.contarSigo(Integer.parseInt(idusuario))));
	
	dispatch = new ModelAndView("actualizar_contador", "mensaje", "actualizado");
	
	return dispatch;
}
	
}