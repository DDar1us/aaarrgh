package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.MeSiguen;
import aaarrgh.model.Sigo;

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
			statement.setInt(3, sigo.getEstado());

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
				statement.setInt(1, sigo.getEstado());
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

	public List<Sigo> findAllSigo() throws PersistenceException {
		List<Sigo> lista = new LinkedList<Sigo>();
		try {
			String query = "select * from sigo";
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
	public Sigo findByIdSigo(Integer idusuario) throws PersistenceException {
		if (idusuario == null) {
			throw new IllegalArgumentException(
					"El id no debe ser nulo");
		}
		Sigo sigo = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from sigo where idusuario = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				sigo = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return sigo;
	}

	private Sigo convertOne(ResultSet resultSet) throws SQLException {
		Sigo retorno = new Sigo();

		retorno.setIdusuario(resultSet.getInt("idusuario"));
		retorno.setIdsigo(resultSet.getInt("idsigo"));
		retorno.setEstado(resultSet.getInt("estado"));

		return retorno;
	}
	

	@Override
	public int contarSigo(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from sigo where idusuario = ? and estado = 1";
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
	public List<Sigo> findAllUserSigo(Integer idusuario) throws PersistenceException {
		List<Sigo> lista = new LinkedList<Sigo>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from sigo where idusuario = ?";
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
			statement.setInt(3, meSiguen.getEstado());

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
			statement.setInt(1, meSiguen.getEstado());
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

	public List<MeSiguen> findAllMeSiguen() throws PersistenceException {
		List<MeSiguen> lista = new LinkedList<MeSiguen>();
		try {
			String query = "select * from meSiguen";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
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
	public MeSiguen findByIdMeSiguen(Integer idusuario) throws PersistenceException {
		if (idusuario == null) {
			throw new IllegalArgumentException(
					"El id no debe ser nulo");
		}
		MeSiguen meSiguen = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from MeSiguen where idusuario = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idusuario);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				meSiguen = convertOneMeSiguen(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return meSiguen;
	}

	
	private MeSiguen convertOneMeSiguen(ResultSet resultSet) throws SQLException {
		MeSiguen retorno = new MeSiguen();

		retorno.setIdusuario(resultSet.getInt("idusuario"));
		retorno.setIdMeSiguen(resultSet.getInt("idMeSiguen"));
		retorno.setEstado(resultSet.getInt("estado"));

		return retorno;
	}
	

	@Override
	public int contarMeSiguen(Integer idusuario) throws PersistenceException {
		int cont = 0;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from MeSiguen where idusuario = ? and estado = 1";
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
	public List<MeSiguen> findAllUserMeSiguen(Integer idusuario) throws PersistenceException {
		List<MeSiguen> lista = new LinkedList<MeSiguen>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from meSiguen where idusuario = ?";
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

}
