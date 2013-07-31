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
		
		ModelAndView dispatch = null;
		String idusuario = (String) request.getSession().getAttribute("id");
		String bandera = (String) request.getSession().getAttribute("bandera");
		
		sigo.setEstado(1);
		sigo.setIdusuario(Integer.parseInt(idusuario));
		sigo.setIdsigo(Integer.parseInt(idSigo));
		
		SeguimientoService.sigo(sigo);
		
		if(Integer.parseInt(bandera) == 0 || Integer.parseInt(bandera) == 1){
			meSiguen.setEstado(1);
			meSiguen.setIdusuario(Integer.parseInt(idSigo));
			meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
			
			SeguimientoService.meSiguen(meSiguen);
			
		}else{
			meSiguen.setEstado(0);
			meSiguen.setIdusuario(Integer.parseInt(idusuario));
			meSiguen.setIdMeSiguen(Integer.parseInt(idSigo));
			
			SeguimientoService.actualizarMeSiguen(meSiguen);
			
			meSiguen.setEstado(0);
			meSiguen.setIdusuario(Integer.parseInt(idSigo));
			meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
			
			SeguimientoService.meSiguen(meSiguen);
			}
		String mensaje = "Dejar de Seguir";
		
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
		
		tweetUser = SeguimientoService.finAllUserSigo(idusuario);
	     
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
	
	tweetUser = SeguimientoService.finAllUserMeSiguen(idusuario);
     
	dispatch = new ModelAndView("marineros", "tweetUser", tweetUser);
	
	return dispatch;
}

@RequestMapping("/marineros")
public ModelAndView finAllUser(HttpServletRequest request) throws PersistenceException, ServletException {
	
	List<TweetUser> tweetUser = new LinkedList<TweetUser>();
	
	String idusuario = (String) request.getSession().getAttribute("id");
	
	HttpSession session = request.getSession();
	
	session.setAttribute("bandera", Integer.toString(0));
	
	session.setAttribute("titulo", "Marineros");
	
	tweetUser = SeguimientoService.finAllUser(idusuario);
	
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
	
	sigo.setEstado(2);
	sigo.setIdusuario(Integer.parseInt(idusuario));
	sigo.setIdsigo(Integer.parseInt(idSigo));
	
	SeguimientoService.actualizarSigo(sigo);
	
	meSiguen.setEstado(2);
	meSiguen.setIdusuario(Integer.parseInt(idSigo));
	meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
		
	SeguimientoService.actualizarMeSiguen(meSiguen);
		
	String mensaje = "volver a seguir";
	
	dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
	
	return dispatch;
}
	
}