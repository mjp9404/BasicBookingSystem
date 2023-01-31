package problemdomain;

/**
 * Class for creating and defining Cone Object
 * Extends Abstract Shape Class
 * 
 *
 */
public class Cone extends Shape {
	double radius;
	
	/**
	 * No arg Cone constructor
	 */
	public Cone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Cone Object constructor with assigned values
	 * @param height Height of Cone Object
	 * @param side Length of one Side of Cone Object
	 */
	public Cone(double height, double radius) {
		super();
		this.height = height;
		this.radius = radius;
	}

	/**
	 * Retrieve Height of Cone Object
	 * @return height Height of Cone Object
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Pyramid Object
	 * @param height Height of Pyramid Object
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Retrieve Side length of Cone Object
	 * @return side Length of one Side of Cone Object
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Side length Mutator for Cone Object
	 * @param side Length of one Side of Cone Object
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Cone Object
	 */
	@Override
	public double calcVolume() {
		return (1/3.0) * Math.PI*Math.pow(radius, 2) * height;
	}

	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Cone Object
	 */
	@Override
	public double calcBaseArea() {
		return Math.PI*Math.pow(radius, 2);
	}

	/**
	 * Custom toString method
	 * @return String defining Cone Object
	 */
	@Override
	public String toString() {
		return String.format("%-17s ","Cone:") + 
				String.format("%s %-10.2f", "r =" , radius) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
}
}
