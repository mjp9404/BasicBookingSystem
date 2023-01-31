package utilitiesTest;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.MyDLL;
import utilities.ListADT;

/**
 * Tests for the methods in the MyDLL class
 * @author Travis Milne, Kyle Helmer, Mahdiyeh
 * 
 * @version 1.1
 *
 */
class MyDLLTests {
	/**
	 * Uses the <code>ListADT</code> in the utilities package
	 */
	private ListADT DLL;

	/**
	 * Sets up environment for testing
	 * @throws Exception if there is an exception
	 */
	@Before
	void setUp() throws Exception {
		// Create a DLL list to be used for each test
		this.DLL = new MyDLL();

	}

	/**
	 * Sets up environmnet after each test is completed
	 * @throws Exception if there is an exception
	 */
	@After
	void tearDown() throws Exception {
		// Empty the contents of the DLL after each test
		this.DLL.clear();
	}

	/**
	 * Test the DLL contains no elements
	 */
	@Test
	void testIsEmptyHappy() {

		assertTrue(this.DLL.isEmpty());
		assertEquals(0, this.DLL.size());
	}

	/**
	 * Testing if the DLL is empty while having 2 elements added to the list
	 */
	@Test
	void testIsEmptySad() {
		this.DLL.add("a");
		this.DLL.add("b");
		// List is now a -> b

		assertFalse(this.DLL.isEmpty());
		assertEquals(2, this.DLL.size());
	}

	/**
	 * Testing the size of the DLL
	 */
	@Test
	void testSize() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// List is now a -> b -> c

