package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;

public class ReservationManager {

	private static final String SAVE_TO_FILE = "res/reservation.dat";
	private ArrayList<Reservation> reservations;
	private final String MODE = "rw";
	private RandomAccessFile raf;
	final int RERSERVATION_BYTE_SIZE = 331;

	public ReservationManager() {
		super();
	}

	/**
	 *
	 * @param flight flight code
	 * @param name customer's name
	 * @param citizenship customer's citizenship
	 * @return reservation object
	 * @throws IOException If the file doesn't exist, throws exception
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException {
		if (this.getAvailableSeats(flight) > 0) {

			if (name == "") {
				System.out.println("Please enter name");
				return null;
			}

			else if (citizenship == "") {
				System.out.println("Please enter citizenship");
				return null;
			}

			else {
				String generatedCode = generateReservationCode(flight);
				System.out.println("Reservation created. Your code is " + generatedCode + ".");

				flight = new Flight(flight.getCode(), flight.getFrom(), flight.getTo(), flight.getWeekday(),
						flight.getTime(), generatedCode, this.getAvailableSeats(flight) - 1, flight.getCostPerSeat());
				Reservation rsv = new Reservation(generatedCode, flight.getCode(), flight.getAirlineName(), name,
						citizenship, flight.getCostPerSeat(), true);

				reservations.add(rsv);
				persist();
				return rsv;

			}

		}

		else {
			System.out.println("This flight is not available");
			return null;
		}
	}

	/**
	 *
	 * @param code reservation code
	 * @param airline airline name
	 * @param name customer's name
	 * @return found reservation results
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> findMatchReservation = new ArrayList<>();

		for (Reservation r : reservations) {
			if (r.getCode().toUpperCase().equals(code) || r.getAirline().toUpperCase().equals(airline)
					|| r.getName().toUpperCase().equals(name)) {
				findMatchReservation.add(r);
			}
		}
		return findMatchReservation;
	}

	/**
	 * Find reservation using the reservation code
	 * 
	 * @param code reservation code
	 * @return found reservation object
	 */
	public Reservation findReservationByCode(String code) {
		for (Reservation r : reservations) {
			if (r.getCode().equals(code)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Saves Reservation objects to binary file
	 * 
	 * @throws IOException if the file cannot be saved
	 */
	public void persist() throws IOException {
		for (int i = 0; i < reservations.size(); i++) {
			String generatedCode = reservations.get(i).getCode();
			raf.writeUTF(generatedCode);

			String flightCode = reservations.get(i).getFlightCode();
			raf.writeUTF(flightCode);

			String airline = reservations.get(i).getAirline();
			raf.writeUTF(airline);

			String name = reservations.get(i).getName();
			raf.writeUTF(name);

			String citizenship = reservations.get(i).getCitizenship();
			raf.writeUTF(citizenship);

		}
	}

	/**
	 * Get the available seats
	 * 
	 * @param flight gets seat for flight
	 * @return available seats
	 */
	private int getAvailableSeats(Flight flight) {
		return flight.getSeats();

	}

	/**
	 *
	 * @param flight customer who wants to reserve a flight
	 * @return generated reservation code
	 */
	private String generateReservationCode(Flight flight) {
		String reservationCode = "";
		int number = (int) (Math.random() * 9000 + 1000);
		if (flight.getFrom().startsWith("Y") && flight.getTo().startsWith("Y")) {
			reservationCode = "D" + number;
		} else {
			reservationCode = "I" + number;
		}
		return reservationCode;

	}

	/**
	 * Read binary file to save records
	 * 
	 * @throws IOException if the file doesn't exist
	 */
	private void populateFromBinary() throws IOException {
		DataInputStream in = null;

		in = new DataInputStream(new FileInputStream(SAVE_TO_FILE));
		boolean endOfFile = false;

		while (!endOfFile) {
			String generatedCodeBinary = in.readUTF().trim();
			String flightCodeBinary = in.readUTF().trim();
			String airLineBinary = in.readUTF().trim();
			String nameBinary = in.readUTF().trim();
			String citizenshipBinary = in.readUTF().trim();
			double costPerSeat = in.readDouble();
			boolean isActive = in.readBoolean(); // boolean part
			reservations.add(new Reservation(generatedCodeBinary, flightCodeBinary, airLineBinary, nameBinary,
					citizenshipBinary, costPerSeat, isActive));
		}
	}

}
