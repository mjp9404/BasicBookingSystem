package utilitiesTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TreeException;
import utilities.BSTree;
import utilities.BSTree.InIterator;
import utilities.BSTreeADT;
import utilities.BSTreeNode;
import utilities.Iterator;
/**
 * Test cases for BSTree class
 * @author Travis Milne
 * @version August 2, 2022
 */
public class BSTtests {
	/**
	 * Contains the Binary Search Tree that is manipulated and tested
	 */
	public BSTreeADT<String> myBST;
	
	/**
	 * Contains the Iterator used for iterating the tree. Could be and of the three forms of Iterator
	 */
	public Iterator it;
	
	/**
	 * Constructs Binary Search tree to be used
	 * @throws Exception if there are any Exceptions
	 */
	@Before
	public void setUp() throws Exception {
		this.myBST = new BSTree();
	}

	/**
	 * Clears the Tree upon completion of tests
	 * @throws Exception any exception that might occur
	 */
	@After
	public void tearDown() throws Exception {
		this.myBST.clear();
	}
	
	/**
	 * Test getRoot method to retrieve the root node of a tree
	 * @throws TreeException Throws exception if Tree is empty when using getRoot
	 */
	@Test
	public void testGetRoot() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	   \
		 * 	  Bubbles		Ricky
		 * 			\
		 * 			Corey
		 */
		
