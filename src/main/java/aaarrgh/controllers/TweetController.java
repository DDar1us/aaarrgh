package aaarrgh.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import aaarrgh.model.Tweet;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.TweetDao;
import aaarrgh.services.TweetService;

@Controller
@RequestMapping("/tweet")
public class TweetController {

	TweetService TweetService = new TweetService();
	Tweet tweet_insertar = new Tweet();
	TweetDao dao = DaoFactory.getTweetDao();
	
	@RequestMapping("/insertar")
	
	public ModelAndView insertarTweet( 
			@RequestParam("tweet") String tweet, HttpServletRequest request
			) throws PersistenceException{
		
		ModelAndView dispatch = null;
		
		String idusuario = (String) request.getSession().getAttribute("id");
		
		if(idusuario != null){
			this.tweet_insertar.setIdusuario(Integer.parseInt(idusuario));
			this.tweet_insertar.setTweet(tweet);
			Date now = new Date();
			this.tweet_insertar.setNow(now);
			
			TweetService.postear(tweet_insertar);
			
			List<Tweet> todosLosTweets = dao.findAll();
			
			dispatch = new ModelAndView("welcome_tweet", "tweets", todosLosTweets);
			
		}
		
		else
			dispatch = new ModelAndView("../../index", "message", "Sesi�n Cerrada");
		
		return dispatch;
	}

}
