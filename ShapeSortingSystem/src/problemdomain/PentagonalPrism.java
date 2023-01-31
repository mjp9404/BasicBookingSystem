package problemdomain;

/**
 * Class for creating and defining Pentagonal Prism Object
 * Extends Abstract Prism Class
 * 
 *
 */
public class PentagonalPrism extends Prism {
	
	/**
	 * No arg Pentagonal prism constructor
	 */
	public PentagonalPrism() {
		super();
	}
	
	/**
	 * Pentagonal Prism constructor with assigned values
	 * @param height Height of Pentagonal Prism
	 * @param side Length of one Side of Pentagonal Prism
	 */
	public PentagonalPrism(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}
	
	/**
	 * Retrieve Height of Pentagonal Prism Object
	 * @return height Height of Pentagonal Prism
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Pentagonal Prism Object
	 * @param height Height of Pentagonal Prism
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Retrieve Side length of Pentagonal Prism Object
	 * @return side Length of one Side of Pentagonal Prism
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Side length Mutator for Pentagonal Prism Object
	 * @param side Length of one Side of Pentagonal Prism
	 */
	public void setSide(double side) {
		this.side = side;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Pentagonal Prism
	 */
	@Override
	public double calcVolume() {
		return (5 * Math.pow(side, 2) * Math.tan(54) / 4)*height;
	}
	
	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Pentagonal Prism
	 */
	@Override
	public double calcBaseArea() {
		return (5 * Math.pow(side, 2) * Math.tan(54) / 4);
	}

	/**
	 * Custom toString method
	 * @return String defining Pentagonal Prism
	 */
	public String toString() {
		return String.format("%-17s ","Pentagonal Prism:") + 
				String.format("%s %-10.2f", "s =", side) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}
	
	

}
