package aaarrgh.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.TweetUser;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.MarinerosService;

@Controller
@RequestMapping("/marineros")
public class MarinerosController {

	MarinerosService MarinerosService = new MarinerosService();
	List<TweetUser> tweetUser = new LinkedList<TweetUser>();

	@RequestMapping("/tweets")
	public ModelAndView finAllUser(HttpServletRequest request) throws PersistenceException, ServletException {
		
		String idusuario = (String) request.getSession().getAttribute("id");
		
		tweetUser = MarinerosService.finAllUser(idusuario);
		
		ModelAndView dispatch = null;
		
		dispatch = new ModelAndView("marineros", "tweetUser", tweetUser); 
		
		return dispatch;

	}

}