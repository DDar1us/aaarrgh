package aaarrgh.services;

import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.SeguimientoDao;

public class SeguimientoService {
	
	SeguimientoDao seguimientodao = DaoFactory.getSeguimientoDao();

	public void sigo (Sigo sigo) throws PersistenceException{
		seguimientodao.insert(sigo);
	}
	
	public void meSiguen (MeSiguen meSiguen) throws PersistenceException{
		seguimientodao.insert(meSiguen);
	}
	
	public void actualizarMeSiguen (MeSiguen meSiguen) throws PersistenceException{
		seguimientodao.update(meSiguen);
	}
	
	public void actualizarSigo (Sigo sigo) throws PersistenceException{
		seguimientodao.update(sigo);
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
