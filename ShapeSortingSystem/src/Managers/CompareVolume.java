package Managers;

import java.util.Comparator;

import problemdomain.Shape;

/**
 * Compare Volume Class implementing Comparator used to compare volume of Shape Object
 *
 */
public class CompareVolume implements Comparator<Shape> {

	/**
	 * Envokes Comparable interface from Shape Object
	 * @return returns 1 if greater, 0 is they match, -1 if lower
	 */
	@Override
	public int compare(Shape o1, Shape o2) {
		if (o1.calcVolume() > o2.calcVolume()) {
			return 1;
		} else if (o1.calcVolume() < o2.calcVolume()) {
			return -1;
		} else {
			return 0;
		}
	}
}
