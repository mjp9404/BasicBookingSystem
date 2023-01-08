package sait.bms.problemdomain;

public class PaperBack extends Book {
	private String author;
	private int year;
	private String genre;

	/**
	 * Creates a PaperBack object with default values of the super class
	 */
	public PaperBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a ChildrenBook object providing the isbn, callNumber, available,
	 * total, bookTitle, author, year, and genre.
	 *
	 * @param isbn       isbnNumber for book
	 * @param callNumber callNumber for book
	 * @param available  available number of book
	 * @param total      total of book
	 * @param bookTitle  Title of the book
	 * @param author	 The author of the book
	 * @param year		 The year of the book
	 * @param genre		 the genre of the book
	 */
	public PaperBack(long isbn, String callNumber, int available, int total, String bookTitle, String author, int year,
			String genre) {
		super(isbn, callNumber, available, total, bookTitle);
		this.author = author;
		this.year = year;
		this.genre = genre;
	}

	/**
	 * returns author.
	 *
	 * @return author
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
	 * returns the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets the year.
	 *
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * returns the genre.
	 *
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * sets the genre.
	 *
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * returns the book saved.
	 *
	 * @return the bookSaved
	 */
	public String bookSaved() {
		return getIsbn() + ";" + getCallNumber() + ";" + getAvailable() + ";" + getTotal() + ";" + getBookTitle() + ";"
				+ getAuthor() + ";" + getYear() + ";" + getGenre();
	}

	@Override
	public String toString() {
		if (genre.contains("A")) {
			genre = "Adventure";
		}
		if (genre.contains("C")) {
			genre = "Classic";
		}
		if (genre.contains("D")) {
			genre = "Drama";
		}
		if (genre.contains("E")) {
			genre = "Education";
		}
		if (genre.contains("F")) {
			genre = "Fantasy";
		}
		if (genre.contains("S")) {
			genre = "Science Fiction";
		}

		return "ISBN:\t" + getIsbn() + "\nCall Number:\t" + getCallNumber() + "\nAvailable:\t" + getAvailable()
				+ "\nTotal:\t" + getTotal() + "\nTitle:\t" + getBookTitle() + "\nAuthors:\t" + getAuthor() + "\nYear:\t"
				+ getYear() + "\nGenre:\t" + genre;
	}
}