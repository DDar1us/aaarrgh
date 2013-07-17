package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static ConnectionProvider instance;
	private static Connection connection = null;

	private String url = "jdbc:sqlite:C:/java/workspace/aaarrgh/aaarrgh.db";

	private ConnectionProvider() throws PersistenceException {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(this.url);
		} catch (Exception classNotFoundException) {
			throw new PersistenceException(classNotFoundException);
		}
	}

	public static ConnectionProvider getInstance() throws PersistenceException {
		if (instance == null) {
			instance = new ConnectionProvider();
		}
		return instance;
	}

	public Connection getConnection() throws PersistenceException {

		try {
			this.closeConnection();
			// siempre debe ser una nueva conexión, por cuestiones de SQLite

			connection = DriverManager.getConnection(this.url);
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			throw new PersistenceException(exception);
		}
		return connection;
	}

	public void closeConnection() throws PersistenceException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	public void rollback() throws PersistenceException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

}
