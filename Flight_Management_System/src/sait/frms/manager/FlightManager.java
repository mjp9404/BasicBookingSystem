package sait.frms.manager;

import java.io.*;
import java.util.*;


import javax.swing.JList;

import sait.frms.problemdomain.Flight;

public class FlightManager {

	static final String WEEKDAY_ANY = "ANY";
	static final String WEEKDAY_SUNDAY = "SUNDAY";
	static final String WEEKDAY_MONDAY = "MONDAY";
	static final String WEEKDAY_TUESDAY = "TUESDAY";
	static final String WEEKDAY_WEDNESDAY = "WEDNESDAY";
	static final String WEEKDAY_THURSDAY = "THURSDAY";
	static final String WEEKDAY_FRIDAY = "FRIDAY";
	static final String WEEKDAY_SATURDAY = "SATURDAY";

	private ArrayList<Flight> flights;
	private ArrayList<String> airport;
	
	
	
	private ArrayList<Flight> Nflights = new ArrayList<>();
	private final String FLIGHTS_PATH = "res/flights.csv";
	private final String AIRPORT_PATH = "res/airports.csv";
	private String codeString = "";

	public FlightManager() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		try {
			flights = new ArrayList<>();
			airport = new ArrayList<>();
			populateFlights();
			populateAirport();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @return gets airport information
	 * @throws FileNotFoundException if the file doesn't exist, throws Exception
	 */

	public ArrayList<String> getAirport() throws FileNotFoundException {
		return airport;
	}
	
	/**
	 * 
	 * @return gets Flight information
	 * @throws FileNotFoundException if the file doesn't exist, throws Exception
	 */
	
	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		return flights;
	}

	/**
	 * 
	 * @param code finds airport by code
	 * @return airport name
	 */
	public String findAirportByCode(String code) {
		for (int i = 0; i < airport.size(); i++) {
			if (airport.get(i) == (code)) {
				return airport.get(i + 1);
			}
		}
		return codeString;
	}
	/**
	 * 
	 * @param code finds a flight by code
	 * @return flight code
	 */

	public Flight findFlightByCode(String code)  {
		for (Flight flight : flights) {
			if (flight.getCode().equalsIgnoreCase(code)) {
				return flight;
			}
		}
		throw new RuntimeException("The code is invalid!");
	}
	/**
	 * 
	 * @param from gets departure of flight
	 * @param to gets arrival of flight
	 * @param weekday gets the day of flight
	 * @return finds flight
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		if (weekday == WEEKDAY_ANY) {
			for (Flight flight : flights) {
				if (flight.getFrom().equalsIgnoreCase(from) && flight.getTo().equalsIgnoreCase(to)) {
					Nflights.add(flight);
				}
			}
		} else if(weekday != WEEKDAY_ANY){
			for (Flight flight : flights) {
				if (flight.getFrom().equalsIgnoreCase(from) && flight.getTo().equalsIgnoreCase(to)
						&& flight.getWeekday().equalsIgnoreCase(weekday)) {
					Nflights.add(flight);
				}
			}
		}
		return Nflights;
	}

	/**
	 * 
	 * @throws FileNotFoundException if the file doesn't exist, throws Exception
	 */
	private void populateFlights() throws FileNotFoundException {
		Scanner input = new Scanner(new File(FLIGHTS_PATH));

		while (input.hasNext()) {
			String[] Ffield = input.nextLine().split(",");
			String code = Ffield[0];
			String from = Ffield[1];
			String to = Ffield[2];
			String weekday = Ffield[3];
			String time = Ffield[4];
			int flightChairs = Integer.parseInt(Ffield[5]);
			double costOfChair = Double.parseDouble(Ffield[6]);
			flights.add(new Flight(code, from, to, weekday, time, flightChairs, costOfChair));
		}
	}
	/**
	 * 
	 * @throws FileNotFoundException if the file doesn't exist, throws Exception
	 */
	private void populateAirport() throws FileNotFoundException {
		Scanner input = new Scanner(new File(AIRPORT_PATH));

		while (input.hasNext()) {
			String[] Afield = input.nextLine().split(",");
			String code = Afield[0];
			airport.add(code);
		}
	}

}
