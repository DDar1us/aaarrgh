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

public class MarinerosService {
	
public List<TweetUser> finAllUser(String idusuario) throws PersistenceException{
		
		TweetDao tweetUser = DaoFactory.getTweetDao();
		SeguimientoDao seguimientodao = DaoFactory.getSeguimientoDao();
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		List<Tweet> tweet = new LinkedList<Tweet>();
		
		PersonaDao dao = DaoFactory.getPersonaDao();
		
		tweet = tweetUser.findAllUser();
		
		List<Sigo> sigoList = seguimientodao.findAllUserSigo(Integer.parseInt(idusuario));
		
		for(Iterator<Tweet> iterador = tweet.listIterator() ; iterador.hasNext();){
			
			TweetUser tweetUserModel = new TweetUser();
			
			Tweet tweetIter = (Tweet) iterador.next();
			
			if(tweetIter.getIdusuario() != Integer.parseInt(idusuario)){
			
			Persona persona = dao.findById(tweetIter.getIdusuario());
			
			for(Iterator<Sigo> iteradorSigo = sigoList.listIterator() ; iteradorSigo.hasNext();){
				
				Sigo sigo = (Sigo) iteradorSigo.next();
				
				if(sigo.getIdsigo() == tweetIter.getIdusuario()){
					
					if(sigo == null || sigo.getEstado() == 0){
						tweetUserModel.setMensaje("Seguir");
					}else{
						
						if(sigo.getEstado() == 1 && sigo.getIdsigo() == tweetIter.getIdusuario())
							tweetUserModel.setMensaje("Dejar de Seguir");
						
						else{
							tweetUserModel.setMensaje("Seguir");
						}
					}
					
					String usuario = persona.getNombre();
					
					tweetUserModel.setUsuario(usuario);
					tweetUserModel.setTweet(tweetIter);
					
					lisTweetUser.add(tweetUserModel);
				}
				
			}
			
			}
			
		}
		
		return lisTweetUser;
		
	}

}
