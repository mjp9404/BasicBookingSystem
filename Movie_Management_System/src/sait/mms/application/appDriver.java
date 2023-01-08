package sait.mms.application;
import java.sql.SQLException;
import sait.mms.managers.MovieManagementSystem;
/**
* calls the Manager constructor
* @author Jonathan Ryan Muriel
* @author Minjong Park
*/

public class appDriver {

	public static void main(String[] args) throws SQLException {
		MovieManagementSystem sms = new MovieManagementSystem();
		sms.MovieManagementSystem();
	}


}
