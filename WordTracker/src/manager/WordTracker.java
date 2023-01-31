package manager;

import java.io.*;
import java.util.*;

import exceptions.TreeException;
import utilities.*;
import utilities.Iterator;

/**
 * @author Mahdiyeh Abbaspour
 * @author Minjong Park
 * @version 1.1
 * Created: July 28, 2022
 * Updated: July 28, 2022
 *
 * Class Description:Specifications
 *  Cross-reference program (WordTracker.java), which constructs a binary search tree with
 *  all words included from a text file (supplied at the command line) and records the line numbers on
 * which these words were used. The line numbers should be stored with the file names, which in
 * turn are associated with the nodes of the tree.
 * 3. Using Java serialization techniques, store the tree in a binary file (repository.ser). Make sure you
 * insert the class version UID to ensure the backward compatibility with your repository should the
 * class specification change with future enhancements.
 * 4. Every time the program executes, it should check if the binary file (repository.ser) exists, and if
 * so, restores the words tree. The results of the scanning the next file are to be inserted in the
 * appropriate nodes of the tree. Therefore, repository.ser will contain all words occurred in all files
 * scanned with the meta information about those word locations.
 * 5. The user should be able to run the program via the command line as follows:
 * java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]
 * a) <input.txt> is the path and filename of the text file to be processed by the WordTracker program.
 * b) 3 mutually exclusive options at command line:
 * - -pf to print in alphabetic order all words along with the corresponding list of files in which
 * the words occur.
 * - -pl to print in alphabetic order all words along with the corresponding list of files and
 * numbers of the lines in which the word occur.
 * - -po to print in alphabetic order all words along with the corresponding list of files,
 * numbers of the lines in which the word occur and the frequency of occurrence of the
 * words.
 * c) Optional argument to redirect of the report in the previous step to the path and filename specified
 * in <output.txt.
 * d) You can assume that the user will provide these arguments in the order shown. * 
 */
public class WordTracker {
	/**
	 * The Path that contains the name of the text file
	 */
	String inputTextFileNameWithPath = ""; 
	
	/**
	 * the name of the text file to be output
	 */
	String outputTextFile = "";
	
	/**
	 * the selection for the report 
	 */
	String select = "";
	
	/**
	 * the name of the repository to be serialized
	 */
	String serialFile="repository.ser";

	/**
	 * 
	 * class constructor to initialize the newly created WordTracker
	 */
	public WordTracker() {
		super();
	}
	
	/**
	 * Runs WordTracker
	 * 
	 * @param args the args that contain the check the process of the command line and the methods
	 * of creating BSTree, restoring BSTree from Binary file, storing BSTree to Binary File, 
	 * and creating report for the output text file  
	 * 
	 * @return if true, returns the process of the command line and the methods
	 * of creating BSTree, restoring BSTree from Binary file, storing BSTree to Binary File, 
	 * and creating report for the output text file  
	 */
	public boolean run(String[] args){
		
		if (!processArgsOfCommandLine(args))	
			return false;
		
		BSTree<Word> wordBSTree = new BSTree<Word>();

		// restore the tree from binary file (repository.ser)
		wordBSTree=restoreBSTeeFromBinaryFile(wordBSTree,serialFile);
		
		try {
			wordBSTree=makeBstFromFile(wordBSTree, inputTextFileNameWithPath);
			
			// store the tree in a binary file (repository.ser)
			storeBSTeeToBinaryFile(wordBSTree, serialFile);
 
			doReport(wordBSTree, select, outputTextFile);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage()); 
		} catch (TreeException e) {
			System.out.println(e.getMessage()); 
		}
		
