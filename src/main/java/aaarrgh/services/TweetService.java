package aaarrgh.services;

import aaarrgh.model.Tweet;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.TweetDao;

public class TweetService {

	public void postear(Tweet tweet) throws PersistenceException{
		
		TweetDao mensaje = DaoFactory.getTweetDao();
		
		mensaje.insert(tweet);
		
	}
	
	
}
