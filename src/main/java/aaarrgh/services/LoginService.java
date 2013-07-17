package aaarrgh.services;


import aaarrgh.model.Persona;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;

public class LoginService {
	
	PersonaDao logindao = DaoFactory.getPersonaDao();
	Persona usuario = new Persona();
	
	public Persona authenticate(String username, String password) throws PersistenceException {
		return logindao.authenticate(username, password);
	}
	
}
