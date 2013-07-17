package aaarrgh.persistence;

public class DaoFactory {

	public static PersonaDao getPersonaDao(){
		return PersonaDaoJdbcImpl.getInstance();
	}
	
	public static TweetDao getTweetDao(){
		return TweetDaoJdbcImpl.getInstance();
	}
	
}
