package aaarrgh.controllers;

import java.util.List;

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
			@RequestParam("tweet") String tweet, @RequestParam("idusuario") String idusuario
			) throws PersistenceException{
		
		ModelAndView dispatch = null;
		
		this.tweet_insertar.setIdTweet(1);
		this.tweet_insertar.setIdusuario(Integer.parseInt(idusuario));
		this.tweet_insertar.setTweet(tweet);
		
		TweetService.postear(tweet_insertar);
		
		List<Tweet> todosLosTweets = dao.findAll();
		
		dispatch = new ModelAndView("welcome_tweet", "tweets", todosLosTweets);
		
		return dispatch;
	}

}
