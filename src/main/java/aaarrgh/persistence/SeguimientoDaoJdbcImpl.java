package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Seguimiento;
import aaarrgh.model.TweetUser;

public class SeguimientoDaoJdbcImpl implements SeguimientoDao{
	
	private static SeguimientoDao instance = new SeguimientoDaoJdbcImpl();
	public static SeguimientoDao getInstance() {
		return instance;
	}
	
	@Override
	public void insert(Seguimiento seguimiento) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into seguimiento (idSeguidor, idSeguido, estadoSeguidor, estadoSeguido) values (?,?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, seguimiento.getIdSeguidor());
			statement.setInt(2, seguimiento.getIdSeguido());
			statement.setString(3, seguimiento.getEstadoSeguidor());
			statement.setString(4, seguimiento.getEstadoSeguido());

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
	public void delete(Seguimiento seguimiento) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from seguimiento where idSeguidor = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, seguimiento.getIdSeguidor());
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

	public void update (Seguimiento seguimiento) throws PersistenceException {
			Transaction tx = TransactionJdbcImpl.getInstance();
			Connection conn = tx.getConnection();

			try {
				tx.begin();
				String query = "update seguimiento set estadoSeguidor =? where idSeguidor =? and idSeguido=?";
				PreparedStatement statement = TransactionJdbcImpl.getInstance()
						.getConnection().prepareStatement(query);
				statement.setString(1, seguimiento.getEstadoSeguidor());
				statement.setInt(2, seguimiento.getIdSeguidor());
				statement.setInt(3, seguimiento.getIdSeguido());

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
		retorno.setEstado(resultSet.getString("estadoSeguidor"));
		retorno.setIdusuario(resultSet.getInt("idSeguido"));
		return retorno;
	}
	
	private TweetUser convertOneMarineros(ResultSet resultSet) throws SQLException {
		
		TweetUser retorno = new TweetUser();
		
		retorno.setUsuario(resultSet.getString("nombre"));
		retorno.setTweet(resultSet.getString("tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tiempo"));
		retorno.setIdusuario(resultSet.getInt("idusuario"));
		
		if(resultSet.getString("estadoSeguidor") == null){
			retorno.setEstado("seguir");
		}else{
			if(resultSet.getString("estadoSeguidor").equals("dejar de seguir")){
				retorno.setEstado("siguiendo..");
			}else{
				retorno.setEstado("seguir");
			}
		}
		
		return retorno;
	}

	private TweetUser convertOneMeSiguen(ResultSet resultSet) throws SQLException {
		TweetUser retorno = new TweetUser();

		retorno.setUsuario(resultSet.getString("nombre"));
		retorno.setTweet(resultSet.getString("tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tiempo"));
		retorno.setEstado(resultSet.getString("estadoSeguido"));
		retorno.setIdusuario(resultSet.getInt("idSeguidor"));
		return retorno;
	}
	
	private TweetUser convertOne(ResultSet resultSet) throws SQLException {
		TweetUser retorno = new TweetUser();

		retorno.setUsuario(resultSet.getString("persona.nombre"));
		retorno.setTweet(resultSet.getString("tweet.tweet"));
		retorno.setTiempo(resultSet.getTimestamp("tweet.tiempo"));
		retorno.setIdusuario(resultSet.getInt("tweet.idusuario"));
		retorno.setEstado(resultSet.getString("seguimiento.estadoSeguido"));
		return retorno;
	}
	

	@Override
	public int contarSigo(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from seguimiento where idSeguidor = ? and estadoSeguidor != 'volver a seguir'";
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
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, seguimiento.estadoSeguidor, seguimiento.idSeguido " +
					"from tweet inner join persona on persona.id = tweet.idusuario " +
					"inner join seguimiento on seguimiento.idSeguido = tweet.idusuario " +
					"where seguimiento.idSeguidor=?" +
					"group by persona.nombre " +
					"order by tweet.tiempo desc";
			
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
	
//	@Override
//	public void insert(MeSiguen meSiguen) throws PersistenceException {
//
//		Transaction tx = TransactionJdbcImpl.getInstance();
//		Connection conn = tx.getConnection();
//
//		try {
//			tx.begin();
//			String query = "insert into meSiguen (idusuario, idmeSiguen, estado) values (?,?,?)";
//			PreparedStatement statement = TransactionJdbcImpl.getInstance()
//					.getConnection().prepareStatement(query);
//			statement.setInt(1, meSiguen.getIdusuario());
//			statement.setInt(2, meSiguen.getIdMeSiguen());
//			statement.setString(3, meSiguen.getEstado());
//
//			statement.executeUpdate();
//
//			tx.commit();
//
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException sqlException) {
//				throw new PersistenceException(sqlException);
//			}
//		}
//	}
//
//	@Override
//	public void delete (MeSiguen meSiguen) throws PersistenceException {
//		Transaction tx = TransactionJdbcImpl.getInstance();
//		Connection conn = tx.getConnection();
//
//		try {
//			tx.begin();
//
//			String query = "delete from meSiguen where idusuario = ? and idmeSiguen= ?";
//			PreparedStatement statement = conn.prepareStatement(query);
//			statement.setInt(1, meSiguen.getIdusuario());
//			statement.setInt(2, meSiguen.getIdMeSiguen());
//			statement.executeUpdate();
//
//			tx.commit();
//
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException sqlException) {
//				throw new PersistenceException(sqlException);
//			}
//		}
//	}

//	public void updateSeguido (Seguimiento seguimiento) throws PersistenceException {
//		Transaction tx = TransactionJdbcImpl.getInstance();
//		Connection conn = tx.getConnection();
//
//		try {
//			tx.begin();
//			String query = "update seguimiento set estado =? where idSeguidor =? and idSeguido=?";
//			PreparedStatement statement = TransactionJdbcImpl.getInstance()
//					.getConnection().prepareStatement(query);
//			statement.setString(1, seguimiento.getEstadoSeguidor());
//			statement.setInt(2, seguimiento.getIdSeguidor());
//			statement.setInt(3, seguimiento.getIdSeguidor());
//
//			statement.executeUpdate();
//
//			tx.commit();
//
//		} catch (SQLException sqlException) {
//			throw new PersistenceException(sqlException);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException sqlException) {
//				throw new PersistenceException(sqlException);
//			}
//		}
//	}

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
			String query = "select * from seguimiento where idSeguido = ? and estadoSeguidor != 'volver a seguir'";
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
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, seguimiento.estadoSeguido, seguimiento.idSeguidor " +
							"from tweet inner join persona on persona.id = tweet.idusuario " +
							"inner join seguimiento on seguimiento.idSeguidor = tweet.idusuario " +
							"where seguimiento.idSeguido=? " +
							"group by persona.nombre " +
							"order by tweet.tiempo desc";
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
			String query = "select persona.nombre, tweet.tweet, tweet.tiempo, seguimiento.estadoSeguido, tweet.idusuario " +
							"from tweet left outer join persona on persona.id = tweet.idusuario " +
							"left outer join seguimiento on seguimiento.idSeguido = tweet.idusuario " +
							"where tweet.idusuario=? " +
							"union " +
							"select persona.nombre, tweet.tweet, tweet.tiempo, seguimiento.estadoSeguido, tweet.idusuario " +
							"from tweet inner join persona on persona.id = tweet.idusuario " +
							"inner join seguimiento on seguimiento.idSeguido = tweet.idusuario " +
							"where seguimiento.idSeguidor=? and seguimiento.estadoSeguidor != 'volver a seguir'  " +
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
		
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			 		String query = "select persona.nombre, tweet.tweet, tweet.tiempo, tweet.idusuario, seguimiento.estadoSeguidor " +
					 				"from tweet inner join persona on persona.id = tweet.idusuario " +
					 				"left join seguimiento on seguimiento.idSeguido = tweet.idusuario " +
					 				"where tweet.idusuario != ? " +
					 				"group by persona.nombre " +
					 				"order by tweet.tiempo";
			 		
			 		PreparedStatement statement;
			 		ResultSet resultSet;
			 		
					statement = c.prepareStatement(query);
					statement.setInt(1, idusuario);
					resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						lista.add(convertOneMarineros(resultSet));
					}
				
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

}
