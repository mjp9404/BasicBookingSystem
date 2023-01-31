package utilities;


import exceptions.EmptyQueueException;

/**
 * Implementation of the QueueADT<E> interface
 * @author Travis Milne, Kyle Helmer
 *
 * @param <E> Type of element stored in MyQueue
 */
public class MyQueue<E> implements QueueADT<E> {
	private MyDLL<E> dll;
	private int capacity;
    
    public MyQueue() {
    	this.dll = new MyDLL();
	}

    public MyQueue(int capacity) {
    	this.dll = new MyDLL();
    	this.capacity = capacity;
    }
    
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		dll.add(toAdd);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(dll.size()==0) {
			throw new EmptyQueueException();
		}
		return dll.remove(0);
	}

	@Override
	public E peek() throws EmptyQueueException {
		if(dll.size()==0) {
			throw new EmptyQueueException();
		}
		return dll.get(0);
	}

	@Override
	public void dequeueAll() {
		while(dll.iterator().hasNext()) {
			dll.remove(0);
		}
	}

	@Override
	public boolean isEmpty() {
		if(dll.size() == 0)
		return true;
		else {
		return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return dll.iterator();
	}
		
	@Override
	public boolean equals(QueueADT<E> that) {
		Iterator it2 = that.iterator();
		Iterator it = this.dll.iterator();

		if(that.size() != this.size()) {
			return false;
		}
		while(it.hasNext()) {
			if(it.next() != it2.next()) {
				return false;
			}
		}
		return
		true;
	}

	@Override
	public Object[] toArray() {
		return dll.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return dll.toArray(holder);
	}

	@Override
	public boolean isFull() {
		if(this.dll.size() == capacity) {
		return true;
		} else {
		return false;
		}
	}

	@Override
	public int size() {
		return dll.size();
	}
}
