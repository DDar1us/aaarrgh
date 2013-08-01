package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;
import aaarrgh.model.TweetUser;

public class SeguimientoDaoJdbcImpl implements SeguimientoDao{
	
	private static SeguimientoDao instance = new SeguimientoDaoJdbcImpl();
	public static SeguimientoDao getInstance() {
		return instance;
	}
	
	@Override
	public void insert(Sigo sigo) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into sigo (idusuario, idsigo, estado) values (?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, sigo.getIdusuario());
			statement.setInt(2, sigo.getIdsigo());
			statement.setString(3, sigo.getEstado());

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void delete(Sigo sigo) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from sigo where idusuario = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, sigo.getIdusuario());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void update (Sigo sigo) throws PersistenceException {
			Transaction tx = TransactionJdbcImpl.getInstance();
			Connection conn = tx.getConnection();

			try {
				tx.begin();
				String query = "update sigo set estado =? where idusuario =? and idsigo=?";
				PreparedStatement statement = TransactionJdbcImpl.getInstance()
						.getConnection().prepareStatement(query);
				statement.setString(1, sigo.getEstado());
				statement.setInt(2, sigo.getIdusuario());
				statement.setInt(3, sigo.getIdsigo());

				statement.executeUpdate();

				tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

//	public List<Sigo> findAllSigo() throws PersistenceException {
//		List<Sigo> lista = new LinkedList<Sigo>();
//		try {
//			String query = "select * from sigo";
//			PreparedStatement statement = ConnectionProvider.getInstance()
//					.getConnection().prepareStatement(query);
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				lista.add(convertOneSigo(resultSet));
//			}
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		}
//		return lista;
//	}
//
//	@Override
//	public Sigo findByIdSigo(Integer idusuario) throws PersistenceException {
//		if (idusuario == null) {
//			throw new IllegalArgumentException(
//					"El id no debe ser nulo");
//		}
//		Sigo sigo = null;
//		try {
//			Connection c = ConnectionProvider.getInstance().getConnection();
//			String query = "select * from sigo where idusuario = ?";
//			PreparedStatement statement = c.prepareStatement(query);
//			statement.setInt(1, idusuario);
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				sigo = convertOneSigo(resultSet);
//			}
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		}
//		return sigo;
//	}

	private TweetUser convertOneSigo(ResultSet resultSet) throws SQLException {
		TweetUser retorno = new TweetUser();

		retorno.setUsuario(resultSet.getString("nombre"));
		retorno.setTweet(resultSet.getString("tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tiempo"));
		retorno.setEstado(resultSet.getString("estado"));
		retorno.setIdusuario(resultSet.getInt("idsigo"));
		return retorno;
	}
	
	private TweetUser convertOneMarinerosTweet(ResultSet resultSet, List<Sigo> lista_2) throws SQLException {
		
		TweetUser retorno = new TweetUser();
		
		for(Iterator<Sigo> iterador = lista_2.listIterator() ; iterador.hasNext();){
			
			Sigo sigo = (Sigo) iterador.next();
			
			if(resultSet.getInt("idusuario") != sigo.getIdsigo()){
				retorno.setUsuario(resultSet.getString("nombre"));
				retorno.setTweet(resultSet.getString("tweet"));
				retorno.setTiempo(resultSet.getTimestamp("tiempo"));
				retorno.setIdusuario(sigo.getIdsigo());
				
				if(sigo.getEstado() == null){
					retorno.setEstado("seguir");
				}else{
					if(sigo.getEstado().equals("dejar de seguir")){
						retorno.setEstado("seguiendo");
					}else{
						retorno.setEstado("seguir");
					}
				}
				
			}
		}
		
		return retorno;
	}

	private Sigo convertOneMarinerosSigo(ResultSet resultSet) throws SQLException {
		Sigo retorno = new Sigo();

		retorno.setIdusuario(resultSet.getInt("idusuario"));
		retorno.setIdsigo(resultSet.getInt("idsigo"));
		retorno.setEstado(resultSet.getString("estado"));
		
		return retorno;
	}
	
	
	private TweetUser convertOneMeSiguen(ResultSet resultSet) throws SQLException {
		TweetUser retorno = new TweetUser();

		retorno.setUsuario(resultSet.getString("nombre"));
		retorno.setTweet(resultSet.getString("tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tiempo"));
		retorno.setEstado(resultSet.getString("estado"));
		retorno.setIdusuario(resultSet.getInt("idmeSiguen"));
		return retorno;
	}
	
	private TweetUser convertOne(ResultSet resultSet) throws SQLException {
		TweetUser retorno = new TweetUser();

		retorno.setUsuario(resultSet.getString("persona.nombre"));
		retorno.setTweet(resultSet.getString("tweet.tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tweet.tiempo"));
		retorno.setIdusuario(resultSet.getInt("sigo.idsigo"));
		return retorno;
	}
	

	@Override
	public int contarSigo(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from sigo where idusuario = ? and estado = 'dejar de seguir'";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cont++;
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return cont;
	}
	
	@Override
	public List<TweetUser> buscarTodosLosTweetsaQuienesSigo(Integer idusuario) throws PersistenceException {
		List<TweetUser> lista = new LinkedList<TweetUser>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, sigo.estado, sigo.idsigo " +
							"from tweet inner join persona on persona.id = tweet.idusuario " +
							"inner join sigo on sigo.idsigo = tweet.idusuario " +
							"where sigo.idusuario = ? " +
							"group by persona.nombre " +
							"order by tweet.tiempo";
			
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOneSigo(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	
	//consultas meSiguen
	@Override
	public void insert(MeSiguen meSiguen) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into meSiguen (idusuario, idmeSiguen, estado) values (?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, meSiguen.getIdusuario());
			statement.setInt(2, meSiguen.getIdMeSiguen());
			statement.setString(3, meSiguen.getEstado());

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void delete (MeSiguen meSiguen) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from meSiguen where idusuario = ? and idmeSiguen= ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, meSiguen.getIdusuario());
			statement.setInt(2, meSiguen.getIdMeSiguen());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void update (MeSiguen meSiguen) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "update meSiguen set estado =? where idusuario =? and idmeSiguen=?";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, meSiguen.getEstado());
			statement.setInt(2, meSiguen.getIdusuario());
			statement.setInt(3, meSiguen.getIdMeSiguen());

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