		assertEquals(3, this.DLL.size());
	}

	/**
	 * Testing size after removing an element
	 */
	@Test
	void testSize2() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// List is now a -> b -> c

		assertEquals(3, this.DLL.size());
		this.DLL.remove(0);
		// List is now b -> c

		assertEquals(2, this.DLL.size());
	}

	/**
	 * Testing that elements can be added successfully
	 */
	@Test
	void testAddHappyCase() {
		assertTrue(this.DLL.add("a"));
		assertTrue(this.DLL.add("b"));
		assertTrue(this.DLL.add("c"));
		// List is now a -> b -> c
	}

	/**
	 * Testing that the exception is thrown correctly
	 */
	@Test
	void testAddSadException() {
		assertThrows(NullPointerException.class, () -> this.DLL.add(null));
	}

	/**
	 * Testing that the exception is thrown correctly
	 */
	@Test
	void testAddAtIndexNullException() {
		assertThrows(NullPointerException.class, () -> this.DLL.add(0, null));
	}

	/**
	 * Testing if an exception is thrown correctly
	 */
	@Test
	void testAddAtNegitiveIndexException() {
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.add(-1, "a"));

	}

	/**
	 * Testing if an exception is thrown correctly
	 */
	@Test
	void testAddAtLargeIndexException() {
		this.DLL.add("c");
		this.DLL.add("d");
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.add(5, "a"));

	}

	/**
	 * Testing that an element can be added to the front of the DLL if there is
	 * already an element in that place
	 */
	@Test
	void testAddAtIndexZero() {
		assertTrue(this.DLL.add(0, "a"));
		// List only contains a
		assertEquals("a", this.DLL.get(0));

		assertTrue(this.DLL.add(1, "b"));
		assertTrue(this.DLL.add(0, "c"));
		// List will now be c -> a -> b
		assertEquals("c", this.DLL.get(0));
		assertEquals("a", this.DLL.get(1));
		assertEquals("b", this.DLL.get(2));
	}

	/**
	 * tests the add() and get() methods in the MyDLL class
	 */
	@Test
	void testAddAtIndexHappy() {

		assertTrue(this.DLL.add(0, "a"));
		assertTrue(this.DLL.add(1, "b"));
		assertTrue(this.DLL.add(2, "c"));
		assertTrue(this.DLL.add(3, "d"));
		// DLL will now be a -> b -> c -> d
		assertTrue(this.DLL.add(2, "q"));
		assertTrue(this.DLL.add(0, "z"));
		// DLL will now be z -> a -> b -> q -> c -> d
		assertEquals("q", this.DLL.get(3));
		assertEquals("z", this.DLL.get(0));
		assertEquals("a", this.DLL.get(1));
		assertEquals("b", this.DLL.get(2));
		assertEquals("c", this.DLL.get(4));
		assertEquals("d", this.DLL.get(5));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testHasNextElementException() {
		assertThrows(NoSuchElementException.class, () -> this.DLL.iterator().next());
	}

	/**
	 * Testing that a list can be transfered over to an existing list with elements
	 * already present
	 */
	@Test
	void testAddAllHappy() {
		// Creating a new list to contain elements to be added to the test DLL
		ListADT addAllDLL = new MyDLL<>();
		this.DLL.add("q");
		this.DLL.add("z");
		// Test DLL is q -> z
		addAllDLL.add("a");
		addAllDLL.add("b");
		addAllDLL.add("c");
		addAllDLL.add("d");
		// Secondary DLL is a -> b -> c -> d

		assertTrue(this.DLL.addAll(addAllDLL));
		// Test DLL is now q -> z -> a -> b -> c -> d
		assertEquals("q", this.DLL.get(0));
		assertEquals("z", this.DLL.get(1));
		assertEquals("a", this.DLL.get(2));
		assertEquals("b", this.DLL.get(3));
		assertEquals("c", this.DLL.get(4));
		assertEquals(6, this.DLL.size());
	}

	/**
	 * Testing that a lists contents can be transfered over to a empty list 
	 */
	@Test
	void testAddAllIntoEmptyListHappy() {
		// Creating a new list to contain elements to be added to the test DLL
		ListADT addAllDLL = new MyDLL<>();
		addAllDLL.add("a");
		addAllDLL.add("b");
		addAllDLL.add("c");
		addAllDLL.add("d");
		// Test list is empty
		// Secondary DLL is a -> b -> c -> d
		assertTrue(this.DLL.addAll(addAllDLL));
		// Test list is now a -> b -> c -> d
		assertEquals(4, this.DLL.size());
		assertEquals("a", this.DLL.get(0));
		assertEquals("b", this.DLL.get(1));
		assertEquals("c", this.DLL.get(2));
		assertEquals("d", this.DLL.get(3));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testAddAllNullException() {
		assertThrows(NullPointerException.class, () -> this.DLL.addAll(null));
	}

	/**
	 * Test removing an element in the middle of the list
	 */
	@Test
	void testRemoveAtIndexHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		// DLL is now a -> b -> c
		assertEquals("b", this.DLL.remove(1));
		assertEquals("c", this.DLL.get(1));
		// DLL is now a -> c
	}

	/**
	 * Test removing an element in the head of the list
	 */
	@Test
	public void testRemoveAtHeadIndexHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		// DLL is now a -> b -> c
		assertEquals("a", this.DLL.remove(0));
		assertEquals("b", this.DLL.get(0));
		// DLL is now b -> c
	}

	/**
	 * tests the remove() method in the myDLL class
	 */
	@Test
	void testRemoveAtTailIndexHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		// DLL is now a -> b -> c
		assertEquals("c", this.DLL.remove(2));
		assertEquals(2, this.DLL.size());
		// DLL is now a -> b

	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testRemoveAtNegitiveIndexException() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");

		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.remove(-1));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testRemoveAtLargeIndexException() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");

		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.remove(7));
	}

	/**
	 * Testing that the head element can be removed and any other elements that are
	 * the same remain
	 */
	@Test
	void testRemoveHeadElementHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		this.DLL.add(3, "a");
		// DLL is now a -> b -> c -> a
		assertEquals("a", this.DLL.remove("a"));
		// DLL is now b -> c -> a
		assertEquals("a", this.DLL.get(2));
	}

	/**
	 * Testing that the tail element can be removed
	 */
	@Test
	void testRemoveTailElementHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		this.DLL.add(3, "q");
		// DLL is now a -> b -> c -> q
		assertEquals("q", this.DLL.remove("q"));
		// DLL is now a -> b -> c
		assertEquals("c", this.DLL.get(2));
	}

	/**
	 * Testing that a middle element can be removed and the rest of the list will
	 * shift as it needs
	 */
	@Test
	void testRemoveMiddleElementHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		this.DLL.add(3, "q");
		// DLL is now a -> b -> c -> q
		assertEquals("c", this.DLL.remove("c"));
		// DLL is now a -> b -> q
		assertEquals("q", this.DLL.get(2));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testRemoveElementSad() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		this.DLL.add(3, "a");
		assertThrows(NullPointerException.class, () -> this.DLL.remove(null));
	}

	/**
	 * Testing that elements at the head, tail and middle can be returned
	 */
	@Test
	void testGetElementHappy() {
		this.DLL.add(0, "a");
		this.DLL.add(1, "b");
		this.DLL.add(2, "c");
		// DLL is now a -> b -> c
		assertEquals("a", (String) this.DLL.get(0));
		assertEquals("b", (String) this.DLL.get(1));
		assertEquals("c", (String) this.DLL.get(2));

	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testGetElementSadNegitive() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.get(-1));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testGetElementSadBigNum() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.get(7));
	}

	/**
	 * Testing the head element can be set to a new piece of data while still
	 * keeping the links between nodes the same
	 */
	@Test
	void testSetHeadElementHappy() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// DLL is now a -> b -> c
		assertEquals("a", this.DLL.set(0, "q"));
		// DLL is now q -> b -> c
		assertEquals("q", this.DLL.get(0));
	}

	/**
	 * Testing the tail element can be set to a new piece of data while still
	 * keeping the links between nodes the same
	 */
	@Test
	void testSetTailElementHappy() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// DLL is now a -> b -> c
		assertEquals("c", this.DLL.set(2, "q"));
		// DLL is now a -> b -> q
		assertEquals("q", this.DLL.get(2));

	}

	/**
	 * Testing a middle element can be set to a new piece of data while still
	 * keeping the links between nodes the same
	 */
	@Test
	void testSetMiddleElementHappy() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// DLL is now a -> b -> c
		assertEquals("b", this.DLL.set(1, "q"));
		// DLL is now a -> q -> c
		assertEquals("q", this.DLL.get(1));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testSetElementIndexOutOfBounds() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.set(55, "burger"));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testSetElementIndexOutOfBounds2() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		assertThrows(IndexOutOfBoundsException.class, () -> this.DLL.set(-1, "burger"));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testSetElementNull() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		assertThrows(NullPointerException.class, () -> this.DLL.set(0, null));
	}

	/**
	 * Testing that a element is present in the DLL
	 */
	@Test
	void testContainsElementHappy() {
		this.DLL.add("a");
		this.DLL.add("b");
		this.DLL.add("c");
		// DLL is now a -> b -> c

		assertTrue(this.DLL.contains("a"));
		assertTrue(this.DLL.contains("b"));
		assertTrue(this.DLL.contains("c"));
	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testContainsElementException() {
		assertThrows(NullPointerException.class, () -> this.DLL.contains(null));
	}

	/**
	 * tets the contains() method in the myDLL class
	 */
	@Test
	void testContainsNoElement() {
		this.DLL.add("b");
		this.DLL.add("c");
		this.DLL.add("q");
		// DLL is now b -> c -> q
		assertFalse(this.DLL.contains("a"));
	}

	/**
	 * Testing that the DLL will be converted into an array
	 */
	@Test
	void testToArray() {

		this.DLL.add("r");
		this.DLL.add("c");
		this.DLL.add("q");
		// DLL is r -> c -> q
		Object[] array = this.DLL.toArray();
		// array is now [r, c, q]
		assertEquals("r", array[0]);
		assertEquals(3, array.length);

		array = this.DLL.toArray(array);

		assertEquals(3, array.length);

		assertEquals("r", array[0]);

	}

	/**
	 * Testing the exception is thrown correctly
	 */
	@Test
	void testToArrayException() {
		assertThrows(NullPointerException.class, () -> this.DLL.toArray(null));
	}

	/**
	 * Testing that a new Object array can be created with the contents of the DLL
	 */
	@Test
	void testToObjectArray() {
		this.DLL.add("b");
		this.DLL.add("c");
		this.DLL.add("q");

		Object[] array = this.DLL.toArray();

		assertEquals(3, array.length);

		assertEquals("b", array[0]);
		assertEquals("c", array[1]);
		assertEquals("q", array[2]);

	}
}
