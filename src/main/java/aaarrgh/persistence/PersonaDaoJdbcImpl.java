package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Persona;

public class PersonaDaoJdbcImpl implements PersonaDao {

	private static PersonaDao instance = new PersonaDaoJdbcImpl();

	public static PersonaDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Persona persona) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into persona (nombre, apellido, edad, password) values (?, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setInt(3, persona.getEdad());
			statement.setString(4, persona.getPassword());

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
	public void delete(Persona persona) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from persona where id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, persona.getId());
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
	public void update(Persona persona) throws PersistenceException {
		try {
			String query = "update persona set nombre = ?, apellido = ?, edad = ? where id = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setInt(3, persona.getEdad());
			statement.setInt(4, persona.getId());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}

	public List<Persona> findAll() throws PersistenceException {
		List<Persona> lista = new LinkedList<Persona>();
		try {
			String query = "select * from persona";
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
	public Persona findById(Integer idPersona) throws PersistenceException {
		if (idPersona == null) {
			throw new IllegalArgumentException(
					"El id de persona no debe ser nulo");
		}
		Persona persona = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select id, nombre, apellido, edad, password from persona where id = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idPersona);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				persona = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return persona;
	}

	private Persona convertOne(ResultSet resultSet) throws SQLException {
		Persona retorno = new Persona();

		retorno.setId(resultSet.getInt("id"));
		retorno.setNombre(resultSet.getString("nombre"));
		retorno.setApellido(resultSet.getString("apellido"));
		retorno.setEdad(resultSet.getInt("edad"));
		retorno.setPassword(resultSet.getString("password"));
		

		return retorno;
	}
	
	public Persona authenticate(String username, String password) throws PersistenceException {
		
		if (username == null && password == null) {
			throw new IllegalArgumentException(
					"El nombre y el password no deben ser nulos");
		}
		
		Persona persona = null;
		
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from persona where nombre= ? and password= ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				
				persona = convertOne(resultSet);
				
				}
			c.close();
		}catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
		}
		return persona;
		
	}

}
