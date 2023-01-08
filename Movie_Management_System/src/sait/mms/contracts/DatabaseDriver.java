package sait.mms.contracts;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
* connecting the database
* @author Jonathan Ryan Muriel
* @author Minjong Park
*/
public interface DatabaseDriver {
	
	void connect() throws SQLException;
	
	ResultSet get(String query) throws SQLException;
	
	int update(String query) throws SQLException;
	
	void disconnect() throws SQLException;

}
