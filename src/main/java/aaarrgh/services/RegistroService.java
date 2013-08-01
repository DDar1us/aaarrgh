package aaarrgh.services;

import aaarrgh.model.Persona;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;

public class RegistroService {
	
	PersonaDao logindao = DaoFactory.getPersonaDao();
	
	public void registro(Persona persona) throws PersistenceException {
		logindao.insert(persona);
	}

}
