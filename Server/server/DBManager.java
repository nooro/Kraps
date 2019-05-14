package server;
import java.sql.*;

import exception.MissingDBConnectorException;
import exception.NoDBConnectionException;

public class DBManager {
	
	private Connection connection = null;
	
	public DBManager() throws MissingDBConnectorException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			throw new MissingDBConnectorException();
		}
	}
	
	public void openConnection(String dbURL, String user, String password) throws NoDBConnectionException {
		try {
			this.connection = DriverManager.getConnection(dbURL, user, password);
		}
		catch(SQLException e) {
			throw new NoDBConnectionException();
		}
	}
	
	public void closeConnection() throws NoDBConnectionException { 
		try {
			this.connection.close();
		} 
		catch (SQLException e) {
			throw new NoDBConnectionException();
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