		// Test to ensure Root is Julian
		assertEquals("Julian", myBST.getRoot().getData());
	}

	/**
	 * Test that exception is thrown when using getRoot method on an empty tree
	 */
	@Test
	public void testGetRootNull() {
		assertThrows(TreeException.class, () -> myBST.getRoot());
	}
	
	/**
	 * Test that exception is thrown when using contains method on an empty tree
	 */
	@Test
	public void testContainsException() {
		assertThrows(TreeException.class, () -> myBST.contains("Ricky"));
	}
	
	/**
	 * Test that exception is thrown when using search method on an empty tree
	 */
	@Test
	public void testSearchException() {
		assertThrows(TreeException.class, () -> myBST.search("Randy"));
	}
	
	/**
	 * Happy test to show isEmpty method returns true if the tree is empty
	 * @throws TreeException Throws exception if Tree is empty when using isEmpty method
	 */
	@Test
	public void testIsEmpty() throws TreeException {
		
		// Test isEmpty showing empty Tree
		assertTrue(myBST.isEmpty());
		
		//Confirm Tree is empty with a 0 height
		assertEquals(-1, myBST.getHeight());
	}
	
	/**
	 * Happy test to show size method returns 0 if the tree is empty
	 * @throws TreeException Throws exception if Tree is empty when using size method
	 */
	@Test
	public void testEmptySize() throws TreeException {
		assertEquals(0, myBST.size());
	}
	
	/**
	 * Sad test to show isEmpty method will fail if the tree is not empty
	 */
	@Test
	public void testisEmptyFail() {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	   \
		 * 	  Bubbles		Ricky
		 * 			\
		 * 			Corey
		 */
		
		// Test to fail as tree is in fact not empty
		assertFalse(myBST.isEmpty());
	}
	
	/**
	 * Test getHeight method to return an integer representing the height of the tree
	 * @throws TreeException thrown if a null node is added to tree
	 */
	@Test
	public void testHeight() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Cyrus");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	   \
		 * 	  Bubbles		Ricky
		 * 			\
		 * 			Corey
		 * 				\
		 * 				Cyrus
		 */
		
		// Test that return Integer is 4
		assertEquals(4, myBST.getHeight());
	}

	/**
	 * Tests add method by adding elements then iterating through to return all added elements
	 * @throws TreeException when the tree is empty
	 */
	@Test
	public void testAdd() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Sarah");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */
		
		// Constructs In Order iterator 
		Iterator it = myBST.inorderIterator();
		
		//Tests elements, in order, showing all are present
		assertEquals("Bubbles", it.next());
		assertEquals("Corey", it.next());
		assertEquals("Cyrus", it.next());
		assertEquals("Julian", it.next());
		assertEquals("Lucy", it.next());
		assertEquals("Ricky", it.next());
		assertEquals("Sarah", it.next());
		assertEquals("Trevor", it.next());
	}

	/**
	 * Test that NullPointerException is thrown when trying to add a null node to tree
	 */
	@Test
	public void testAddException() {
		assertThrows(NullPointerException.class, () -> myBST.add(null));
	}
	
	/**
	 * Test Pre Order Iterator to show it returns proper order
	 */
	@Test
	public void testPreIterator() {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		this.myBST.add("Sarah");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */
		
		// Construct pre order iterator
		Iterator it = myBST.preorderIterator();
		
		// Node elements in Pre Order all returning positive
		assertEquals("Julian", it.next());
		assertEquals("Bubbles", it.next());
		assertEquals("Corey", it.next());
		assertEquals("Cyrus", it.next());
		assertEquals("Ricky", it.next());
		assertEquals("Lucy", it.next());
		assertEquals("Trevor", it.next());
		assertEquals("Sarah", it.next());
	}
	
	/**
	 * Tests that exception is thrown when moving pre order iterator past final node
	 */
	@Test
	public void testPreIteratorException() {
		this.myBST.add("Julian");
		
		//Constructs Pre Order Iterator Object
		Iterator it = myBST.preorderIterator();
		
		//Move it to final node
		it.next();
		
		//Exception should be thrown when moving past final node
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	/**
	 * Tests that exception is thrown when moving post order iterator past final node
	 */
	@Test
	public void testPostIteratorException() {
		this.myBST.add("Julian");
		
		//Constructs Post Order Iterator
		Iterator it = myBST.postorderIterator();
		
		//Move it to final node
		it.next();
		
		//Exception should be thrown when moving past final node
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	/**
	 * Test NullPointerException is thrown when trying to iterate an empty tree
	 */
	@Test
	public void testPostIteratorNullRootException() {
		assertThrows(NullPointerException.class, () -> myBST.postorderIterator());
	}
	
	/**
	 * Tests that exception is thrown when moving post order iterator past final node
	 */
	@Test
	public void testInOrderIteratorException() {
		this.myBST.add("Julian");
		
		//Constructs In Order Iterator
		Iterator it = myBST.inorderIterator();
		
		// Move it to final node
		it.next();
		
		//Exception should be thrown when moving past final node
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	/**
	 * Test set Node data method by creating empty node, and adding data after
	 */
	@Test
	public void testNodeSet() {
		// Create null node
		BSTreeNode testNode = new BSTreeNode();
		//Set the data of Node
		testNode.setData("Jimmy");
		//Test the node ensuring data is accurate
		assertEquals("Jimmy", testNode.getData());
		
		// Create null node
		BSTreeNode testNodeL = new BSTreeNode("Randy");
		//Set the data of Node
		testNode.setLeft(testNodeL);
		//Test the node ensuring data is accurate
		assertEquals("Randy", testNode.left.getData());
		
		// Create null node
		BSTreeNode testNodeR = new BSTreeNode("Barb");
		//Set the data of Node
		testNode.setRight(testNodeR);
		//Test the node ensuring data is accurate
		assertEquals("Barb", testNode.right.getData());
	}
	
	/**
	 * Test Post Iterator Showing it returns elements in the correct order
	 */
	@Test
	public void testPostIterator() {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		this.myBST.add("Sarah");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */
		
		// Construct Post Order Iterator
		Iterator it = myBST.postorderIterator();
		
		//Correct order for post iteration, thus all should equal
		assertEquals("Cyrus", it.next());
		assertEquals("Corey", it.next());
		assertEquals("Bubbles", it.next());
		assertEquals("Lucy", it.next());
		assertEquals("Sarah", it.next());
		assertEquals("Trevor", it.next());
		assertEquals("Ricky", it.next());
		assertEquals("Julian", it.next());
	}

	/**
	 * Test contains method passes when looking for all present elements in the tree
	 * @throws TreeException is thrown when using contains method on an empty tree
	 */
	@Test
	public void testHappyContains() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		this.myBST.add("Sarah");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */
		
		// All cases return true showing tree contains all elements added
		assertTrue(myBST.contains("Julian"));
		assertTrue(myBST.contains("Ricky"));
		assertTrue(myBST.contains("Bubbles"));
		assertTrue(myBST.contains("Corey"));
		assertTrue(myBST.contains("Trevor"));
		assertTrue(myBST.contains("Lucy"));
		assertTrue(myBST.contains("Cyrus"));
		assertTrue(myBST.contains("Sarah"));
		
		// Tree contains only the 8 elements tested above
		assertEquals(8, myBST.size());
	}
	
	/**
	 *  Test to fail showing a false return when using contain method for element that doesn't exist in the tree
	 * @throws TreeException is thrown when using contains method on an empty tree
	 */
	@Test
	public void testSadContains() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		this.myBST.add("Sarah");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */
		
		// All 4 uses return false as none of the elements are present
		assertFalse(myBST.contains("Phil"));
		assertFalse(myBST.contains("George"));
		assertFalse(myBST.contains("Jacob"));
		assertFalse(myBST.contains("Barb"));
	}
	
	/**
	 * Tests search method that it returns the correct element that was search for
	 * @throws TreeException is thrown when applying search method to an empty tree
	 */
	@Test
	public void testHappySearch() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		this.myBST.add("Corey");
		this.myBST.add("Trevor");
		this.myBST.add("Lucy");
		this.myBST.add("Cyrus");
		this.myBST.add("Sarah");
		
		/** Tree should now be:
		 * 			Juilan
		 * 		   /	  \
		 * 	Bubbles		  Ricky
		 * 		\		   /  \
		 * 		Corey	 Lucy Trevor
		 * 			\		   /
		 * 		   Cyrus	 Sarah
		 */

		// Return search method into results Node
		BSTreeNode results = myBST.search("Ricky");
		// Test results data to show it was what was search for
		assertEquals("Ricky", results.getData());
		
		// Return search method into results Node
		results = myBST.search("Cyrus");
		// Test results data to show it was what was search for
		assertEquals("Cyrus", results.getData());
	}
	
	/**
	 * Test showing search method returns null when tree does not contain searched element
	 * @throws TreeException is thrown when applying search method to an empty tree
	 */
	@Test
	public void testSearchNoMatch() throws TreeException {
		this.myBST.add("Julian");
		this.myBST.add("Ricky");
		this.myBST.add("Bubbles");
		
		// Test for a Null return when searching for Randy
		assertNull(myBST.search("Randy")); 
	}
}
