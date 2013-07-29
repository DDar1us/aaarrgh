package aaarrgh.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.SeguimientoService;

@Controller
@RequestMapping("/seguimiento")
public class SeguimientoController {

	SeguimientoService SeguimientoService = new SeguimientoService();
	
	@RequestMapping("/seguir")
	
	public ModelAndView seguir(HttpServletRequest request
			) throws PersistenceException{
		
		Sigo sigo = new Sigo();
		MeSiguen meSiguen = new MeSiguen();
		String mensaje = "Dejar de Seguir";
		
		ModelAndView dispatch = null;
		
		String idSigo = request.getParameter("idusuario");
		
		String idusuario = (String) request.getSession().getAttribute("id");
		
		sigo.setEstado(1);
		sigo.setIdusuario(Integer.parseInt(idusuario));
		sigo.setIdsigo(Integer.parseInt(idSigo));
		
		meSiguen.setIdusuario(Integer.parseInt(idSigo));
		meSiguen.setIdMeSiguen(Integer.parseInt(idusuario));
		
		SeguimientoService.seguir(sigo, meSiguen);
		
		dispatch = new ModelAndView("seguimiento", "mensaje", mensaje);
		
		return dispatch;
	}
	
}