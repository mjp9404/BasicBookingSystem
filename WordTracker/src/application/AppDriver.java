package application;
import manager.WordTracker;

/**
 * Class to run the program
 * 
 * @author Mahdiyeh Abbaspour
 * @version 1.1
 * Created: July 28, 2022
 * Updated: July 28, 2022
 *
 */
public class AppDriver {
	
	/**
	 * main method to run the program and create
	 * a WordTracker object
	 * 
	 * @param args arguments passed as <input.txt> -pf/-pl/-po [-f <output.txt>]
	 */
	public static void main(String[] args)  {
				
		WordTracker wordTracker = new WordTracker();
		wordTracker.run(args);
		
	}

}
