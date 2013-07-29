package aaarrgh.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.persistence.PersistenceException;


@Controller
@RequestMapping("/logout")

public class LogoutController {

	@RequestMapping("/out")
	public ModelAndView authenticate(HttpServletRequest request) throws PersistenceException, ServletException {

		ModelAndView dispatch = null;
		
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			dispatch = new ModelAndView("../../index", "message", "Sesión Cerrada");
			
			return dispatch;
		} 

	}

