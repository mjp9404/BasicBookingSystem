package sait.bms.problemdomain;

public class Periodical extends Book {
	private String frequency;

	/**
	 * Creates a Periodical object with default values of the super class
	 */
	public Periodical() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a ChildrenBook object providing the isbn, callNumber, available,
	 * total, bookTitle, and frequency.
	 *
	 * @param isbn       isbnNumber for book
	 * @param callNumber callNumber for book
	 * @param available  available number of book
	 * @param total      total of book
	 * @param bookTitle  Title of the book
	 * @param frequency	 The frequency of the book
	 */
	public Periodical(long isbn, String callNumber, int available, int total, String bookTitle, String frequency) {
		super(isbn, callNumber, available, total, bookTitle);
		this.frequency = frequency;
	}

	/**
	 * returns the frequency.
	 *
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * sets the frequency.
	 *
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * returns the book saved.
	 *
	 * @return the bookSaved
	 */
	public String bookSaved() {
		return getIsbn() + ";" + getCallNumber() + ";" + getAvailable() + ";" + getTotal() + ";" + getBookTitle() + ";"
				+ getFrequency();
	}

	@Override
	public String toString() {

		if (frequency.contains("B")) {
			frequency = "Bi-weekly";
		}
		if (frequency.contains("D")) {
			frequency = "Daily";
		}
		if (frequency.contains("M")) {
			frequency = "Monthly";
		}
		if (frequency.contains("Q")) {
			frequency = "Quarterly";
		}
		if (frequency.contains("W")) {
			frequency = "Weekly";
		}

		return "ISBN:\t" + getIsbn() + "\nCall Number:\t" + getCallNumber() + "\nAvailable:\t" + getAvailable()
				+ "\nTotal:\t" + getTotal() + "\nTitle:\t" + getBookTitle() + "\nFrequency:\t" + frequency;

	}
}