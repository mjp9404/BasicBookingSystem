package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {

	static final String WEEKDAY_ANY = "ANY";
	static final String WEEKDAY_SUNDAY = "SUNDAY";
	static final String WEEKDAY_MONDAY = "MONDAY";
	static final String WEEKDAY_TUESDAY = "TUESDAY";
	static final String WEEKDAY_WEDNESDAY = "WEDNESDAY";
	static final String WEEKDAY_THURSDAY = "THURSDAY";
	static final String WEEKDAY_FRIDAY = "FRIDAY";
	static final String WEEKDAY_SATURDAY = "SATURDAY";

	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */

	JTextField flightText;
	JTextField airlineText;
	JTextField dayText;
	JTextField timeText;
	JTextField costText;
	JTextField nameText;
	JTextField citizenshipText;

	JLabel flight;
	JLabel airline;
	JLabel day;
	JLabel time;
	JLabel cost;
	JLabel name;
	JLabel citizenship;

	JScrollPane scrollPane;

	JComboBox fromBox;
	JComboBox toBox;
	JComboBox dayBox;

	private ArrayList<Flight> flightsO = null;
	private ArrayList<String> airportO = null;
	Reservation reserve = null;

	String from_Flight;
	String to_Flight;
	String day_Flight;

	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		JPanel westPanel = createWestPanel();
		panel.add(westPanel, BorderLayout.WEST);
	}

	/**
	 * 
	 * @return JPanel that goes in West.
	 */

	private JPanel createWestPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		JTextArea textInput = new JTextArea();
		textInput.setBorder(BorderFactory.createLineBorder(Color.gray));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);
		panel.add(textInput);
		return panel;
	}

	/**
	 * 
	 * @return JPanel that goes in South.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel thirdtitle = new JLabel("Flight Finder", SwingConstants.CENTER);
		thirdtitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(thirdtitle, BorderLayout.NORTH);

		JPanel panelGrid = new JPanel();
		panelGrid.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;

		// Display From
		JLabel from = new JLabel("From: ", SwingConstants.RIGHT);
		from.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.weightx = 0.02;

		panelGrid.add(from, constraint);

		fromBox = new JComboBox();
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.weightx = 0.98;

		panelGrid.add(fromBox, constraint);

		// Display To
		JLabel to = new JLabel("To: ", SwingConstants.RIGHT);
		to.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.weightx = 0.02;

		panelGrid.add(to, constraint);

		toBox = new JComboBox();
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.weightx = 0.98;

		panelGrid.add(toBox, constraint);

		try {
			airportO = flightManager.getAirport();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String ao : airportO) {
			toBox.addItem(ao);
			fromBox.addItem(ao);
		}

		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.weightx = 0.95;
		panelGrid.add(toBox, constraint);

		// Display Day
		JLabel day = new JLabel("Day: ", SwingConstants.RIGHT);
		day.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.weightx = 0.02;

		panelGrid.add(day, constraint);

		String[] nameDay = { WEEKDAY_ANY, WEEKDAY_SUNDAY, WEEKDAY_MONDAY, WEEKDAY_THURSDAY, WEEKDAY_WEDNESDAY,
				WEEKDAY_THURSDAY, WEEKDAY_FRIDAY, WEEKDAY_SATURDAY };
		dayBox = new JComboBox(nameDay);
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.weightx = 0.98;

		panelGrid.add(dayBox, constraint);

		panel.add(panelGrid, BorderLayout.CENTER);
		JButton findFlight = new JButton("Find Flights");
		panel.add(findFlight, BorderLayout.SOUTH);
		findFlight.addActionListener(new ButtonListen());

		return panel;
	}

	/**
	 * 
	 * @return JPanel that goes in East.
	 */

	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel secondtitle = new JLabel("Reserve", SwingConstants.CENTER);
		secondtitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(secondtitle, BorderLayout.NORTH);

		JPanel panelGrid = new JPanel();
		panelGrid.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		// Display Flight
		flight = new JLabel("Flight: ", SwingConstants.RIGHT);
		flight.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 0;
		panelGrid.add(flight, constraint);

		flightText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 0;
		panelGrid.add(flightText, constraint);
		flightText.setEditable(false);

		// Display Airline
		airline = new JLabel("Airline: ", SwingConstants.RIGHT);
		airline.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 1;
		panelGrid.add(airline, constraint);

		airlineText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 1;
		panelGrid.add(airlineText, constraint);
		airlineText.setEditable(false);

		// Display Day
		day = new JLabel("Day: ", SwingConstants.RIGHT);
		day.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 2;
		panelGrid.add(day, constraint);

		dayText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 2;
		panelGrid.add(dayText, constraint);
		dayText.setEditable(false);

		// Display Time
		time = new JLabel("Time: ", SwingConstants.RIGHT);
		time.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 3;
		panelGrid.add(time, constraint);

		timeText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 3;
		panelGrid.add(timeText, constraint);
		timeText.setEditable(false);

		// Display cost
		cost = new JLabel("Cost: ", SwingConstants.RIGHT);
		cost.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 4;
		panelGrid.add(cost, constraint);

		costText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 4;
		panelGrid.add(costText, constraint);
		costText.setEditable(false);

		// Display Name
		name = new JLabel("Name: ", SwingConstants.RIGHT);
		name.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 5;
		panelGrid.add(name, constraint);

		nameText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 5;
		panelGrid.add(nameText, constraint);

		// Display Citizenship
		citizenship = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		citizenship.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 6;
		panelGrid.add(citizenship, constraint);

		citizenshipText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 6;
		panelGrid.add(citizenshipText, constraint);

		panel.add(panelGrid, BorderLayout.CENTER);

		JButton reserve = new JButton("Reserve");
		panel.add(reserve, BorderLayout.SOUTH);

		return panel;
	}

	public class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */

		@Override
		public void valueChanged(ListSelectionEvent e) {
			flightText.setText(flightsList.getSelectedValue().getCode());
			airlineText.setText(flightsList.getSelectedValue().getAirlineName());
			dayText.setText(flightsList.getSelectedValue().getWeekday());
			timeText.setText(flightsList.getSelectedValue().getTime());
			costText.setText("$" + flightsList.getSelectedValue().getCostPerSeat());
		}
	}

	public class ButtonListen implements ActionListener {
		/**
		 * Called when user click the buttons for from, to, and day.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {

			from_Flight = (String) fromBox.getSelectedItem();
			to_Flight = (String) toBox.getSelectedItem();
			day_Flight = (String) dayBox.getSelectedItem();

			try {
				flightsO = flightManager.getFlights();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			for (Flight f : flightsO) {
				if (day_Flight == (WEEKDAY_ANY)) {
					if (day_Flight == (WEEKDAY_ANY) && f.getFrom() == (from_Flight)&& f.getTo() == (to_Flight)) {
						flightsModel.addElement(f);
					}
					
				} 
				else if (f.getFrom()==(from_Flight) && f.getTo()==(to_Flight)&& f.getWeekday()==(day_Flight)) {
					flightsModel.addElement(f);
				}
			}
		}
	}

	private class ReserveButtonListener implements ActionListener {

		/**
		 * Called the user clicks reserve button to reserve a flight.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String errorMessage = "name and citizenship can't be empty";
			String correctMessage = "Reservation Created. Your code is ";

			String name = nameText.getText();
			String citizen = citizenshipText.getText();

			if (name.isEmpty() && citizen.isEmpty()) {
				JOptionPane.showMessageDialog(null, errorMessage);
			} else {
				JOptionPane.showMessageDialog(null, correctMessage + reserve.getCode());
			}

		}
	}
}
