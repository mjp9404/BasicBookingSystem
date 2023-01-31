package Managers;

import java.util.Comparator;

import problemdomain.Shape;

/**
 * Compare Height Class implementing Comparator used to compare height of Shape Object
 * 
 */
public class CompareHeight implements Comparator<Shape> {

	/**
	 * Envokes Comparable interface from Shape Object
	 * @return returns 1 if greater, 0 is they match, -1 if lower
	 */
		@Override
		public int compare(Shape o1, Shape o2) {
			{
				return o1.compareTo(o2);
			}
		}
}