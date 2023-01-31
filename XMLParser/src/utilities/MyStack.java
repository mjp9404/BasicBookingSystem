package utilities;

import java.util.EmptyStackException;

/**
 * Implementation of the StackADT<E> interface
 * 
 * @author Mahdiyeh
 * @version 1.2
 *
 * @param <E> Type of element stored in MyStack
 */

public class MyStack<E> implements StackADT<E> {

	/**
	 * Serial number of the MyStack class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Uses the <code>MyArrayList&ltE&gt</code> in the utilities package
	 */
	MyArrayList<E> myArrayList = new MyArrayList<E>();

	@Override
	public void push(E toAdd) throws NullPointerException {
		myArrayList.add(toAdd);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (myArrayList.isEmpty()) {
			throw new EmptyStackException();
		}
		return myArrayList.remove(size() - 1);
	}

	@Override
	public E peek() throws EmptyStackException {
		if (myArrayList.isEmpty()) {
			throw new EmptyStackException();
		}
		return myArrayList.get(size() - 1);
	}

	@Override
	public void clear() {
		myArrayList.clear();
	}

	@Override
	public boolean isEmpty() {
		return myArrayList.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return myArrayList.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return (E[]) myArrayList.toArray(holder);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return myArrayList.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		if (toFind == null)
			return -1;
		if (myArrayList.size() == 0)
			return -1;
		int idx = 1;
		for (int i = myArrayList.size() - 1; i >= 0; i--) {
			if (myArrayList.get(i).equals(toFind)) {
				return (idx);
			}
			idx++;
		}
		// if the element was not found then false is returned
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return myArrayList.iterator();
	}

	@Override
	public boolean equals(StackADT<E> that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		// If the stacks are not the same length, then they won't be equal, easy first
		// test case
		if (myArrayList.size() != that.size())
			return false;

		E[] temp = (E[]) (that.toArray());
		for (int i = 0; i < myArrayList.size(); i++) {
			// Step through each item in both stacks, if any don't match return false
			if (!myArrayList.get(i).equals(temp[i])) {
				return false;
			}
		}

		// Haven't returned yet, they must be equal
		return true;
	}

	@Override
	public int size() {
		return myArrayList.size();
	}

}
