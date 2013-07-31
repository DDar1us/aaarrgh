package aaarrgh.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Persona;
import aaarrgh.model.Sigo;
import aaarrgh.model.Tweet;
import aaarrgh.model.TweetUser;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;
import aaarrgh.persistence.SeguimientoDao;
import aaarrgh.persistence.TweetDao;

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

	public List<TweetUser> finAllUserId(String idusuario) throws PersistenceException{
		
		TweetDao tweetUser = DaoFactory.getTweetDao();
		
		PersonaDao dao = DaoFactory.getPersonaDao();
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		List<Tweet> lisTweet = new LinkedList<Tweet>();
		lisTweet = tweetUser.findAllUserId(Integer.parseInt(idusuario));
		
		for(Iterator<Tweet> iteradorTweet = lisTweet.listIterator() ; iteradorTweet.hasNext();){
			
			TweetUser tweetUserModel = new TweetUser();
			
			Tweet tweetIter = (Tweet) iteradorTweet.next();
			
			Persona persona = dao.findById(tweetIter.getIdusuario());
			
			String usuario = persona.getNombre();
			
			tweetUserModel.setUsuario(usuario);
			tweetUserModel.setTweet(tweetIter);
			
			lisTweetUser.add(tweetUserModel);
		}
		
		List<Sigo> sigoList = new LinkedList<Sigo>();
		sigoList = seguimientodao.findAllUserSigo(Integer.parseInt(idusuario));
		
		for(Iterator<Sigo> iterador = sigoList.listIterator() ; iterador.hasNext();){
			
			Sigo sigoIter = (Sigo) iterador.next();
			
			List<Tweet> lisTweetSigo = new LinkedList<Tweet>();
			lisTweetSigo = tweetUser.findAllUserId(sigoIter.getIdsigo());
			
			if(sigoIter.getEstado() != 2){
				
				for(Iterator<Tweet> iteradorTweetSigo = lisTweetSigo.listIterator() ; iteradorTweetSigo.hasNext();){
					
					TweetUser tweetUserModelSigo = new TweetUser();
					
					Tweet tweetIterSigo = (Tweet) iteradorTweetSigo.next();
					
					Persona persona = dao.findById(tweetIterSigo.getIdusuario());
					
					String usuario = persona.getNombre();
					
					tweetUserModelSigo.setUsuario(usuario);
					tweetUserModelSigo.setTweet(tweetIterSigo);
					
					lisTweetUser.add(tweetUserModelSigo);
				}
				
			}
			
		}
		return lisTweetUser;
	}

	public List<TweetUser> finAllUserSigo(String idusuario) throws PersistenceException{
			
			TweetDao tweetUser = DaoFactory.getTweetDao();
			
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
					
					if(sigoIter.getEstado() != 2){
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
					
				
			}
			return lisTweetUser;
	}
	
	public List<TweetUser> finAllUserMeSiguen(String idusuario) throws PersistenceException{
		
		TweetDao tweetUser = DaoFactory.getTweetDao();
		
		List<TweetUser> lisTweetUser = new LinkedList<TweetUser>();
		
		Tweet tweet;
		
		List<MeSiguen> sigoList = new LinkedList<MeSiguen>();
		
		PersonaDao dao = DaoFactory.getPersonaDao();
		
		sigoList = seguimientodao.findAllUserMeSiguen(Integer.parseInt(idusuario));
		
		for(Iterator<MeSiguen> iterador = sigoList.listIterator() ; iterador.hasNext();){
			
			TweetUser tweetUserModel = new TweetUser();
			
			MeSiguen meSiguenIter = (MeSiguen) iterador.next();
			
			tweet = tweetUser.findByIdUser(meSiguenIter.getIdMeSiguen());
			
				Persona persona = dao.findById(tweet.getIdusuario());
				
				if(meSiguenIter.getEstado() == 1){
					tweetUserModel.setMensaje("Seguir");
				}else{
					
					if(meSiguenIter.getEstado() == 0 && meSiguenIter.getIdMeSiguen() == tweet.getIdusuario())
						tweetUserModel.setMensaje("siguiendo..");
					
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
			
			tweetUserModel.setMensaje("Seguir");
			
			Tweet tweetIter = (Tweet) iterador.next();
			
			if(tweetIter.getIdusuario() != Integer.parseInt(idusuario)){
			
			Persona persona = dao.findById(tweetIter.getIdusuario());
			
			if(sigoList != null){
				
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
					}
			
				}
				
			}
			
			String usuario = persona.getNombre();
			
			tweetUserModel.setUsuario(usuario);
			tweetUserModel.setTweet(tweetIter);
			
			lisTweetUser.add(tweetUserModel);
			
			}
			
		}
		
		return lisTweetUser;
	}
	
}