//	public List<MeSiguen> findAllMeSiguen() throws PersistenceException {
//		List<MeSiguen> lista = new LinkedList<MeSiguen>();
//		try {
//			String query = "select * from meSiguen";
//			PreparedStatement statement = ConnectionProvider.getInstance()
//					.getConnection().prepareStatement(query);
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				lista.add(convertOneMeSiguen(resultSet));
//			}
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		}
//		return lista;
//	}
//
//	@Override
//	public MeSiguen findByIdMeSiguen(Integer idusuario) throws PersistenceException {
//		if (idusuario == null) {
//			throw new IllegalArgumentException(
//					"El id no debe ser nulo");
//		}
//		MeSiguen meSiguen = null;
//		try {
//			Connection c = ConnectionProvider.getInstance().getConnection();
//			String query = "select * from MeSiguen where idusuario = ?";
//			PreparedStatement statement = c.prepareStatement(query);
//			statement.setInt(1, idusuario);
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				meSiguen = convertOneMeSiguen(resultSet);
//			}
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		}
//		return meSiguen;
//	}

	@Override
	public int contarMeSiguen(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from MeSiguen where idusuario = ? and (estado = 'seguir' or estado = 'siguiendo..')";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cont++;
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return cont;
	}
	
	@Override
	public List<TweetUser> buscarTodosLosTweetsDeMisSeguidores(Integer idusuario) throws PersistenceException {
		List<TweetUser> lista = new LinkedList<TweetUser>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, meSiguen.estado, meSiguen.idmeSiguen " +
					"from tweet inner join persona on persona.id = tweet.idusuario " +
					"inner join meSiguen on meSiguen.idmeSiguen = tweet.idusuario " +
					"where meSiguen.idusuario = ? and (meSiguen.estado = 'siguiendo..' or meSiguen.estado = 'seguir')" +
					"group by persona.nombre " +
					"order by tweet.tiempo";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOneMeSiguen(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	@Override
	public List<TweetUser> buscarTodosMisTweetsaQuienesSigo(Integer idusuario) throws PersistenceException {
		List<TweetUser> lista = new LinkedList<TweetUser>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, sigo.estado, sigo.idsigo " +
							"from tweet left outer join persona on persona.id = tweet.idusuario " +
							"left outer join sigo on sigo.idsigo = tweet.idusuario " +
							"where tweet.idusuario=? " +
							"union " +
							"select persona.nombre, tweet.tweet, tweet.tiempo, sigo.estado, sigo.idsigo " +
							"from tweet inner join persona on persona.id = tweet.idusuario " +
							"inner join sigo on sigo.idsigo = tweet.idusuario " +
							"where sigo.idusuario=? and sigo.estado='dejar de seguir'  " +
							"order by tweet.tiempo desc";
			
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			statement.setInt(2, idusuario);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	@Override
	public List<TweetUser> buscarTweetDelosUsuariosMenosElMio(Integer idusuario) throws PersistenceException {
		List<TweetUser> lista = new LinkedList<TweetUser>();
		List<Sigo> lista_2 = new LinkedList<Sigo>();
		
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			 		String query = "select persona.nombre, tweet.tweet, tweet.tiempo, tweet.idusuario " +
					 				"from tweet inner join persona on persona.id = tweet.idusuario " +
					 				"where tweet.idusuario != ? " +
					 				"group by persona.nombre";
			 		
			 		PreparedStatement statement;
			 		ResultSet resultSet;
			 		
					statement = c.prepareStatement(query);
					statement.setInt(1, idusuario);
					resultSet = statement.executeQuery();
					
					String query2 = "select sigo.idusuario, sigo.idsigo, sigo.estado from sigo " +
									"where sigo.idusuario = ?";
					
					PreparedStatement statement_2;
			 		ResultSet resultSet_2;
			 		
					statement_2 = c.prepareStatement(query2);
					statement_2.setInt(1, idusuario);
					resultSet_2 = statement_2.executeQuery();
			
					while (resultSet.next()) {
						lista_2.add(convertOneMarinerosSigo(resultSet_2));
					}
					
					while (resultSet.next()) {
						lista.add(convertOneMarinerosTweet(resultSet, lista_2));
					}
				
				
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

}
