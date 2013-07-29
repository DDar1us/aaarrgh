package aaarrgh.services;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.SeguimientoDao;

public class SeguimientoService {

	public void seguir (Sigo sigo, MeSiguen meSiguen) throws PersistenceException{
		
		SeguimientoDao seguir = DaoFactory.getSeguimientoDao();
		
		seguir.insert(sigo);
		seguir.insert(meSiguen);
		
	}
}
