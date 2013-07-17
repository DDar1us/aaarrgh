package aaarrgh.persistence;

public class DaoFactory {

	public static PersonaDao getPersonaDao(){
		return PersonaDaoJdbcImpl.getInstance();
	}
	
}