		return false;
	}
	
	/**
	 * The boolean method to print in alphabetic order all words along 
	 * with the corresponding list of files at the command line of the three options 
	 * that are pf, pl, po
	 * 
	 * @param args args that include the process for 3 mutually exclusive options 
	 * which are "-pf", "-pl", "-po"  at command line to print in alphabetic order all words
	 * @return returns the words corresponding the options to be printed in alphabetic order 
	 */
	private boolean processArgsOfCommandLine(String [] args){
		
		for (int i=0;i<args.length;i++){
			String arg=args[i];
			if (arg.equalsIgnoreCase("-pf")){
				select="pf";
			} else if (arg.equalsIgnoreCase("-pl")){
				select="pl";
			} else if (arg.equalsIgnoreCase("-po")){
				select="po";
			} else if (arg.equalsIgnoreCase("-f")){
				if (i+1<args.length) 
					outputTextFile=args[i+1];
				else 
					System.out.println("option -f specified but output file missed.");
			} else if (arg.equals(outputTextFile)){
				
			} else {
				inputTextFileNameWithPath=arg;
			}
		}
		
		System.out.println("WordTracker program run with these options received from the command line:");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Input file for processing: "+inputTextFileNameWithPath);
	    File file = new File(inputTextFileNameWithPath);
	    if (!file.exists()){
	      System.out.println("This file does not exist.");
	      return false;
	    }
	    
		if (outputTextFile.equals(""))
			System.out.println("Redirect the report to Console");
		else
			System.out.println("Redirect the report to output file name: "+outputTextFile);
		if (select.equals("pf"))
			System.out.println("-pf selected to print in alphabetic order all words along with the corresponding list of files in which	the words occur.");
		
		if (select.equals("pl"))
			System.out.println("-pl to print in alphabetic order all words along with the corresponding list of files and	numbers of the lines in which the word occur.");
		
		if (select.equals("po"))
			System.out.println("-po to print in alphabetic order all words along with the corresponding list of files,numbers of the lines in which the word occur and the frequency of occurrence of the words.");
		
	    System.out.println("logs:");
	    System.out.println("-----");
		return true;
	}

	/**
	 * The method to create BSTree as word tracker tree,
	 * to add line numbers with file name, by reading the text file
	 * 
	 * @param wordTrackerTree BSTree that contains word
	 * @param inputTextFileNameWithPath The Path with the name of text file
	 * @return returns BSTree as wordTrackerTree that include words
	 * @throws FileNotFoundException if the file already processed file or 
	 * doesn't exist error occurs
	 * @throws TreeException if tree is empty, error occurs
	 */
	private BSTree<Word> makeBstFromFile(BSTree<Word> wordTrackerTree, String inputTextFileNameWithPath) throws FileNotFoundException, TreeException {

			File file = new File(inputTextFileNameWithPath);
		    String inputTextFileName=file.getName();

			FileReader reader = new FileReader(file);
			Scanner scn = new Scanner(reader);
			int lineNo=0;
			boolean checkAlreadyFileProcessd=true;
			
			while (scn.hasNext()) {
				String line=scn.nextLine();
				lineNo++;
				// finding word in "line"
				while (line.length()>0){
					String word = "";
					int i = 0;
					for (i = 0; i < line.length(); i++) {
						String ch = line.substring(i, i + 1);
						if (ch.matches("[a-zA-Z']*"))
							break;
					}
					int j=i;
					for ( ; j < line.length(); j++) {
						String ch = line.substring(j, j + 1);
						if (!ch.matches("[a-zA-Z']*"))
							break;
						word += ch;
					}
					if (word.length()>0){
						word=word.toLowerCase();
						Location locationFile=new Location(lineNo, inputTextFileName); // 

						Word wor=new Word(word, locationFile);
						BSTreeNode bsTreeNode=null;
						if(!wordTrackerTree.isEmpty()) 
						   bsTreeNode= wordTrackerTree.search(wor);
						else
							checkAlreadyFileProcessd=false;
						
						if (bsTreeNode==null) {
							wordTrackerTree.add(wor);
							checkAlreadyFileProcessd=false;
						}else {
							Word w= (Word) bsTreeNode.getData();
							w.addCounter();
							ArrayList<Location> linefiles=w.getLineFiles();
							boolean added=false;
							for(Location l : linefiles){
								if (l.getTextFile().equalsIgnoreCase(inputTextFileName)){
									if (checkAlreadyFileProcessd) {
										throw new FileNotFoundException("The file "+ inputTextFileName +" has already been processed."); 
									}
									l.addLineNumber(lineNo);
									added=true;
								}
								
							}
							if (!added){
								checkAlreadyFileProcessd=false;
								w.addLineFiles(locationFile);
							}
						}
					}
						
				   line=line.substring(j);
				}
				
			}
			
			return wordTrackerTree;
	}
