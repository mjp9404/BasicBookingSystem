package problemdomain;

/**
 * Class for creating and defining Cylinder Object
 * Extends Abstract Shape Class
 * 
 *
 */
public class Cylinder extends Shape {
	double radius;
	
	/**
	 * No arg Cylinder constructor
	 */
	public Cylinder() {
		super();
	}
	
	/**
	 * Cylinder Object constructor with assigned values
	 * @param height Height of Cylinder Object
	 * @param side Length of one Side of Cylinder Object
	 */
	public Cylinder(double height, double radius) {
		super();
		this.height = height;
		this.radius = radius;
	}

	/**
	 * Retrieve Height of Cylinder Object
	 * @return height Height of Cylinder Object
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Cylinder Object
	 * @param height Height of Cylinder Object
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Retrieve Side length of Cylinder Object
	 * @return side Length of one Side of Cylinder Object
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Side length Mutator for Cylinder Object
	 * @param side Length of one Side of Cylinder Object
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Cylinder Object
	 */
	@Override
	public double calcVolume() {
		return Math.PI*Math.pow(radius, 2)*height;
	}

	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Cylinder Object
	 */
	@Override
	public double calcBaseArea() {
		return Math.PI*Math.pow(radius, 2);
	}

	/**
	 * Custom toString method
	 * @return String defining Cylinder Object
	 */
	public String toString() {
		return String.format("%-18s", "Cylinder:")  + 
				String.format("%s %-10.2f", "r =" , radius) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}
}
