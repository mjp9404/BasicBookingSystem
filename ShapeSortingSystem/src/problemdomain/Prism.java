package problemdomain;

public abstract class Prism extends Shape {
	public double side;

	public Prism() {
		super();
	}

	public Prism(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}

	/**
	 * Retrieve Height of Cone Object
	 * @return height Height of Cone Object
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
	 * Retrieve Side of prism Object
	 * @return side Side of Prism Object
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Side Mutator
	 * @param side Length of Side of Prism Object
	 */
	public void setSide(double side) {
		this.side = side;
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
	 * Custom toString method
	 * @return String defining Prism Object
	 */
	@Override
	public String toString() {
		return "Prism [side=" + side + ", height=" + height + "]";
	}

}
