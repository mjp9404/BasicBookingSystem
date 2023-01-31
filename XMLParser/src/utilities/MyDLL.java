package utilities;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * MyDLL class to create a doubly linked list
 * 
 * @author Kyle Helmer
 *
 * 
 */
public class MyDLL<E> implements ListADT<E> {

	private static final long serialVersionUID = 6958588139945908255L;

	/**
	 * the head of the Linked List
	 */
	private MyDLLNode<E> head;
	/**
	 * the tail of the Linked List
	 */
	private MyDLLNode<E> tail;
	/**
	 * the size of the Linked List
	 */
	private int size = 0;

	/**
	 * Default constructor to make a doubly linked list
	 */
	public MyDLL() {
		this.head = null;
		this.tail = null;
	}

	/**
	 * returns the size of the linked list
	 * @return - returns the number of elements contained in the doubly linked list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Will empty the list of all elements
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Add a new element to a specified index.
	 * 
	 * @param index - the specified spot in the list where the element will be added
	 * @param toAdd - the element that is being added to the list
	 * 
	 * @return - returns <code> true </code> if the element is added successfully,
	 *         returns <code> false </code> if the element is not added successfully
	 * 
	 * @exception NullPointerException      - thrown when toAdd is a null value or
	 *                                      if list does not allow null elements
	 * @exception IndexOutOfBoundsException - thrown when the specified index is a
	 *                                      negative value or if it is larger then
	 *                                      the existing lists size
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		MyDLLNode newNode = new MyDLLNode(toAdd);
		MyDLLNode curr = head;

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (toAdd == null) {
			throw new NullPointerException();
		}
		if (index == 0) {
			if (size == 0) {
				head = newNode;
				tail = newNode;
				size++;
			} else {
				head.getNext().setPrev(newNode);
				newNode.setNext(head);
				head = newNode;
				size++;
			}
			return true;
		} else if (index == size) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			size++;
			return true;
		} else {
			for (int k = 0; k < index; k++) {
				curr = curr.getNext();
			}
			curr.getPrev().setNext(newNode);
			newNode.setPrev(curr.getPrev());
			curr.setPrev(newNode);
			newNode.setNext(curr);
			size++;
			return true;
		}

	}

	/**
	 * Will add a new element to the back of the list
	 * 
	 * @param toAdd - the element that is being added to the list
	 * 
	 * @return - returns <code> true </code> if the element is added successfully,
	 *         returns <code> false </code> if the element is not added successfully
	 * 
	 * @exception NullPointerException - thrown if the element being added is null
	 *                                 or if the list does not allow null values
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);

		if (toAdd == null) {
			throw new NullPointerException();
		}
		if (head == null) {
			head = newNode;
			tail = newNode;
			size++;
			return true;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			size++;
			return true;
		}
	}

	/**
	 * Will add all elements from one list to another list of the same type. New
	 * elements will be appended to the back of the list when called
	 * 
	 * @param toAdd - the list that is to be appended to the existing list
	 * 
	 * @return - returns <code> true </code> if the element is added successfully,
	 *         returns <code> false </code> if the element is not added successfully
	 * 
	 * @exception NullPointerException - thrown if the list being added is null
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		MyDLL<E> newList = (MyDLL<E>) toAdd;
		MyDLLNode curr = newList.head;
		int count = 0;
		for (int k = 0; k < toAdd.size() - 1; k++) {
			if (head == null) {

				head = curr;
				tail = curr;
				count++;
				curr = curr.getNext();
			} else {

				tail.setNext(curr);
				curr.setPrev(tail);
				tail = curr;
				count++;
				curr = curr.getNext();

			}
		}
		size = size + newList.size;
		return true;

	}

	/**
	 * Will retrieve the element at the given index
	 * 
	 * @param index - the specified spot in the list where the element will be
	 *              retrieved
	 * 
	 * @return - the element that is being retrieved from the list
	 * 
	 * @exception IndexOutOfBoundsException - thrown when the specified index is a
	 *                                      negative value or if it is larger then
	 *                                      the existing lists size
	 * 
	 * 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		MyDLLNode curr = head;

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Cannot get this element because it is out of bounds");
		}
		for (int k = 0; k < index; k++) {
			curr = curr.getNext();
		}
		return (E) curr.getElement();
	}

	/**
	 * Will remove the element at the given index
	 * 
	 * @param index - the specified spot in the list where an element will be
	 *              removed
	 * 
	 * @return - the element that has been removed
	 * 
	 * @exception IndexOutOfBoundsException - thrown when the specified index is a
	 *                                      negative value or if it is larger then
	 *                                      the existing lists size
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		MyDLLNode curr = head;

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int k = 0; k < index; k++) {
			curr = curr.getNext();
		}
		if (curr == head) {
			head = head.getNext();
			size--;
			return (E) curr.getElement();
		}

		else if (curr.getNext() != null) {
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
			size--;
			return (E) curr.getElement();
		} else {
			curr.getPrev().setNext(null);
			tail = curr.getPrev();
			size--;
			return (E) curr.getElement();
		}

	}

	/**
	 * Will remove the this element in the list. Note if more then 1 element
	 * matches, then only the first occurrence of this element will be removed
	 * 
	 * @param toRemove - the element that is to be removed from the list.
	 * 
	 * @return - the element being removed
	 * 
	 * @exception NullPointerException - thrown if the element being passed is null
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		MyDLLNode curr = head;
		if (toRemove == null) {
			throw new NullPointerException();
		}
		while (curr.getElement() != toRemove) {
			curr = curr.getNext();
		}
		if (curr.getNext() == null) {
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			size--;
			return (E) curr.getElement();

		} else if (curr.getPrev() == null) {
			head.getNext().setPrev(null);
			head = head.getNext();
			size--;
			return (E) curr.getElement();
		} else {
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
			size--;
			return (E) curr.getElement();
		}
	}

	/**
	 * Will change the data contained at a specified index
	 * 
	 * @param index    - the specified index
	 * @param toChange - the new element that is being swapped into the list
	 * 
	 * @return - the previous element that was just swapped
	 * 
	 * @exception IndexOutOfBoundsException - thrown when the specified index is a
	 *                                      negative value or if it is larger then
	 *                                      the existing lists size
	 * @exception NullPointerException      - thrown if the new element is null
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		MyDLLNode curr = head;
		E temp = null;
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (toChange == null) {
			throw new NullPointerException();
		}
		for (int k = 0; k < index; k++) {
			curr = curr.getNext();
		}
		temp = (E) curr.getElement();
		curr.setElement(toChange);

		return temp;
	}

	/**
	 * 
	 * @return - returns <code> true </code> if the list contains no elements,
	 *         returns <code> false </code> if the list is not empty
	 * 
	 */
	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Searches a list to see if the element is inside the list
	 * 
	 * @param toFind - the element that is being searched for in the list
	 * 
	 * @return - returns <code> true </code> if the list contains the element,
	 *         returns <code> false </code> if the list does not contain the element
	 * 
	 * @exception NullPointerException - thrown if the specified element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		MyDLLNode curr = head;

