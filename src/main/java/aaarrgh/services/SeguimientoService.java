package aaarrgh.services;

import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Seguimiento;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.SeguimientoDao;

public class SeguimientoService {
	
	SeguimientoDao seguimientodao = DaoFactory.getSeguimientoDao();

	public void seguimiento (Seguimiento seguimiento) throws PersistenceException{
		seguimientodao.insert(seguimiento);
	}
	
	public void actualizarSigo (Seguimiento seguimiento) throws PersistenceException{
		seguimientodao.update(seguimiento);
	}
	
	public Integer contarSigo(Integer idusuario) throws PersistenceException {
		return seguimientodao.contarSigo(idusuario);
	}
	
	public Integer contarSeguidores(Integer idusuario) throws PersistenceException {
		return seguimientodao.contarMeSiguen(idusuario);
	}

	public List<TweetUser> buscarTodosLosTweetDeUnUsuarioYaQuienesSigo(String idusuario) throws PersistenceException{
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		lisTweetUser = seguimientodao.buscarTodosMisTweetsaQuienesSigo(Integer.parseInt(idusuario));
		
		return lisTweetUser;
	}

	public List<TweetUser> buscarTodosLosTweetsaQuienesSigo(String idusuario) throws PersistenceException{
			
			List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
			
			lisTweetUser = seguimientodao.buscarTodosLosTweetsaQuienesSigo(Integer.parseInt(idusuario));
			
			return lisTweetUser;
	}
	
	public List<TweetUser> buscarTodosLosTweetsDeMisSeguidores(String idusuario) throws PersistenceException{
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		lisTweetUser = seguimientodao.buscarTodosLosTweetsDeMisSeguidores(Integer.parseInt(idusuario));
		
		return lisTweetUser;
	}
	
	public List<TweetUser> buscarTweetDelosUsuariosMenosElMio(String idusuario) throws PersistenceException{
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		lisTweetUser = seguimientodao.buscarTweetDelosUsuariosMenosElMio(Integer.parseInt(idusuario));
		
		return lisTweetUser;
	}
	
}
