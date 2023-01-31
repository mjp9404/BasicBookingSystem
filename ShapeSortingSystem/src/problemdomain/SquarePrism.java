package problemdomain;
/**
 * Class for creating and defining Square Prism Object
 * Extends Abstract Prism Class
 * 
 *
 */
public class SquarePrism extends Prism {

	/**
	 * No arg Square prism constructor
	 */
	public SquarePrism() {
		super();
	}

	/**
	 * Square Prism constructor with assigned values
	 * @param height Height of Square Prism
	 * @param side Length of one Side of Square Prism
	 */
	public SquarePrism(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}

	/**
	 * Retrieve Height of Square Prism Object
	 * @return height Height of Square Prism
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Height Mutator for Square Prism Object
	 * @param height Height of Square Prism
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Retrieve Side length of Square Prism Object
	 * @return side Length of one Side of Square Prism
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Side length Mutator for Square Prism Object
	 * @param side Length of one Side of Square Prism
	 */
	public void setSide(double side) {
		this.side = side;
	}

	/**
	 * Method to calculate volume from height and side length
	 * @return Volume of Square Prism
	 */
	@Override
	public double calcVolume() {
		return Math.pow(side, 2)*height;
	}
	
	/**
	 * Method to calculate Base Area from side length
	 * @return Base Area of Square Prism
	 */
	@Override
	public double calcBaseArea() {
		return Math.pow(side, 2);
	}

	/**
	 * Custom toString method
	 * @return String defining Square Prism
	 */
	public String toString() {
		return String.format("%-17s ","Square Prism:") + 
				String.format("%s %-10.2f", "s =", side) + 
				String.format("%s %-10.2f", "h =", height) + 
				String.format("%s %-15.2f", "ba =", calcBaseArea()) +
				String.format("%s %-15.2f", "v =", calcVolume());
	}


}