		if (toFind == null) {
			throw new NullPointerException();
		}
		while (curr != toFind) {
			if (curr.getElement().equals(toFind)) {
				return true;
			} else {
				curr = curr.getNext();
			}
			if (curr == null) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param toHold - the array being passed to the method
	 * 
	 * @return - a shallow copy of the array
	 * 
	 * @exception NullPointerException - thrown if the array being passed is null
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException();
		}

		MyDLLNode curr = head;
		toHold = (E[]) new Object[size];

		for (int k = 0; k < toHold.length; k++) {
			toHold[k] = (E) curr.getElement();
			curr = curr.getNext();
		}

		return (E[]) toHold;
	}

	/**
	 * Converts the list into an Object array. Index 0 of the array will be the head
	 * of the list. Order of the array will be same as the order of the list
	 * 
	 * @return - The newly created array
	 * 
	 */
	@Override
	public Object[] toArray() {
		MyDLLNode curr = head;

		Object[] objectArray = new Object[size];

		for (int k = 0; k < size; k++) {
			objectArray[k] = curr.getElement();
			curr = curr.getNext();

		}

		return objectArray;
	}

	/**
	 * Returns an iterator for the linked list
	 * @return an iterator for the linked list
	 */
	@Override
	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	/**
	 * Custom iterator inner class for traversing through a doubly linked list
	 * 
	 */
	private class DLLIterator implements Iterator<E> {

		/**
		 * position of the iterator
		 */
		private MyDLLNode<E> position;
		/**
		 * linked to the previous node
		 */
		private MyDLLNode<E> previousNode;

		/**
		 * class constructor
		 */
		public DLLIterator() {
			position = head;
			previousNode = null;
		}

		/**
		 * returns a boolean specifying if there is a next element or not
		 * @return - returns <code>true</code> if the list has more elements, returns
		 *         <code>false</code> if the list has no more elements
		 * 
		 */
		@Override
		public boolean hasNext() {
			return position != null;
		}

		/**
		 * Returns the next element in the list
		 * @return - the next element in the list
		 * 
		 * @exception NoSuchElementException - thrown if the next element in the list is
		 *                                   null
		 */
		@Override
		public E next() throws NoSuchElementException {

			if (!hasNext()) {
				throw new NoSuchElementException("Next() error");
			} else {

				previousNode = position;
				position = position.getNext();
				return (E) previousNode.getElement();

			}
		}

	}

}
