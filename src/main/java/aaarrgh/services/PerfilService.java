package aaarrgh.services;

import aaarrgh.model.Persona;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;

public class PerfilService {
	
	PersonaDao logindao = DaoFactory.getPersonaDao();
	
	public Persona perfil(Integer idusuario) throws PersistenceException {
		return logindao.findById(idusuario);
	}

}
