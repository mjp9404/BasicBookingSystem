package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * Manager class to display the management system
 * 
 * @author Jonathan Ryan Muriel
 * @author Minjong Park
 */

public class MovieManagementSystem {

	private DatabaseDriver dbDriver;

	private static Scanner input;

	/**
	 * Calls the methods
	 * 
	 * @throws SQLException throws SQL Exception
	 */
	public void MovieManagementSystem() throws SQLException {
		this.dbDriver = new MariaDBDriver();
		this.dbDriver.connect();
		this.input = new Scanner(System.in);

		int option = 0;

		while (option != 5) {
			option = displayMenu(input);
			input.nextLine();
			switch (option) {
			case 1: {
				addMovie();
				break;
			}
			case 2: {
				printMoviesInYear();
				break;
			}
			case 3: {
				printRandomMovies();
				break;
			}
			case 4: {
				deleteMovie();
				break;
			}
			case 5: {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			default: {
				System.out.println("Invalid input.");
				break;
			}
			}
		}
	}

	/**
	 * displays the options to call the methods
	 * 
	 * @param input the Scanner input
	 * @return selection to select the menu list
	 * @throws SQLException throws SQL Exception
	 */
	public static int displayMenu(Scanner input) throws SQLException {

		int select = 0;

		System.out.println("MJ's Movie Manager");
		System.out.println("1\tAdd New Movie");
		System.out.println("2\tPrint movies released in year");
		System.out.println("3\tPrint random list of movies");
		System.out.println("4\tDelete a movie");
		System.out.println("5\tExit");
		System.out.println("Enter option: ");
		try {
			select = input.nextInt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return select;

	}

	/**
	 * the methods to add new movie information
	 * 
	 * @throws SQLException throws SQL Exception
	 */

	public void addMovie() throws SQLException {

		String title;
		System.out.println("Enter movie title");
		title = input.next();
		int duration;
		System.out.println("Enter movie duration");
		duration = input.nextInt();
		int year;
		System.out.println("Enter movie year");
		year = input.nextInt();

		String queryString = "insert into movies (title, duration, year) values ('" + title + "', " + duration + ", "
				+ year + " )";

		int rows = dbDriver.update(queryString);
		System.out.println("Added movie to database");
	}

	/**
	 * the methods to print movie information by year
	 * 
	 * @throws SQLException throws SQL Exception
	 */

	public void printMoviesInYear() throws SQLException {

		System.out.println("Enter movie year");
		int year = input.nextInt();

		String queryString = "Select * from movies where year = " + year;

		ResultSet rs = dbDriver.get(queryString);

		System.out.printf("%15s %5s %10s\n", "Duration", "Year", "Title");
		while (rs.next()) {
			System.out.printf("%10d %10d %10s\n", rs.getInt("duration"), rs.getInt("year"), rs.getString("title"));
		}

		rs.close();
	}

	/**
	 * the method to print movie information by random number
	 * 
	 * @throws SQLException throws SQL Exception
	 */
	public void printRandomMovies() throws SQLException {

		System.out.println("Enter number of movies");
		int randomNumber = input.nextInt();
		int i;

		String queryString = "Select * FROM movies ORDER BY RAND() ";
		ResultSet rs = dbDriver.get(queryString);
		System.out.printf("%15s\n", "Movie List");
		System.out.printf("%15s %5s %10s\n", "Duration", "Year", "Title");

		for (i = 0; i < randomNumber; i++) {
			if (rs.next()) {
				System.out.printf("%10d %10d %10s\n", rs.getInt("duration"), rs.getInt("year"), rs.getString("title"));
			}
		}
		rs.close();
	}

	/**
	 * the method to delete movie information by id number
	 * 
	 * @throws SQLException throws SQL Exception
	 */
	public void deleteMovie() throws SQLException {
		System.out.println("Enter movie ID");
		int id = input.nextInt();
		String queryString = "DELETE FROM movies " + "WHERE id = " + id + "";
		int rows = dbDriver.update(queryString);

		System.out.println(rows + " Movie Deleted");
	}
}
