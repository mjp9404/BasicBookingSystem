package sait.mms.drivers;

import sait.mms.contracts.*;
import java.sql.*;

/**
 * Driver for connecting to and accessing a MySQL or MariaDB database.
 * 
 * @author Jonathan Ryan Muriel
 * @author Minjong Park
 *
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	private Connection connection = null;

	/**
	 * Initializes the newly created JdbcDriver.
	 */
	public MariaDBDriver() {
	}


	@Override
	public void connect() throws SQLException {
		String dsn = this.getDsn();
		connection = DriverManager.getConnection(dsn);
	}

	/**
	 * Gets the data source name to connect to the database.
	 * 
	 * @return DSN
	 */
	private String getDsn() {
	
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE,
				USERNAME, PASSWORD);

		return dataSourceName;
	}


	/**
	 * Gets the results through statement to the database.
	 * @return results from statement(query)
	 */
	@Override
	public ResultSet get(String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);

		return results;
	}


	/**
	 * Updates Query
	 * @return rows updated
	 */
	@Override
	public int update(String query) throws SQLException {
		Statement statement = connection.createStatement();
		int rows = statement.executeUpdate(query);

		return rows;
	}

	/**
	 * Disconnects the database
	 */
	
	@Override
	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed())
			connection.close();
	}
}
