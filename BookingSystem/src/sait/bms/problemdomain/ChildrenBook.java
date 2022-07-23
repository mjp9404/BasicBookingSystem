package sait.bms.problemdomain;

public class ChildrenBook extends Book {
	private String author;
	private String format;

	/**
	 * Creates a ChildrenBook object with default values of the super class
	 */
	public ChildrenBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a ChildrenBook object providing the isbn, callNumber, available,
	 * total, bookTitle, author, and format.
	 *
	 * @param isbn       isbnNumber for book
	 * @param callNumber callNumber for book
	 * @param available  available number of book
	 * @param total      total of book
	 * @param bookTitle  Title of the book
	 * @param author     The author of the book
	 * @param format     format of the book
	 */
	public ChildrenBook(long isbn, String callNumber, int available, int total, String bookTitle, String author,
			String format) {
		super(isbn, callNumber, available, total, bookTitle);
		this.author = author;
		this.format = format;

	}

	/**
	 * returns the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * sets the author.
	 *
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * returns the format.
	 *
	 * @return the format
	 */
	public String getFormat() {

		return format;
	}

	/**
	 * sets the format.
	 *
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * returns the book saved.
	 *
	 * @return the bookSaved
	 */
	public String bookSaved() {
		return getIsbn() + ";" + getCallNumber() + ";" + getAvailable() + ";" + getTotal() + ";" + getBookTitle() + ";"
				+ getAuthor() + ";" + getFormat();
	}

	@Override
	public String toString() {

		if (format.contains("P")) {
			format = "Picture Book";
		}
		if (format.contains("E")) {
			format = "Early Readers";
		}
		if (format.contains("C")) {
			format = "Chapter Book";
		}

		return "ISBN: \t" + getIsbn() + "\nCall Number:\t" + getCallNumber() + "\nAvailable:\t" + getAvailable()
				+ "\nTotal:\t" + getTotal() + "\nTitle:\t" + getBookTitle() + "\nAuthor:\t" + getAuthor()
				+ "\nFormat:\t" + format;
	}
}