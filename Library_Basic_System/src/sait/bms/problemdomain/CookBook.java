package sait.bms.problemdomain;

public class CookBook extends Book {
	private String Publisher;
	private String Diet;

	/**
	 * Creates a CookBook object with default value of the super class
	 */
	public CookBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a CookBook object providing the isbn, callNumber, available, total,
	 * bookTitle, publisher, and diet.
	 *
	 * @param isbn       isbnNumber for book
	 * @param callNumber callNumber for book
	 * @param available  available number of book
	 * @param total      total of book
	 * @param bookTitle  Title of the book
	 * @param publisher  publisher of the book
	 * @param diet		 the type of book for diet
	 */
	public CookBook(long isbn, String callNumber, int available, int total, String bookTitle, String publisher,
			String diet) {
		super(isbn, callNumber, available, total, bookTitle);
		Publisher = publisher;
		Diet = diet;
	}

	/**
	 * returns the publisher.
	 *
	 * @return publisher
	 */
	public String getPublisher() {
		return Publisher;
	}

	/**
	 * sets the publisher.
	 *
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	/**
	 * returns the diet.
	 *
	 * @return the diet
	 */
	public String getDiet() {
		return Diet;
	}

	/**
	 * sets the diet.
	 *
	 * @param diet the diet to set
	 */
	public void setDiet(String diet) {
		Diet = diet;
	}

	/**
	 * returns the book saved.
	 *
	 * @return the bookSaved
	 */
	public String bookSaved() {
		return getIsbn() + ";" + getCallNumber() + ";" + getAvailable() + ";" + getTotal() + ";" + getBookTitle() + ";"
				+ getPublisher() + ";" + getDiet();
	}

	@Override
	public String toString() {
		if (Diet.contains("D")) {
			Diet = "Diabetic";
		}
		if (Diet.contains("V")) {
			Diet = "Vegetarian";
		}
		if (Diet.contains("G")) {
			Diet = "Gluten-free";
		}
		if (Diet.contains("I")) {
			Diet = "International";
		}
		if (Diet.contains("N")) {
			Diet = "None";
		}

		return "ISBN:\t" + getIsbn() + "\nCall Number:\t" + getCallNumber() + "\nAvailable:\t" + getAvailable()
				+ "\nTotal:\t" + getTotal() + "\nTitle:\t" + getBookTitle() + "\nPublisher:\t" + getPublisher()
				+ "\nDiet:\t" + Diet;

	}

}