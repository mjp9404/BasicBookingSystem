package utilities;
import java.io.*;
import java.util.*;

/**
 * 
 * Class description: separate class that represents words 
 * and word for the WordTracker
 * 
 * @author Mahdiyeh Abbaspour
 * @author Minjong Park
 * 
 * @version 1.1
 *
 */
public class Word implements Comparable<Word>, Serializable {

	/**
	 * The class version UID to ensure the backward compatibility 
	 * with the repository should the class specification change 
	 * with future enhancements.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The word that is being saved
	 */
	private String word;
	
	/**
	 * the locations of the places the word has been repeated
	 */
	private ArrayList <Location> lineFiles = new ArrayList<>();
	
	/**
	 * The number of times the word has been used
	 */
	int counter=0;
	
	/**
	 * Class constructor to initialize the newly created Word
	 * by user defined like words and location
	 * 
	 * @param word the words  
	 * @param lineFile lineFile that calls Location
	 */
	public Word(String word, Location lineFile) {
		super();
		this.word = word;
		this.lineFiles.add(lineFile);
		this.counter=1;
	
	}
	
	/**
	 * Gets the word
	 * 
	 * @return the words 
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Returns the line numbers with the files in the Location list
	 * 
	 * @return lineFiles in the Location list
	 */
	public ArrayList<Location> getLineFiles() {
		return lineFiles;
	}
	
	/**
	 * Adds the line numbers with the files in the location
	 * 
	 * @param lineFile the lineFiles in the Location
	 */
	public void addLineFiles(Location lineFile) {
		if (!this.lineFiles.contains(lineFile)) 
			this.lineFiles.add(lineFile);
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
	 * Returns the counter number increased
	 * 
	 * @return the counter number increased
	 */
	public int addCounter() {
		return this.counter++;
	}

	/**
	 * Compares the words
	 * 
	 * @param oWord the object word to be compared
	 * @return returns 1 if the object greater oWord, 0 is if they match, 
	 * -1 if the object lower oWord 
	 */
	@Override
	public int compareTo(Word oWord) {
		
		return this.word.compareTo(oWord.getWord());
	}
}
