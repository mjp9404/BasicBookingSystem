package sait.bms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import sait.bms.*;

import sait.bms.problemdomain.*;

/**
 * Manager class to display the info about the different type of books in many
 * search ways
 *
* @author Jonathan Ryan Muriel
* @author Minjong Park
* @author Majd Alshahaf
 */
public class Manager {

	private static ArrayList<Book> books = new ArrayList<>();
	static Scanner scn = new Scanner(System.in);

	/**
	 * reads the file and calls the methods.
	 *
	 * @throws IOException if it has a failure for input & output
	 */
	public Manager() throws IOException {
		String PATH = "res/books.txt";
		loadIsbnFromFile(books, PATH);

		int option = 0;

		while (option != 5) {

			option = displayMenu(scn);
			scn.nextLine(); // This just removes the \n generated

			switch (option) {
			case 1: {
				checkOut();
				break;
			}
			case 2: {
				bookTitle();
				break;
			}
			case 3: {
				bookType();
				break;
			}
			case 4: {
				randomBook();
				break;
			}
			case 5: {
				System.out.println("Bye");
				savedFile(books, PATH);
				System.exit(0);
			}
			default: {
				System.out.println("Invalid input.");
				break;
			}
			}
		}
	}

	/**
	 * loads the isbn from the file and reads the last digit of the isbn.
	 *
	 * @param books the array list
	 * @param PATH  the file path
	 * @throws FileNotFoundException if the file was not found
	 */
	public static void loadIsbnFromFile(ArrayList<Book> books, String PATH) throws FileNotFoundException {
		File file = new File(PATH);
		Scanner in = new Scanner(file);

		while (in.hasNext()) {

			String line = in.nextLine();
			String[] fields = line.split(";");

			char lastDigitOfIsbn = fields[0].charAt(fields[0].length() - 1);
			Book b;

			switch (lastDigitOfIsbn) {
			case '0':
			case '1':
				b = new ChildrenBook(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6]);
				books.add(b);
				break;
			case '2':
			case '3':
				b = new CookBook(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6]);
				books.add(b);
				break;
			case '4':
			case '5':
			case '6':
			case '7':
				b = new PaperBack(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], Integer.parseInt(fields[6]), fields[7]);
				books.add(b);
				break;
			case '8':
			case '9':
				b = new Periodical(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5]);
				books.add(b);
				break;
			}
		}

		in.close();
	}

	/**
	 * display the menu for the user to choose the way of searching.
	 *
	 * @param scn the scanner object
	 * @return select from the user input
	 */
	public static int displayMenu(Scanner scn) {

		int select = 0;

		System.out.println("Welcome in ABC Book Company: How May We Assist You?");
		System.out.println("1\tCheckout Book");
		System.out.println("2\tFind Books by Title");
		System.out.println("3\tDisplay Books by Type");
		System.out.println("4\tProduce Random Book List");
		System.out.println("5\tSave & Exit");
		System.out.println("Enter option: ");
		select = scn.nextInt();
		return select;

	}

	/**
	 * search for the book type using isbn numebr
	 */
	public static void checkOut() {

		System.out.print("Enter ISBN of Book: ");
		long input = scn.nextLong();
		boolean found = false;

		for (Book bc : books) {
			if (bc.getIsbn() == input) {
				if (bc.getAvailable() > 0) {
					System.out.println("The book \"" + bc.getBookTitle() + "\" has been checked out.");
					System.out.println("It can be located using a call number: " + bc.getCallNumber());
					System.out.println();
					
					bc.setAvailable(bc.getAvailable()-1);
					found = true;
				} else if (bc.getAvailable() == 0) {
					System.out.println("Book Not Available");
					System.out.println();
					found = true;
				}
			}
		}

		if (!found) {
			System.out.println("Invalid ISBN Number");
			System.out.println();
		}
	}

	/**
	 * search for the book type by the title
	 *
	 * @throws FileNotFoundException if the file was not found
	 */
	public static void bookTitle() throws FileNotFoundException {
		boolean found = false;
		System.out.print("Enter title to search for: ");
		String searchArg = scn.nextLine().toLowerCase();
		for (Book bc : books) {
			if (bc.getBookTitle().toLowerCase().contains(searchArg.toLowerCase())) {
				System.out.println(bc);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Invalid input");
			System.out.println();
		}

	}

	/**
	 * search for the book type by entering the book type
	 */
	public static void bookType() {
		Scanner input = new Scanner(System.in);
		int option;
		String freq, diet, format, genre;
		String type = "";
		String trial = "";

		System.out.println("1\tChildren's Book");
		System.out.println("2\tCookbooks");
		System.out.println("3\tPaperbacks");
		System.out.println("4\tPeriodicals");
		System.out.println("Enter type of book: ");
		option = scn.nextInt();

		switch (option) {
		case 1:
			System.out.println("P for Picture book, E for Early Readers, or C for Chapter book. ");
			type = scn.next().toUpperCase();

			for (Book b : books) {

				if (b instanceof ChildrenBook) {
					format = (((ChildrenBook) b).getFormat());
					if (format.equals(type)) {
						System.out.println(b.toString());
						System.out.println();
					}
				}
			}
			break;

		case 2:
			System.out.println(
					"D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, or N for None.");
			type = scn.next().toUpperCase();

			for (Book b : books) {

				if (b instanceof CookBook) {
					diet = (((CookBook) b).getDiet());
					if (diet.equals(type)) {
						System.out.println(b.toString());
						System.out.println();
					}
				}
			}
			break;

		case 3:
			System.out.println(
					"A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, or S for Science Fiction.");
			type = scn.next().toUpperCase();

			for (Book b : books) {

				if (b instanceof PaperBack) {
					genre = (((PaperBack) b).getGenre());
					if (genre.equals(type)) {
						System.out.println(b.toString());
						System.out.println();
					}
				}

			}
			break;
		case 4:
			System.out.println(
					"Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Biweekly, or Q for Quarterly): ");
			type = scn.next().toUpperCase();

			for (Book b : books) {

				if (b instanceof Periodical) {
					freq = (((Periodical) b).getFrequency());
					if (freq.equals(type)) {
						System.out.println(b.toString());
						System.out.println();
					}
				}

			}
			break;
		}

	}

	/**
	 * generate a random book to be displayed
	 */
	public static void randomBook() {

		int option;
		System.out.println("Enter number of books: ");
		option = scn.nextInt();
		Collections.shuffle(books);
		for (int i = 0; i < option; i++) {
			System.out.println(books.get(i));
			System.out.println();
		}
	}

	/**
	 * prints the saved file
	 *
	 * @param books the array list
	 * @param PATH  the file path
	 * @throws FileNotFoundException if the file was not found
	 */
	private void savedFile(ArrayList<Book> books, String PATH) throws FileNotFoundException {
		PrintWriter changedFile = new PrintWriter(PATH);
		for (Book b : books) {
			changedFile.print(b.bookSaved());
		}
		changedFile.close();
	}

}