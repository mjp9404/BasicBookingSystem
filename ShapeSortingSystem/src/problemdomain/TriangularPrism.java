package problemdomain;
/**
 * Class for creating and defining Triangular Prism Object
 * Extends Abstract Prism Class
 * 
 *
 */
public class TriangularPrism extends Prism {
	
	/**
	 * No arg Triangular prism constructor
	 */
	public TriangularPrism() {
		super();
	}
	
	/**
	 * Triangular Prism constructor with assigned values
	 * @param height Height of Triangular Prism
	 * @param side Length of one Side of Triangular Prism
	 */
	public TriangularPrism(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}
	
	/**
	 * Retrieve Height of Triangular Prism Object
	 * @return height Height of Triangular Prism
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Triangular Prism Object
	 * @param height Height of Triangular Prism
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Retrieve Side length of Triangular Prism Object
	 * @return side Length of one Side of Triangular Prism
	 */
	public double getSide() {
		return side;
	}
	/**
	 * Side length Mutator for Triangular Prism Object
	 * @param side Length of one Side of Triangular Prism
	 */
	public void setSide(double side) {
		this.side = side;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Triangular Prism
	 */
	@Override
	public double calcVolume() {
		return ((Math.pow(side, 2) * Math.sqrt(3)) / 4)*height;
	}

	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Triangular Prism
	 */
	@Override
	public double calcBaseArea() {
		return ((Math.pow(side, 2) * Math.sqrt(3)) / 4);
	}
	
	/**
	 * Custom toString method
	 * @return String defining Triangular Prism
	 */
	public String toString() {
		return String.format("%-17s ","Triangular Prism:") + 
				String.format("%s %-10.2f", "s =", side) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}
}
