package problemdomain;

/**
 * Main Shape Class for creating, defining and comparing Shapes
 * 
 *
 */
public abstract class Shape implements Comparable<Shape> {
	double height;
	
	/**
	 * No arg Shape Constructor
	 */
	public Shape() {
		super();
	}
	
	/**
	 * Shape constructor with assigned values
	 * @param height Height of Shape
	 */
	public Shape(double height) {
		super();
		this.height = height;
	}

	/**
	 * Retrieve Height of Shape Object
	 * @return height Height of Shape Object
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height length Mutator for Shape Object
	 * @param height Height of Shape Object
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Abstract Method to calculate volume from height and side length
	 */
	public abstract double calcVolume();
	
	/**
	 * Abstract Method to calculate Base Area from side length
	 */
	public abstract double calcBaseArea();
	
	/**
	 * Comparable interface compare to method.
	 * Compares Shape objects by their height
	 */
	public int compareTo(Shape s) {
		{
			if (this.getHeight() > s.getHeight()) {
				return 1;
			} else if (this.getHeight() < s.getHeight()) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Shape [height=" + height + "]";
	}
	
}
