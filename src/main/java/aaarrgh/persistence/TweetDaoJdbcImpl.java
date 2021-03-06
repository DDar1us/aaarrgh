package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Tweet;

public class TweetDaoJdbcImpl implements TweetDao{
	
	private static TweetDao instance = new TweetDaoJdbcImpl();
	public static TweetDao getInstance() {
		return instance;
	}
	
	@Override
	public void insert(Tweet tweet) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into tweet (tweet, idusuario, tiempo) values (?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, tweet.getTweet());
			statement.setInt(2, tweet.getIdusuario());
			statement.setTimestamp(3, tweet.getNow());

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
	public void delete(Tweet tweet) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from tweet where idtweet = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, tweet.getIdTweet());
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
	public void update(Tweet tweet) throws PersistenceException {
		try {
			String query = "update tweet set tweet = ?, idusuario = ?, where idtweet = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, tweet.getTweet());
			statement.setInt(2,tweet.getIdusuario());
			statement.setInt(3, tweet.getIdTweet());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}

	public List<Tweet> findAll() throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select * from tweet";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
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
	public Tweet findById(Integer idtweet) throws PersistenceException {
		if (idtweet == null) {
			throw new IllegalArgumentException(
					"El id del mensaje no debe ser nulo");
		}
		Tweet tweet = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where idtweet = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idtweet);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				tweet = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return tweet;
	}
	
	@Override
	public Tweet findByIdUser(Integer idusuario) throws PersistenceException {
		if (idusuario == null) {
			throw new IllegalArgumentException(
					"El id del mensaje no debe ser nulo");
		}
		Tweet tweet = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where idusuario = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				tweet = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return tweet;
	}

	private Tweet convertOne(ResultSet resultSet) throws SQLException {
		Tweet retorno = new Tweet();

		retorno.setIdTweet(resultSet.getInt("idtweet"));
		retorno.setTweet(resultSet.getString("tweet"));
		retorno.setIdusuario(resultSet.getInt("idusuario"));
		retorno.setNow(resultSet.getTimestamp("tiempo"));

		return retorno;
	}
	

	@Override
	public int contarTweet(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where idusuario = ?";
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
	public List<Tweet> findAllUser() throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select idtweet, tweet, idusuario, tiempo from tweet GROUP BY idusuario";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
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
	public List<Tweet> findAllUserId(Integer idusuario) throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where idusuario = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

}
