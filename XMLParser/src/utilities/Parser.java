/**
 * 
 */
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exceptions.EmptyQueueException;

/**
 * @author Mahdiyeh
 * @version 2.1
 *
 */
public class Parser {
	/**
	 * Use of the BufferedReader to read the XML file
	 */
	private BufferedReader xmlFile;
	/**
    * Use of the <code>MyStack&ltE&gt</code> class
	 */
	private MyStack<String> xmlStack;
	/**
	 * Use of the <code>MyQueue&ltE&gt</code>
	 */
	private MyQueue<String> extrasQ;
	/**
	 * Use of the <code>MyQueue&ltE&gt</code>
	 */
	private MyQueue<String> errorQ;
	/**
	 * A line from xmlFile
	 */
	String line = "";

	/**
	 * Class constructors that calls on the methods to analyze an XML file
	 */
	public Parser() { 
		xmlStack = new MyStack<>();
		extrasQ = new MyQueue<>();
		errorQ = new MyQueue<>();
	}

	/**
	 * Analyzes the File for any errors. Algorithm used was by Kitty Wong
	 * and also processes root tag and comment tag:
	 * <pre>
Kitty’s XML Parser Algorithm
For CPRG 311
If Self_Closing_Tag
	Ignore
If Start_Tag
	Push on stack
If End_Tag
	If matches top of stack, pop stack and all is well
	Else if matches head of errorQ, dequeue and ignore
	Else if stack is empty, add to errorQ
	Else
		Search stack for matching Start_Tag
		If stack has match
			Pop each E from stack into errorQ until match, report as error
		Else
			Add E to extrasQ
Repeat until EOF

If stack is not empty, pop each E into errorQ
If either queue is empty (but not both), report each E in both queues as error
If both queues are not empty, peek both queues
	If they don’t match, dequeue from errorQ and report as error
	Else dequeue from both
Repeat until both queues are empty
</pre>
	 * 
	 * @param filename the name of the XML file that will be processed
	 * @throws IOException when the BufferedReader cannot the read the file
	 */
	public void analize(String filename) throws IOException {
		// Initialize Scanner object
		File file = new File(filename);
		xmlFile = new BufferedReader(new FileReader(file));

		String line = ""; // a row of xml file
		String startTag = "";
		String endTag = "";
		boolean loopTagComment=false;
		while (true) {
			String xmlTag = nextTag();
			if (xmlTag.equals(""))
				break;
			if (!xmlTag.startsWith("<") && !xmlTag.endsWith(">")) 
				continue; // between tags
			if (loopTagComment) {
				if (xmlTag.endsWith("-->") ) 
					loopTagComment=false;
				continue;
			}
			if (xmlTag.startsWith("<?") && xmlTag.endsWith("?>")) {
				continue; // between processing tags				
			}
			if (xmlTag.startsWith("<!--") && !xmlTag.endsWith("-->")) {
				loopTagComment=true;
				continue; // between processing tags
			}
			if (xmlTag.startsWith("<!--") && xmlTag.endsWith("-->")) {
				loopTagComment=false;
				continue; // between processing tags
			}
			
			if(startTag.equals("")) {
				startTag = getTagName(xmlTag);
			}

//			Kitty’s XML Parser Algorithm
//			If Self_Closing_Tag
//			   Ignore
			if (xmlTag.startsWith("<") && xmlTag.endsWith("/>")) {
				continue;
			}
//			If Start_Tag
//			   Push on stack
			if (xmlTag.startsWith("<") && !xmlTag.startsWith("</") && xmlTag.endsWith(">")) {
				String tagName = getTagName(xmlTag);
				xmlStack.push(tagName);
				continue;
			}
			String tagName = getTagName(xmlTag);
			String headOfQueue = "";
			if (!errorQ.isEmpty())
				try {
					headOfQueue = errorQ.peek();
				} catch (EmptyQueueException e) {
					e.printStackTrace();
				}
//			If End_Tag
			if (xmlTag.startsWith("</") && xmlTag.endsWith(">")) {
				endTag = tagName;
//					If matches top of stack, pop stack and all is well
				if (!xmlStack.isEmpty() && xmlStack.peek().equals(tagName)) {
					xmlStack.pop();
					continue;
//					Else if matches head of errorQ, dequeue and ignore
				} else if (!errorQ.isEmpty() && headOfQueue.equals(tagName)) {
					try {
						errorQ.dequeue();
						continue;
					} catch (EmptyQueueException e) {
						e.printStackTrace();
					}
				}
//					Else if stack is empty, add to errorQ 
				else if (xmlStack.isEmpty()) {
					errorQ.enqueue(tagName);
					continue;
//						Else
//						Search stack for matching Start_Tag
				} else {
//						If stack has match
//							Pop each E from stack into errorQ until match, report as error
					if (xmlStack.contains(tagName)) {
						while (!xmlStack.isEmpty()) {
							String t = xmlStack.pop();
							errorQ.enqueue(t);
							// report as error
//							System.out.println("error - Start tag doesn't match end tag: " + t);
							if (xmlStack.peek().equals(tagName)) {
								t = xmlStack.pop();
								break;

							}
								
						}
					}
//						Else
//						Add E to extrasQ
					else {
						extrasQ.enqueue(tagName);
					}
				}
			}
		}

		if (loopTagComment) {
			System.out.println("comment tag doesn't closed");
		}
//			Repeat until EOF
//			If stack is not empty, pop each E into errorQ
		while (!xmlStack.isEmpty()) {
			String e = xmlStack.pop();
			errorQ.enqueue(e);
		}
//			If either queue is empty (but not both), report each E in both queues as error
		if ((!extrasQ.isEmpty() && errorQ.isEmpty()) || (!errorQ.isEmpty() && extrasQ.isEmpty())) {
			while (!extrasQ.isEmpty()) {
				try {
					String extraq = extrasQ.dequeue();
					System.out.println("The match for this element was not found: " + extraq);
				} catch (EmptyQueueException e) {
					e.printStackTrace();
				}
			}

			while (!errorQ.isEmpty()) {
				try {
					String errorq = errorQ.dequeue();
					System.out.println("The match for this element was not found: " + errorq);
				} catch (EmptyQueueException e) {
					e.printStackTrace();
				}
			}
		}
//			If both queues are not empty, peek both queues
//			If they don’t match, dequeue from errorQ and report as error
		while (!extrasQ.isEmpty() && !errorQ.isEmpty()) {
			try {
				String extraq = "";
				String errorq = "";
				extraq = extrasQ.peek();

				errorq = errorQ.peek();

				if (!extraq.equals(errorq)) {
					String s = errorQ.dequeue();
					System.out.println("The tag names do not match: " + s);
//				Else dequeue from both
				} else {
					errorQ.dequeue();
					extrasQ.dequeue();
				}

//			Repeat until both queues are empty
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!startTag.equals(endTag)) {
			System.out.println("There is NOT one root tag.");
		}
	}

	/**
	 * Returns the next tag in the line when the line being analyzed.
	 * when line is empty read another line from file. return a String 
	 * between "<" and ">" when the line is not empty. 
	 * Returns an empty string when it has reached the end of the file.
	 * 
	 * @return the next tag in the file and an empty string when it has reached the end of the file.
	 * @throws IOException if the file cannot be read
	 */
	private String nextTag() throws IOException {
		String rtn = "";
		while (true) {
			if (line.equals("")) {
				line = xmlFile.readLine();
				if (line == null) {
					line = "";
					return rtn;
				}

			}

			while (line.length() > 0) {
				String ch = line.substring(0, 1);
				if (ch.equals("<") && !rtn.equals(""))
					return rtn;

				rtn += ch;
				line = line.substring(1);

				if (ch.equals(">"))
					return rtn;
			}
		}

	}

	/**
	 * returns only the name of the tag that is passed
	 * The name consists of the alphabet(From a to z and A to Z), hyphen(-) and underscore(_)
	 * 
	 * @param xmlTag a String that contains the tag marks "<, </, >, />, <?, ?>"
	 * @return the name of the tag
	 */
	private String getTagName(String xmlTag) {
		String tagName = "";
		int i = 0;
		for (i = 0; i < xmlTag.length(); i++) {
			String ch = xmlTag.substring(i, i + 1);
			if (ch.matches("[a-zA-Z_-]*"))
				break;
		}
		for (int j = i; j < xmlTag.length(); j++) {
			String ch = xmlTag.substring(j, j + 1);
			if (!ch.matches("[a-zA-Z_-]*"))
				break;
			tagName += ch;
		}
		return tagName;
	}
}
