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

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;
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
		
		Sigo sigo = new Sigo();
		MeSiguen meSiguen = new MeSiguen();
		String mensaje;
		
		ModelAndView dispatch = null;
		
		String idusuario = (String) request.getSession().getAttribute("id");
		String bandera = (String) request.getSession().getAttribute("bandera");
		
		HttpSession session = request.getSession();
		session.setAttribute("cantidadSeguidores", Integer.toString(SeguimientoService.contarSeguidores(Integer.parseInt(idusuario))));
	    session.setAttribute("cantidadQueSigo", Integer.toString(SeguimientoService.contarSigo(Integer.parseInt(idusuario))));
		
		sigo.setEstado("dejar de seguir");
		sigo.setIdusuario(Integer.parseInt(idusuario));
		sigo.setIdsigo(Integer.parseInt(idSigo));
		
		SeguimientoService.sigo(sigo);
		
		if(Integer.parseInt(bandera) == 0 || Integer.parseInt(bandera) == 1){
			meSiguen.setEstado("seguir");
			meSiguen.setIdusuario(Integer.parseInt(idSigo));
			meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
			
			SeguimientoService.meSiguen(meSiguen);
			
			mensaje = "dejar de seguir";
			
		}else{
			meSiguen.setEstado("siguiendo..");
			meSiguen.setIdusuario(Integer.parseInt(idusuario));
			meSiguen.setIdMeSiguen(Integer.parseInt(idSigo));
			
			SeguimientoService.actualizarMeSiguen(meSiguen);
			
			meSiguen.setEstado("siguiendo..");
			meSiguen.setIdusuario(Integer.parseInt(idSigo));
			meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
			
			SeguimientoService.meSiguen(meSiguen);
			
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
	
	Sigo sigo = new Sigo();
	MeSiguen meSiguen = new MeSiguen();
	
	ModelAndView dispatch = null;
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	sigo.setEstado("volver a seguir");
	sigo.setIdusuario(Integer.parseInt(idusuario));
	sigo.setIdsigo(Integer.parseInt(idSigo));
	
	SeguimientoService.actualizarSigo(sigo);
	
	meSiguen.setEstado("seguir");
	meSiguen.setIdusuario(Integer.parseInt(idusuario));
	meSiguen.setIdMeSiguen(Integer.parseInt(idSigo));
		
	SeguimientoService.actualizarMeSiguen(meSiguen);
	
	meSiguen.setEstado("siguiendo..");
	meSiguen.setIdusuario(Integer.parseInt(idSigo));
	meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
	
	SeguimientoService.meSiguen(meSiguen);
		
	String mensaje = "volver a seguir";
	
	dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
	
	return dispatch;
}

@RequestMapping("/volveraseguir")

public ModelAndView volveraseguir(HttpServletRequest request, @RequestParam("idusuario") String idSigo
		) throws PersistenceException{
	
	Sigo sigo = new Sigo();
	MeSiguen meSiguen = new MeSiguen();
	
	ModelAndView dispatch = null;
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	sigo.setEstado("dejar de seguir");
	sigo.setIdusuario(Integer.parseInt(idusuario));
	sigo.setIdsigo(Integer.parseInt(idSigo));
	
	SeguimientoService.actualizarSigo(sigo);
	
	meSiguen.setEstado("siguiendo..");
	meSiguen.setIdusuario(Integer.parseInt(idusuario));
	meSiguen.setIdMeSiguen(Integer.parseInt(idSigo));
		
	SeguimientoService.actualizarMeSiguen(meSiguen);
	
	meSiguen.setEstado("siguiendo..");
	meSiguen.setIdusuario(Integer.parseInt(idSigo));
	meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
	
	SeguimientoService.meSiguen(meSiguen);
		
	String mensaje = "volver a seguir";
	
	dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
	
	return dispatch;
}
	
}