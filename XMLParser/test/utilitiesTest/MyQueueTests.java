package utilitiesTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import utilities.*;


/**
 * Test cases for MyQueue class utilizing MyDLL data structure
 * @author Travis Milne
 * @version July 13, 2022
 */
public class MyQueueTests {
	/**
	 * Contains the Queue that is manipulated and tested
	 */
	private QueueADT queue;
	
	/**
	 * Contains the Iterator used for iterating the queue
	 */
	private Iterator it;
	
	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Create your Queue and assign it to queue
		this.queue = new MyQueue(6);
		// Create Iterator and assign it to it
		it = queue.iterator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.queue.dequeueAll();
	}

	/**
	 * Tests Enqueue method to add Nodes to the end of the Queue
	 * @throws EmptyQueueException If the Queue is Empty when using peek
	 */
	@Test
	public void testEnqueueHappy() throws EmptyQueueException {
		this.queue.enqueue("a");
		this.queue.enqueue("b");
		this.queue.enqueue("c");
		this.queue.enqueue("d");
		
		/**
		 * Queue should now be:
		 * 
		 * a -> b -> c -> d
		 */

		// Test the Queue is not empty.
		assertFalse(this.queue.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.queue.size());
		
		// Test the front of Queue for "a"
		assertEquals("a", this.queue.peek());
	}
	
	/**
	 * Test Dequeue method to remove a node from the front of the queue while returning it's element
	 * @throws EmptyQueueException thrown while using dequeue on an empty Queue
	 */
	@Test
	public void testDequeue() throws EmptyQueueException {
		this.queue.enqueue("a");
		this.queue.enqueue("b");
		this.queue.enqueue("c");
		this.queue.enqueue("d");
		
		/**
		 * Queue should now be:
		 * 
		 * a -> b -> c -> d
		 */
		
		// Test the Queue is not empty
		assertFalse(this.queue.isEmpty());
		
		// Test Dequeue Node which should return "a"
		assertEquals("a", this.queue.dequeue());
		
		// Test Dequeue Node which should return "b"
		assertEquals("b", this.queue.dequeue());
		
		// Test Dequeue Node which should return "c"
		assertEquals("c", this.queue.dequeue());
		
		// Test Dequeue Node which should return "d"
		assertEquals("d", this.queue.dequeue());
		
		// Test that the Queue is now Empty
		assertTrue(this.queue.isEmpty());
	}
	
	/**
	 * Test that exception is appropriately thrown when trying to dequeue an empty list
	 * @throws EmptyQueueException thrown while using dequeue on an empty Queue
	 */
	@Test
	public void testDequeueException() throws EmptyQueueException {
		
		// Test that Queue is Empty
		assertTrue(this.queue.isEmpty());
		
		// Throw Exception if Queue is Empty
		assertThrows(EmptyQueueException.class, () -> this.queue.dequeue());
	}

	/**
	 * Test that exception is appropriately thrown when trying to peek an empty list
	 * @throws EmptyQueueException thrown while using peek on an empty Queue
	 */
	@Test
	public void testPeekException() throws EmptyQueueException {
		
		// Test that Queue is Empty
		assertTrue(this.queue.isEmpty());
		
		// Throw Exception if Queue is Empty
		assertThrows(EmptyQueueException.class, () -> this.queue.peek());
	}
	
	/**
	 * Tests peek method which returns element of the node at the front of the queue without dequeueing it
	 * @throws EmptyQueueException thrown while using peek on an empty Queue
	 */
	@Test
	public void testPeekHappy() throws EmptyQueueException {
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		// Tests peek showing 1 is at the front of the queue
		assertEquals(1, this.queue.peek());
		
		// Removes 1 from Queue
		this.queue.dequeue();
		
		// Tests peek showing 2 is at the front of the queue
		assertEquals(2, this.queue.peek());
		
		// Removes 2 from Queue
		this.queue.dequeue();
		
		// Tests peek showing 3 is at the front of the queue
		assertEquals(3, this.queue.peek());
		
		// Removes 3 from Queue
		this.queue.dequeue();
		
		// Tests peek showing 4 is at the front of the queue
		assertEquals(4, this.queue.peek());
	}
	
	/**
	 * 
	 */
	@Test
	public void testToArray() {
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		// Test the size for Queue to be 4
		assertEquals(4, this.queue.size());
		
		// Use toArray to put contents of Queue into myArray array
		Object[] myArray = this.queue.toArray();
		
		// Tests element at index 0 of myArray to be 1
		assertEquals(1, myArray[0]);
		
		// Tests element at index 0 of myArray to be 2
		assertEquals(2, myArray[1]);
		
		// Tests element at index 0 of myArray to be 3
		assertEquals(3, myArray[2]);
		
		// Tests element at index 0 of myArray to be 4
		assertEquals(4, myArray[3]);
	}
	
	/**
	 * Test the toArray() method that 
	 */
	@Test
	public void testToArrayHolder() {
		// Create array to be passed as an arg to toArray method
		Object[] holder = new Object[4];
		
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		// Test the size for Queue to be 4
		assertEquals(4, this.queue.size());
		
		// Use toArray to pass holder Array into method and return the Queue in Array format
		holder = this.queue.toArray(holder);
		
		// Tests element at index 0 of myArray to be 1
		assertEquals(1, holder[0]);
		
		// Tests element at index 0 of myArray to be 2
		assertEquals(2, holder[1]);
		
		// Tests element at index 0 of myArray to be 3
		assertEquals(3, holder[2]);
		
		// Tests element at index 0 of myArray to be 4
		assertEquals(4, holder[3]);
	}
	
	/**
	 * Test Queue Iterator to successfully iterate across a Queue while returning each element it passes over
	 */
	@Test
	public void testQueueIterator() {
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		// Assigns new Iterator Object to it
		it = (Iterator) queue.iterator();
		
		// Iterate past first Node and return its Element which is 1
		assertEquals(1, this.it.next());
		
		// Iterate past first Node and return its Element which is 2
		assertEquals(2, this.it.next());
		
		// Iterate past first Node and return its Element which is 3
		assertEquals(3, this.it.next());
		
		// Iterate past first Node and return its Element which is 4
		assertEquals(4, this.it.next());	
	}
	
	/**
	 * Test isFull method successfully recognizes Queue is full
	 */
	@Test
	public void testIsFullHappy() {
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		this.queue.enqueue(5);
		this.queue.enqueue(6);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
		 */
		
		// Tests Queue to determine it is full
		assertTrue(this.queue.isFull());
	}
	
	/**
	 * Test to fail isFull method as it is in fact not full
	 */
	@Test
	public void testIsFullSad() {
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		this.queue.enqueue(5);
		
		/**
		 * Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4 -> 5
		 */
		
		// Tests Queue to determine it is not full
		assertFalse(this.queue.isFull());
	}
	
	/**
	 * Test to pass equals method
	 */
	@Test 
	public void testEqualsHappy() {
		// Create Queue to pass as an arg into equals method
		MyQueue that = new MyQueue();
		that.enqueue(1);
		that.enqueue(2);
		that.enqueue(3);
		that.enqueue(4);
		
		/**
		 * That Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * This Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		//test the Queues to see that they do equal
		assertTrue(this.queue.equals(that));
	}
	
	/**
	 * Test to fail equals method due to different amount of elements
	 */
	@Test 
	public void testEqualsSad() {
		// Create Queue to pass as an arg into equals method
		MyQueue that = new MyQueue();
		that.enqueue(1);
		that.enqueue(2);
		that.enqueue(3);
		
		/**
		 * That Queue should now be:
		 * 
		 * 1 -> 2 -> 3
		 */
		
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * This Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		//test the Queues to see that they do not equal
		assertFalse(this.queue.equals(that));
	}
	
	/**
	 * Test to fail equals method due to different type of elements
	 */
	@Test 
	public void testEqualsSadAgain() {
		// Create Queue to pass as an arg into equals method
		MyQueue that = new MyQueue();
		that.enqueue("a");
		that.enqueue(1);
		that.enqueue(2);
		that.enqueue(4);
		
		/**
		 * That Queue should now be:
		 * 
		 * a -> 1 -> 2 -> 3
		 */
		
		this.queue.enqueue(1);
		this.queue.enqueue(2);
		this.queue.enqueue(3);
		this.queue.enqueue(4);
		
		/**
		 * This Queue should now be:
		 * 
		 * 1 -> 2 -> 3 -> 4
		 */
		
		//test the Queues to see that they do not equal
		assertFalse(this.queue.equals(that));
	}
	
}
