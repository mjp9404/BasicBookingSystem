package exceptions;

/**
 * Exception class that is thrown whenever the BSTree is empty 
 * @author Travis Milne
 * @version August 2, 2022
 *
 */
public class TreeException extends Exception {
	/**
	 * The class version UID to ensure the backward compatibility 
	 * with the repository should the class specification change 
	 * with future enhancements.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Exception class with the error display
	 */
	public TreeException() {
		super("Tree is Empty");
		
	}

}
