package exceptions;

public class EmptyQueueException extends Exception {

	public EmptyQueueException() {
		super("This Queue is currently empty!");
	}
}
