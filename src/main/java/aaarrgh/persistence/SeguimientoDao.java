package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;

public interface SeguimientoDao {
	
	public void insert(Sigo sigo) throws PersistenceException;
    
    public void delete(Sigo sigo) throws PersistenceException;
    
    public void update(Sigo sigo) throws PersistenceException;
    
    public Sigo findByIdSigo(Integer idusuario) throws PersistenceException;
    
    public List<Sigo> findAllSigo() throws PersistenceException;
	
    public List<Sigo> findAllUserSigo(Integer idusuario) throws PersistenceException;
    
    public int contarSigo(Integer contarSigo) throws PersistenceException;
    
    
    public void insert(MeSiguen meSigue) throws PersistenceException;
    
    public void delete(MeSiguen meSigue) throws PersistenceException;
    
    public void update(MeSiguen meSigue) throws PersistenceException;
    
    public MeSiguen findByIdMeSiguen(Integer idusuario) throws PersistenceException;
    
    public List<MeSiguen> findAllMeSiguen() throws PersistenceException;
	
    public List<MeSiguen> findAllUserMeSiguen() throws PersistenceException;
    
    public int contarMeSiguen(Integer contarMeSiguen) throws PersistenceException;

}
