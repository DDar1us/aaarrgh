package aaarrgh.services;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Persona;
import aaarrgh.model.Sigo;
import aaarrgh.model.Tweet;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;
import aaarrgh.persistence.SeguimientoDao;
import aaarrgh.persistence.TweetDao;

public class LoginService {
	
	PersonaDao logindao = DaoFactory.getPersonaDao();
	SeguimientoDao seguimientodao = DaoFactory.getSeguimientoDao();
	Persona usuario = new Persona();
	
	public Persona authenticate(String username, String password) throws PersistenceException {
		return logindao.authenticate(username, password);
	}
	
	public Integer sigo(Integer idusuario) throws PersistenceException {
		return seguimientodao.contarSigo(idusuario);
	}
	
	public Integer meSiguen(Integer idusuario) throws PersistenceException {
		return seguimientodao.contarMeSiguen(idusuario);
	}
	
public List<TweetUser> finAllUser(String idusuario) throws PersistenceException{
		
		TweetDao tweetUser = DaoFactory.getTweetDao();
		SeguimientoDao seguimientodao = DaoFactory.getSeguimientoDao();
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		Tweet tweet;
		
		List<Sigo> sigoList = new LinkedList<Sigo>();
		
		PersonaDao dao = DaoFactory.getPersonaDao();
		
		sigoList = seguimientodao.findAllUserSigo(Integer.parseInt(idusuario));
		
		for(Iterator<Sigo> iterador = sigoList.listIterator() ; iterador.hasNext();){
			
			TweetUser tweetUserModel = new TweetUser();
			
			Sigo sigoIter = (Sigo) iterador.next();
			
			tweet = tweetUser.findByIdUser(sigoIter.getIdsigo());
			
				Persona persona = dao.findById(tweet.getIdusuario());
				
				if(sigoIter.getEstado() == 0){
					tweetUserModel.setMensaje("Seguir");
				}else{
					
					if(sigoIter.getEstado() == 1 && sigoIter.getIdsigo() == tweet.getIdusuario())
						tweetUserModel.setMensaje("Dejar de Seguir");
					
					else{
						tweetUserModel.setMensaje("Seguir");
					}
				}
				
				String usuario = persona.getNombre();
				
				tweetUserModel.setUsuario(usuario);
				tweetUserModel.setTweet(tweet);
				
				lisTweetUser.add(tweetUserModel);
			
		}
		return lisTweetUser;
}
	
	
}
