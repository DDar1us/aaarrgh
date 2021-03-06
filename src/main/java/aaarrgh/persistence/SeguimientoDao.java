package aaarrgh.persistence;

import java.util.List;
import aaarrgh.model.Seguimiento;
import aaarrgh.model.TweetUser;

public interface SeguimientoDao {
	
	//metodos Sigo
	public void insert(Seguimiento seguimiento) throws PersistenceException;
    
    public void delete(Seguimiento seguimiento) throws PersistenceException;
    
    public void update(Seguimiento seguimiento) throws PersistenceException;
    
//    public Sigo findByIdSigo(Integer idusuario) throws PersistenceException;
//    
//    public List<Sigo> findAllSigo() throws PersistenceException;
	
    public List<TweetUser> buscarTodosLosTweetsaQuienesSigo(Integer idusuario) throws PersistenceException;
    
    public int contarSigo(Integer contarSigo) throws PersistenceException;
    
    //metodos meSiguen
    
//    public void insert(MeSiguen meSiguen) throws PersistenceException;
//    
//    public void delete(MeSiguen meSiguen) throws PersistenceException;
//    
//    public void update(MeSiguen meSiguen) throws PersistenceException;
//    
//    public MeSiguen findByIdMeSiguen(Integer idusuario) throws PersistenceException;
//    
//    public List<MeSiguen> findAllMeSiguen() throws PersistenceException;
	
    public List<TweetUser> buscarTodosLosTweetsDeMisSeguidores(Integer idusuario) throws PersistenceException;
    
    public int contarMeSiguen(Integer contarMeSiguen) throws PersistenceException;

	public List<TweetUser> buscarTodosMisTweetsaQuienesSigo(Integer idusuario) throws PersistenceException;

	public List<TweetUser> buscarTweetDelosUsuariosMenosElMio(Integer idusuario) throws PersistenceException;

}
