package problemdomain;

/**
 * Class for creating and defining Octagonal Prism Object
 * Extends Abstract Prism Class
 * 
 *
 */
public class OctagonalPrism extends Prism {
	double side;
	
	/**
	 * No arg Octagonal prism constructor
	 */
	public OctagonalPrism() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Octagonal Prism constructor with assigned values
	 * @param height Height of Octagonal Prism
	 * @param side Length of one Side of Octagonal Prism
	 */
	public OctagonalPrism(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}

	/**
	 * Retrieve Height of Octagonal Prism Object
	 * @return height Height of Octagonal Prism
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Octagonal Prism Object
	 * @param height Height of Octagonal Prism
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Retrieve Side length of Octagonal Prism Object
	 * @return side Length of one Side of Octagonal Prism
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Side length Mutator for Octagonal Prism Object
	 * @param side Length of one Side of Octagonal Prism
	 */
	public void setSide(double side) {
		this.side = side;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Octagonal Prism
	 */
	@Override
	public double calcVolume() {
		return (2 * (1 + Math.sqrt(2)) * Math.pow(side, 2))*height;
	}

	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Octagonal Prism
	 */
	@Override
	public double calcBaseArea() {
		return (2 * (1 + Math.sqrt(2)) * Math.pow(side, 2));
	}

	/**
	 * Custom toString method
	 * @return String defining Octagonal Prism
	 */
	public String toString() {
		return String.format("%-17s ","Octagonal Prism:") + 
				String.format("%s %-10.2f", "s =", side) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}

}
