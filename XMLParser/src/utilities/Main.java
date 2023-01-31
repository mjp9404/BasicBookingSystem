package utilities;

import java.io.IOException;
/**
 * Contains the main method for running the program
 * The user will need to pass the file through command line
 * 
 * @author Mahdiyeh
 * 
 * @version 1.0
 *
 */
public class Main {
	/***
	 * Main method for running the program
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Parser parser=new Parser();
		parser.analize(args[0]);
		System.out.println("Finished analyzing file.");
	}
}
