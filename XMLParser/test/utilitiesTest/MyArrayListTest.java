/**
 * 
 */
package utilitiesTest;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Iterator;
import utilities.MyArrayList;

/**
 * 
 * Contains the tests for the methods in the MyArrayList class
 * 
 * @author Mahdiyeh
 *@version 1.2
 */
public class MyArrayListTest {
	private MyArrayList<String> myArrayList;
	private MyArrayList<String> smallArrayList;
	private MyArrayList<String> testArrayList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myArrayList = new MyArrayList<>();
		smallArrayList = new MyArrayList<>();
		testArrayList = new MyArrayList<>();
		
		for (int i=0;i<100;i++) {
			myArrayList.add(String.valueOf(i));
		     testArrayList.add(String.valueOf(i));
		}
		smallArrayList.add("0");
		smallArrayList.add("1");
		smallArrayList.add("2");
		smallArrayList.add("3");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myArrayList = null;
		testArrayList = null;
	}

	/**
	 * Test method for {@link utilities.MyArrayList#MyArrayList()}.
	 */
	@Test
	public void testMyArrayList() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link utilities.MyArrayList#size()}.
	 */
	@Test
	public void testSize() {
		//We expects that myArrayList contains 100 elements
		int expected = 100; 
		int actual = myArrayList.size();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#clear()}.
	 */
	@Test
	public void testClear() {
		// we expect that testArrayList should be empty
		testArrayList.clear();
		
		int expected = 0; //size should be zero
		int actualSize = testArrayList.size();
		assertEquals(expected, actualSize);
		
		boolean actualBoolean = testArrayList.isEmpty();
		assertTrue(actualBoolean);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddNullObject() {
		String nullValue = null;
		try {
			myArrayList.add(nullValue);
			
			// If it continues then the method failed
			fail("Expected Exception was not thrown");

		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 * By passing null value as a parameter to add to a specific index
	 */
	@Test
	public void testAddNullObjectWithPosition() {
		String nullValue = null;
		int position = 101;
		try {
			myArrayList.add(position, nullValue);
			
			// If it continues then the method failed
			fail("Expected Exception was not thrown");

		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}


	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 * By passing a correct value as a parameter to add to the end of the ArrayList
	 */
	@Test
	public void testAddIntObjectTrue() {
		String newValue = "100";
		int position = 100;
		boolean actual = myArrayList.add(position, newValue);
		assertTrue(actual);
		
		String actualValue = myArrayList.get(position);
		assertEquals(newValue, actualValue);
		
		int expectedSize = 101;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}


	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 * By passing a correct value as a parameter to add to the middle of the ArrayList
	 */
	@Test
	public void testAddIntObjectTrueMiddlePosition() {
		String newValue = "45";
		int position = 45;
		boolean actual = myArrayList.add(position, newValue);
		assertTrue(actual);
		
		String actualValue = myArrayList.get(position);
		assertEquals(newValue, actualValue);
		
		int expectedSize = 101;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}


	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 * by passing an acceptable value in an unacceptable positive position
	 */
	@Test
	public void testAddIntObjectIndexOutOfBoundsException() {
		String newValue = "200";
		int position = 200;
		try {
		boolean actual = myArrayList.add(position, newValue);
		fail("Expected Exception was not thrown");

		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

	}


	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 * by passing an acceptable value in an unacceptable negative position
	 */
	@Test
	public void testAddIntObjectNegativePositionIndexOutOfBoundsException() {
		String newValue = "200";
		int position = -1;
		try {
			boolean actual = myArrayList.add(position, newValue);
			fail("Expected Exception was not thrown");			
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

	}

	/**
	 * Test method for {@link utilities.MyArrayList#add(java.lang.Object)}.
	 * by passing an acceptable value in an acceptable positive position
	 */
	@Test
	public void testAddObject() {
		String newValue="100";
		boolean actual = myArrayList.add(newValue);
		assertTrue(actual);
		
		int position=100;
		String actualValue = myArrayList.get(position);
		assertEquals(newValue, actualValue);
		
		int expectedSize = 101;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#addAll(utilities.ListADT)}.
	 * by passing an acceptable parameter to an acceptable variable
	 */
	@Test
	public void testAddAll() {
		boolean actual = myArrayList.addAll(testArrayList);
		assertTrue(actual);
		
		int expectedSize = 200;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#addAll(utilities.ListADT)}.
	 * by passing a null parameter to an acceptable variable
	 */
	@Test
	public void testAddAllNull() {
		testArrayList = null;
		try { 
			myArrayList.addAll(testArrayList);
			
			fail("Excpected Exception was not thrown");
		} catch (NullPointerException e) {
			// An Exception should be thrown
			assertTrue(true);
		}
		
	}

	/**
	 * Test method for {@link utilities.MyArrayList#addAll(utilities.ListADT)}.
	 * by passing an acceptable parameter to a null variable
	 */
	@Test
	public void testAddAlltoNull() {
		myArrayList = null;
		try { 
			myArrayList.addAll(testArrayList);
			
			fail("Excpected Exception was not thrown");
		} catch (NullPointerException e) {
			// An Exception should be thrown
			assertTrue(true);
		}
		
	}

	/**
	 * Test method for {@link utilities.MyArrayList#get(int)}.
	 * By using an acceptable parameter
	 */
	@Test
	public void testGetTrue() {
		int index = 0;
		String expected = "0";
		String actual = myArrayList.get(index);
		
		assertEquals(expected, actual);
		
		int index2 = 99;
		String expected2 = "99";
		String actual2 = myArrayList.get(index2);
		
		assertEquals(expected2, actual2);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#get(int)}.
	 * By using an unacceptable parameter
	 */
	@Test
	public void testGetWrongIndex() {
		int index = 101;
		try {
			myArrayList.get(index);
			
			fail("Expected exception was not thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true); 
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(int)}.
	 * by passing an unacceptable parameter
	 */
	@Test
	public void testRemoveBadIndex() {
		int index = 101;
		try {
			myArrayList.remove(index);
			
			fail("Expected exception was not thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true); 
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(int)}.
	 * by passing an null parameter
	 */
	@Test
	public void testRemoveNull() {
		String toRemove = null;
		try {
			myArrayList.remove(toRemove);
			
			fail("Expected exception was not thrown.");
		} catch (NullPointerException e) {
			assertTrue(true); 
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(int)}.
	 * by passing an acceptable parameter index
	 */
	@Test
	public void testRemove() {
		int index = 0;
		String expected = "0";
		String actual = myArrayList.remove(index);
		
		assertEquals(expected, actual);
		
		int index2 = 0;
		String expected2 = "1";
		String actual2 = myArrayList.remove(index2);
		
		assertEquals(expected2, actual2);
		
		int expectedSize = 98;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(int)}.
	 * by passing an acceptable parameter value
	 */
	@Test
	public void testRemoveIntObject() {
		String toRemove = "0";
		String actual = (String) myArrayList.remove(toRemove);
		
		assertEquals(toRemove, actual);
		
		int index2 = 0;
		String expected2 = "1";
		String actual2 = myArrayList.remove(index2);
		
		assertEquals(expected2, actual2);
		
		int expectedSize = 98;
		int actualSize = myArrayList.size();
		assertEquals(expectedSize, actualSize);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(java.lang.Object)}.
	 * by passing an acceptable parameter value that is not contained in the 
	 * ArrayList
	 */
	@Test
	public void testRemoveObjectFalse() {
		String toRemove = "100";
		String actual = (String) myArrayList.remove(toRemove);
		
		assertNull(actual);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#set(int, java.lang.Object)}.
	 * By passing a null value 
	 */
	@Test
	public void testSetNull() {
		int position = 0;
		String toChange = null;
		try {
			myArrayList.set(position, toChange);
			
			fail("Expected exception was not thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#set(int, java.lang.Object)}.
	 * By passing a negative value as index
	 */
	@Test
	public void testSetWrongIndex() {
		int position = -1;
		String toChange = "-1";
		try {
			myArrayList.set(position, toChange);
			
			fail("Expected exception was not thrown");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#set(int, java.lang.Object)}.
	 * by passing an acceptable value as a parameter
	 */
	@Test
	public void testSetTrue() {
		int position = 0;
		String expected = myArrayList.get(position);
		String toChange = "1";
		String actual = (String) myArrayList.set(position, toChange);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		
		boolean actual = myArrayList.isEmpty();
		assertFalse(actual);		
		
		myArrayList.clear();
		boolean actual2 = myArrayList.isEmpty();
		assertTrue(actual2);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#contains(java.lang.Object)}.
	 * by passing a null value as a parameter
	 */
	@Test
	public void testContainsNull() {
		String toSearch =  null;
		try {
			myArrayList.contains(toSearch);
			
			fail("Unexpcted exception occured");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#contains(java.lang.Object)}.
	 * by passing a correct value as a parameter
	 */
	@Test
	public void testContainsTrue() {
		String toSearch =  "99";
		boolean actual = myArrayList.contains(toSearch);
		assertTrue(actual);
		
		String toSearch2 =  "0";
		boolean actual2 = myArrayList.contains(toSearch2);
		assertTrue(actual2);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#contains(java.lang.Object)}.
	 * by passing a correct values that are not present in the ArrayList as a parameter
	 */
	@Test
	public void testContainsFalse() {
		String toSearch =  "-1";
		boolean actual = myArrayList.contains(toSearch);
		assertFalse(actual); 
		
		String toSearch2 =  "word";
		boolean actual2 = myArrayList.contains(toSearch2);
		assertFalse(actual2);
	}

	/**
	 * Test method for {@link utilities.MyArrayList#toArray(java.lang.Object[])}.
	 * By passing a null ArrayList as a parameter
	 */
	@Test
	public void testToArrayObjectArrayNullPointerException() {
		try {
			Object [] temp=null;
			String [] actual= (String []) myArrayList.toArray(temp);
			
			fail("Expected exception was not thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#toArray(java.lang.Object[])}.
	 * By passing an acceptable ArrayList as a parameter
	 */
	@Test
	public void testToArrayObjectArray() {
			Object [] temp=testArrayList.toArray();
			
			assertTrue(temp[0].equals(myArrayList.get(0)));
			assertTrue(temp[50].equals(myArrayList.get(50)));
			assertTrue(temp[99].equals(myArrayList.get(99)));
			assertTrue(temp[testArrayList.size()-1].equals(testArrayList.get(testArrayList.size()-1)));			
			assertTrue(temp.length==myArrayList.size());
			
	}

	/**
	 * Test method for {@link utilities.MyArrayList#toArray(java.lang.Object)}.
	 * By passing an acceptable ArrayList as a parameter
	 */
	@Test
	public void testToArray() {
		Object [] toHold=testArrayList.toArray();
		Object [] actual= myArrayList.toArray(toHold);
		
		assertTrue(toHold[0].equals(testArrayList.get(0)));
		assertTrue(toHold[50].equals(testArrayList.get(50)));
		assertTrue(toHold[99].equals(testArrayList.get(99)));
		assertTrue(toHold[testArrayList.size()-1].equals(testArrayList.get(testArrayList.size()-1)));
	}
	
	/**
	 * Test method for {@link utilities.MyArrayList#toArray(java.lang.Object)}.
	 * By passing an ArrayList that is larger than needed as a parameter
	 */
	@Test
	public void testToArrayBigHolder() {
		String [] toHold=new String [101];
		Object [] actual= myArrayList.toArray(toHold);
		
		assertTrue(toHold[0].equals(actual[0]));
		assertTrue(toHold[50].equals(actual[50]));
		assertTrue(toHold[99].equals(actual[99]));
		assertNull(toHold[(toHold.length)-1]);
		assertTrue(toHold[myArrayList.size()-1].equals(actual[myArrayList.size()-1]));
	}
	
	/**
	 * Test method for {@link utilities.MyArrayList#toArray(java.lang.Object)}.
	 * By passing an ArrayList that is smaller than needed as a parameter
	 */
	@Test
	public void testToArraySmallHolder() {
		Object [] toHold=new Object [80];
		toHold= myArrayList.toArray(toHold);
		
		assertTrue(toHold[0].equals(myArrayList.get(0)));
		assertTrue(toHold[50].equals(myArrayList.get(50)));
		assertTrue(toHold[99].equals(myArrayList.get(99)));
		assertTrue(toHold[myArrayList.size()-1].equals(String.valueOf(myArrayList.size()-1)));	
		} 

	/**
	 * Test method for {@link utilities.MyArrayList#iterator()}.
	 */
	@Test
	public void testIterator() {
		Iterator<String> it=myArrayList.iterator();
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
}
