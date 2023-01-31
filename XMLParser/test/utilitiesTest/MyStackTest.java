/**
 * 
 */
package utilitiesTest;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.Iterator;
import utilities.MyStack;

/**
 * Tests the methods of the MyStack class in the utilities package
 * 
 * @author Mahdiyeh 
 * @version 1.0
 *
 */
public class MyStackTest {
	/**
	 * Use of the <code>MyStack&ltE&gt</code>
	 */
	private MyStack<String> myStack;
	/**
	 * Use of the <code>MyStack&ltE&gt</code>
	 */
	private MyStack<String> tempStack;

	/**
	 * @throws java.lang.Exception if any exceptions are thrown
	 */
	@Before
	public void setUp() throws Exception {
		// creates 2  MyStack objects with the same contents
		myStack=new MyStack<String>();
		tempStack=new MyStack<String>();
		
		for (int i=0;i<100;i++) {
		     myStack.push(String.valueOf(i));
		     tempStack.push(String.valueOf(i));
		}
	}

	/**
	 * @throws java.lang.Exception if any exceptions are thrown
	 */
	@After
	public void tearDown() throws Exception {
		myStack = null;
		tempStack = null;

	}

	/**
	 * Test the size() method to check if the method returns the
	 * expected value
	 */
	@Test
	public void testSize() {
		//We expects that myStack contains 100 elements
		int expected = 100; 
		int actual = myStack.size();
		assertEquals(expected, actual);
	}

	/**
	 * Test the pop() method to check if the method deletes and 
	 * returns the correct element in a Stack containing elements
	 */
	@Test
	public void testPop() {
		try {
			String expectedElement=myStack.pop();
			assertEquals(expectedElement, "99");
			// we expect that myStack contains 99 elements
			int expectedSize = 99;
			int actual = myStack.size();
			assertEquals(expectedSize, actual);
			
			//myStack should not be empty
			boolean actualBoolean = myStack.isEmpty();
			assertFalse(actualBoolean);
			
		} catch (EmptyStackException e) {
			fail("Unexpected Exception");
		}
	}

