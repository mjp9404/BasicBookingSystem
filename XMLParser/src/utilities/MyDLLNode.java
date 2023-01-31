package utilities;

/**
 * MyDLLNode class to create nodes to be used within a doubly linked list 
 * 
 * 
 * @author Kyle Helmer
 *
 */

public class MyDLLNode<E> {

	private E element;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;

	/**
	 * 
	 * @param o Object to be used to create the DLL node
	 */
	
	
	public MyDLLNode(E element) {
		this.element = element;
	}

	
	/**
	 * 
	 * @return returns the element of the DLL node
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * 
	 * @param element changing the element contain in the DLL node to the one
	 *                specified
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * 
	 * @return returns the next DLL node
	 */
	public MyDLLNode getNext() {
		return next;
	}

	/**
	 * 
	 * @param myDLLNode sets the next DLL node to a specified value
	 */
	public void setNext(MyDLLNode myDLLNode) {
		this.next =  (MyDLLNode<E>) myDLLNode;
	}

	/**
	 * 
	 * @return returns the previous DLL node
	 */
	public MyDLLNode getPrev() {
		return prev;
	}

	/**
	 * 
	 * @param nextNode sets the previous DLL node to a specified value
	 */
	public void setPrev(Object prev) {
		this.prev = (MyDLLNode<E>) prev;
	}

}
