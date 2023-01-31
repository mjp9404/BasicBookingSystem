package utilities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Class description: separate class that represents location 
 * and word for the WordTracker
 * 
 * @author Mahdiyeh Abbaspour
 * @author Minjong Park
 * 
 * @version 1.1
 *
 */
public class Location implements Serializable {
	
	/**
	 * The class version UID to ensure the backward compatibility 
	 * with the repository should the class specification change 
	 * with future enhancements.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the line number that the word is in
	 */
	private ArrayList<Integer> lineNumbers = new ArrayList<>();
	
	/**
	 * The name of the file
	 */
	private String textFile = "";
	
	/**
	 * The number of times the word has been used
	 */
	private int counter=0;

	/**
	 * No-arg constructor to initialize the newly created Location
	 */
	public Location() {
		super();
	}
	
	/**
	 * 
	 * Class constructor to initialize the newly created Location 
	 * by user defined like lineNumber and textFile
	 * @param lineNumber the line numbers which the words were used
	 * @param textFile the file name and the path for the text file
	 */
	public Location(int lineNumber, String textFile) {
		super();
		this.lineNumbers.add(lineNumber);
		this.textFile=textFile;
		this.counter=1;
	}
	
	/**
	 * Gets the line numbers of the list
	 * @return the line numbers of the list
	 */
	public ArrayList<Integer> getLineNumbers() {
		return lineNumbers;
	}
	
	/**
	 * Returns line number added
	 * 
	 * @param lineNumber the line number added with incremental counter
	 */
	public void addLineNumber(int lineNumber) {
		this.counter++;
//		if (!this.lineNumbers.contains(lineNumber))   // if the word in a line more than one occures @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		    this.lineNumbers.add(lineNumber);
	}
	
	/**
	 * Gets the counter number
	 * 
	 * @return the number of the counter
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * Gets the text file 
	 * 
	 * @return the text file 
	 */
	public String getTextFile() {
		return textFile;
	}	

}
