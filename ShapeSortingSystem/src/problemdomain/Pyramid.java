package problemdomain;
/**
 * Class for creating and defining Pyramid Object
 * Extends Abstract Shape Class
 * 
 *
 */
public class Pyramid extends Shape {
	double side;
	
	/**
	 * No arg Pyramid constructor
	 */
	public Pyramid() {
		super();
	}
	
	/**
	 * Pyramid Object constructor with assigned values
	 * @param height Height of Pyramid Object
	 * @param side Length of one Side of Pyramid Object
	 */
	public Pyramid(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}

	/**
	 * Retrieve Height of Pyramid Object
	 * @return height Height of Pyramid Object
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
	 * Retrieve Side length of Pyramid Object
	 * @return side Length of one Side of Pyramid Object
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Side length Mutator for Pyramid Object
	 * @param side Length of one Side of Pyramid Object
	 */
	public void setSide(double side) {
		this.side = side;
	}
	
	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Pyramid Object
	 */
	@Override
	public double calcVolume() {
		return (1/3.0) * Math.pow(side, 2) * height;
	}

	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Pyramid Object
	 */
	@Override
	public double calcBaseArea() {
		return Math.pow(side, 2);
	}

	/**
	 * Custom toString method
	 * @return String defining Pyramid Object
	 */
	public String toString() {
		return String.format("%-18s", "Pyramid:") +
				String.format("%s %-10.2f", "s =", side) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}

}
