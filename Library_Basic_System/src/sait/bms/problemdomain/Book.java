package sait.bms.problemdomain;

public class Book {
	private long isbn;
	private String callNumber;
	private int available;
	private int total;
	private String bookTitle;
	private String saved;

	/**
	 * returns the saved.
	 *
	 * @return saved
	 */
	public String getSaved() {
		return saved;
	}

	/**
	 * sets the saved.
	 *
	 * @param saved the saved to set
	 */
	public void setSaved(String saved) {
		this.saved = saved;
	}

	/**
	 * Creates a Book object with default values
	 */
	public Book() {
		super();
	}

	/**
	 * Creates a Book object providing the isbn, callNumber, available, total, and
	 * bookTitle.
	 *
	 * @param isbn       isbnNumber for book
	 * @param callNumber callNumber for book
	 * @param available  available number of book
	 * @param total      total of book
	 * @param bookTitle  Title of the book
	 */
	public Book(long isbn, String callNumber, int available, int total, String bookTitle) {
		super();
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.available = available;
		this.total = total;
		this.bookTitle = bookTitle;
	}

	/**
	 * returns the isbn.
	 *
	 * @return the isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * sets the isbn.
	 *
	 * @param isbn the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * returns the call number.
	 *
	 * @return the callNumber
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * sets the call number.
	 *
	 * @param callNumber the callNumber to set
	 */
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	/**
	 * returns the available.
	 *
	 * @return the available
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * sets the available.
	 *
	 * @param available the available to set
	 */
	public void setAvailable(int available) {
		this.available = available;
	}

	/**
	 * returns the total.
	 *
	 * @return total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * sets the total.
	 *
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * returns the book title.
	 *
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * sets the book title.
	 *
	 * @param bookTitle
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", callNumber=" + callNumber + ", available=" + available + ", total=" + total
				+ ", bookTitle=" + bookTitle + "]";
	}

	/**
	 * returns book saved.
	 *
	 * @return bookSaved
	 */
	public String bookSaved() {
		return saved;
	}

}