	/**
	 * Test the pop() method to check if the method deletes and 
	 * returns the correct element in an empty Stack
	 */
	@Test
	public void testPopEmptyStack() {
		
		myStack.clear();
		// we expect that myStack is now Empty
		try {
			myStack.pop();
			// If the code continues then it should fail
			fail("Expected exception was not thrown");			
		} catch (EmptyStackException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the peek() method works correctly and returns the top 
	 * of the stack without removing the element
	 */
	@Test
	public void testPeekTrue() {
		try {
			myStack.pop();
			// we expect that myStack's last element is 98
			String expected = "98";
			String actual = myStack.peek();
			assertEquals(expected, actual);
			
			// we expect that myStack contains 99 elements
			int expectedSize = 99;
			int actualSize = myStack.size();
			assertEquals(expectedSize, actualSize);
			
			//myStack should not be empty
			boolean actualBoolean = myStack.isEmpty();
			assertFalse(actualBoolean);
			
		} catch (EmptyStackException e) {
			//No exceptions should be thrown
			fail("Unexpected Exception"); 
		}
	}
	
	/**
	 * Tests to see if the peek() method will throw an exception for 
	 * an empty Stack
	 */
	@Test
	public void testPeekBad() {
		try {
			myStack.clear();
			
			myStack.peek();
			
			fail("Expected exception was not thrown");
			
		} catch (EmptyStackException e) {
			//An exception should be thrown because the stack is empty
			assertTrue(true); 
		}
	}
	
	/**
	 * Tests if the myStack and the tempStack are equal using 
	 * the equal() method
	 */
	@Test
	public void testEqualsTrue() {
		// we expect that the two stacks are equal
		assertTrue(myStack.equals(tempStack));
	}	
	
	/**
	 * tests if the equals() method works, by checking the sizes of the 
	 * Stacks first
	 */
	@Test
	public void testEqualsFalseSize() {
		try {
		// remove an element from tempStack
		tempStack.pop();
		// we expect that the two stacks are not equal
		assertFalse(myStack.equals(tempStack));
		} catch (EmptyStackException e) {
			fail("Unexpected Exception");
		}
	}
	
	/**
	 * Test if the equals() method equal() method works by having
	 * different elements in the Stack but having the same size Stacks
	 */
	@Test 
	public void testEqualsFalseValues() {
		try {
		// remove an element from tempStack
		tempStack.pop();
		String a = "100";
		tempStack.push(a);
		// we expect that the two stacks are not equal
		assertFalse(myStack.equals(tempStack));
		} catch (EmptyStackException e) {
			fail("Unexpected Exception");
		}
	}
	
	/**
	 * tests if the equals() method works by checking if the variables 
	 * point to the same object
	 */
	@Test
	public void testEqualsSameObject() { 
		assertTrue((myStack.equals(myStack))); 
		MyStack<String> testStack1=new MyStack<String>();
		testStack1 = myStack;
		MyStack<String> testStack2=new MyStack<String>();
		testStack2 = myStack;
		
		myStack.pop();
		
		assertTrue((myStack.equals(testStack1)));
		assertTrue((testStack1.equals(testStack1)));
		assertTrue((testStack2.equals(testStack1)));
		
		assertTrue((testStack1 == testStack2)? true: false);
		boolean actual = myStack.equals(testStack1);
		assertTrue(actual);
	}
	
	/**
	 * tests if the equals method will not compare values of stacks
	 * with Null values
	 */
	@Test
	public void testEqualsNullValue() {
		tempStack= null;
		assertFalse(myStack.equals(tempStack));
	}

	/**
	 * tests if the clear() method deletes all of the elements in the Stack
	 * and the size() method returns zero and if the isEmpty() method
	 * will return true
 	 */
	@Test
	public void testClear() {
		// we expect that tempStack should be empty
		tempStack.clear();
		
		int expected = 0; //size should be zero
		int actualSize = tempStack.size();
		assertEquals(expected, actualSize);
		
		boolean actualBoolean = tempStack.isEmpty();
		assertTrue(actualBoolean);
	}
	
	/**
	 * tests if the contains() method works correctly 
	 */
	@Test
	public void testContainsTrue() {
		// tempStack should contain 90
		String search1 = "90"; 
		// tempStack should not contain 101
		String search101 = "-1";
		// tempStack should not contain "west"
		String search3 = "west";
		
		try {
			boolean result1 = tempStack.contains(search1);
			assertTrue(result1);
			
			boolean resultFalse = tempStack.contains(search101);
			assertFalse(resultFalse);
			
			boolean result3 = tempStack.contains(search3);
			assertFalse(result3);
			
		} catch (NullPointerException e) {
			fail("Unexpected Exception occured.");
		}
	}

	/**
	 * tests the contains() method to check it does not work with Null values
	 * and it throws an exception
	 */
	@Test
	public void testContainsNullValue() {
		String searchNull = null;
		
		try {
			boolean result = tempStack.contains(searchNull);
			
			fail("Expected Exception was not thrown");
			
		} catch (NullPointerException e) {
			assertTrue(true);

		}
	}
	
	/**
	 * tests if the search() method works as expected by searching 
	 * a null value in the Stack and not receiving an exception
	 */
	@Test
	public void testSearchNull() {
		String search = null;
		int expected = -1;
		int actual = tempStack.search(search);
		// should return -1 because the search value is null
		assertEquals(expected, actual);
	}
	
	/**
	 * tests if the search() method works correctly when given an empty 
	 * Stack by returning -1
	 */
	@Test
	public void testSearchEmptyStack() {
		String search = "3";
		tempStack.clear();
		int expected = -1;
		int actual = tempStack.search(search);
		// should return -1 because the stack is empty
		assertEquals(expected, actual);
	}

	/**
	 * test if the search methods works correctly when passed
	 * a parameter that is in the Stack by returning the distance 
	 * between the element in the STack and the top of the Stack
	 */
	@Test
	public void testSearchTrue() {
		String search = "98";
		int expected = 2;
		int actual = tempStack.search(search);
		// should return 1 because the distance between 98 and the top of the stack is 1
		assertEquals(expected, actual);
		
		String searchFirst = "99";
		int expected2 = 1;
		int actual2 = tempStack.search(searchFirst);
		// should return 0 because the distance between 98 and the top of the stack is 0
		assertEquals(expected2, actual2);
		
		
		String searchLast = "0";
		int expected3 = 100;
		int actual3 = tempStack.search(searchLast);
		// should return 99 because the distance between 98 and the top of the stack is 9
		assertEquals(expected3, actual3);
	}
	

	/**
	 * test if the search methods works correctly when passed
	 * a parameter that is not the Stack by returning -1
	 */
	@Test
	public void testSearchFalse() {
		String searchBig = "101";
		int expected = -1;
		int actual = tempStack.search(searchBig);
		// should return -1 because the value is not in the Stack
		assertEquals(expected, actual);
		
		String searchString = "kim";
		int expected2 = -1;
		int actual2 = tempStack.search(searchString);
		// should return -1 because the value is not in the Stack
		assertEquals(expected2, actual2);
		
		
		String searchNegative = "-1";
		int expected3 = -1;
		int actual3 = tempStack.search(searchNegative);
		// should return -1 because the value is not in the Stack
		assertEquals(expected3, actual3);
	}
	
	/**
	 * tests if the iterator() method works correctly
	 * by checking the values of the iterator and Stack using 
	 * the equals() method
	 */
	@Test
	public void testIterator() {
		Iterator<String> it=myStack.iterator();
		assertTrue(it.hasNext());
		
		int i=0;
		while (it.hasNext()){
		    String actual=it.next();
		    String expected=String.valueOf(i);
			assertEquals(expected, actual);
			i++;
			}
		
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Expected exception was not thrown");

		} catch (NoSuchElementException e){
			assertTrue(true);
		}
	}
	
	/**
	 * tests if the toArray() method works as expected
	 */
	@Test
	public void testToArrayObjectArray() {
			Object [] temp=tempStack.toArray();
			
			assertTrue(temp[0].equals("0"));
			assertTrue(temp[50].equals("50"));
			assertTrue(temp[99].equals("99"));
			assertTrue(temp.length==tempStack.size());
			
	}
	
	/**
	 * tests if the toArray() with parameters method works 
	 * as expected
	 */
	@Test
	public void testToArray() {
		String [] toHold=new String [100];
		Object [] actual= tempStack.toArray(toHold);
		
		assertTrue(toHold[0].equals(actual[0]));
		assertTrue(toHold[50].equals(actual[50]));
		assertTrue(toHold[99].equals(actual[99]));
		assertTrue(toHold[tempStack.size()-1].equals(actual[tempStack.size()-1]));
	}
}

