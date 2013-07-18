package aaarrgh.persistence;

import java.util.List;
import aaarrgh.model.Tweet;

public interface TweetDao {

public void insert(Tweet tweet) throws PersistenceException;
    
    public void delete(Tweet tweet) throws PersistenceException;
    
    public void update(Tweet tweet) throws PersistenceException;
    
    public Tweet findById(Integer idtweet) throws PersistenceException;
    
    public List<Tweet> findAll() throws PersistenceException;
    
    public int contarTweet(Integer contarTweet) throws PersistenceException;
	
}