/**	      
 * The method to create report printing output text file
 * java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]
					a) <input.txt> is the path and filename of the text file to be processed by the WordTracker program.
					b) 3 mutually exclusive options at command line:
					- -pf to print in alphabetic order all words along with the corresponding list of files in which
					the words occur.
					- -pl to print in alphabetic order all words along with the corresponding list of files and
					numbers of the lines in which the word occur.
					- -po to print in alphabetic order all words along with the corresponding list of files,
					numbers of the lines in which the word occur and the frequency of occurrence of the
					words.
					c) Optional argument to redirect of the report in the previous step to the path and filename specified
					in <output.txt.
			
* @param wordTrackerTree  BSTree that contains word
* @param select selection corresponding list of files in alphabetic order
* @param outputTextFile text file to be output
*/			
	private void doReport(BSTree<Word> wordTrackerTree, String select, String outputTextFile) {

		PrintStream report=null;
		if (outputTextFile!=null && outputTextFile!=""){
		    try {
		    	report = new PrintStream(outputTextFile);
		    	System.setOut(report);
		      } catch (IOException e) {
		        System.out.println("Creation of output text file to redirect of the report failed. fileName="+outputTextFile);
		      }
			
		}
		
		Iterator<Word> it = wordTrackerTree.inorderIterator(); // preorderIterator(); //postorderIterator();
		while(it.hasNext()){
			Word w=it.next();
			ArrayList <Location> lineFiles=w.getLineFiles();
			
			if (select.equals("")) {
				System.out.println(w.getWord());
			} else if (select.equalsIgnoreCase("pf")){
				orderPf(w,lineFiles);
			} else if (select.equalsIgnoreCase("pl")){
				orderPl(w,lineFiles);
			} else if (select.equalsIgnoreCase("po")){
				orderPo(w,lineFiles);
			}
		}
	}
	
	/**
	 * The method that represents order "-pf" to print in alphabetic 
	 * order all words along with the corresponding list of files in 
	 * which the words occur.
	 * 
	 * @param w the words for the order "-pf"
	 * @param lineFiles line numbers with file in Location list
	 */
	private void orderPf(Word w, ArrayList <Location> lineFiles) {
		String fileNames="";
		for(int i=0;i<lineFiles.size();i++)
			fileNames+=','+lineFiles.get(i).getTextFile();
		System.out.println(w.getWord()+" Files= "+fileNames.substring(1));
	}

	/**
	 * The method that represents order "-pl" to print in alphabetic 
	 * order all words along with the corresponding list of files and 
	 * numbers of the lines in which the word occur.
	 * 
	 * @param w the words for the order "-pl"
	 * @param lineFiles line numbers with file in Location list
	 */
	private void orderPl(Word w, ArrayList <Location> lineFiles) {
		System.out.println(w.getWord());
		for(int i=0;i<lineFiles.size();i++){
			System.out.print(" File= "+lineFiles.get(i).getTextFile()+" lines= "+lineFiles.get(i).getLineNumbers());
			}
		System.out.println();
	}

	/**
	 * The method that represents order "-po" to print in alphabetic 
	 * order all words along with the corresponding list of files, 
	 * numbers of the lines in which the word occur and the frequency 
	 * of occurrence of the words.
	 * 
	 * @param w the words for the order "-po"
	 * @param lineFiles line numbers with file in Location list
	 */
	private void orderPo(Word w, ArrayList <Location> lineFiles) {
		System.out.println(w.getWord()+" allCounts="+w.getCounter());
		for(int i=0;i<lineFiles.size();i++){
			System.out.print(" File= "+lineFiles.get(i).getTextFile()+" this file counts= "+lineFiles.get(i).getCounter()+" lines= "+lineFiles.get(i).getLineNumbers());
			}
		System.out.println();
	}

	/**
	 * The method to store BSTree in the Binary file
	 * 
	 * @param wordBSTree BSTree that contains Words
	 * @param serialFile serialized the file
	 */
	private void storeBSTeeToBinaryFile(BSTree<Word> wordBSTree,String serialFile){
		try {
			FileOutputStream fout = new FileOutputStream(serialFile);
	        ObjectOutputStream oos = new ObjectOutputStream(fout);
	        oos.writeObject(wordBSTree);
	        oos.close();
	        System.out.println("Process complete. the tree was stored in a binary file (repository.ser)");
		} catch (IOException e) {
			e.printStackTrace();
	        System.out.println("Error: the tree cannot be stored in a binary file (repository.ser)");
		}
		
	}
	
	/**
	 * The method to read BSTree stored from the binary file
	 * and to restore BSTree including words from the binary file
	 * 
	 * @param wordBSTree BSTree that contains Words
	 * @param serialFile serialized the file
	 * @return returns wordBSTree restored which is BSTree that contains Word 
	 */
	private BSTree<Word> restoreBSTeeFromBinaryFile(BSTree<Word> wordBSTree,String serialFile){
		try{
	        FileInputStream fin = new FileInputStream(serialFile);
	        ObjectInputStream ois = new ObjectInputStream(fin);
	        wordBSTree = (BSTree<Word>) ois.readObject();
	        ois.close();
	        System.out.println("The tree was restored from binary file (repository.ser)");	
		} catch ( FileNotFoundException e){
	        	
		} catch ( ClassNotFoundException  | IOException e){
			e.printStackTrace();
	        System.out.println("Error: the tree cannot be read from binary file (repository.ser).");
		}
		return wordBSTree;
	}

}
