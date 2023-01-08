package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import sait.frms.gui.FlightsTab.MyListSelectionListener;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	private JList<Reservation> reservationsList;

	private DefaultListModel<Reservation> reservationModel;
	
	private ArrayList<Reservation> reserveRecord;
	private ArrayList<Flight> flights;
	Reservation reserveRecord2 = new Reservation();
	/**
	 * Creates the components for reservations tab.
	 */
	

	String[] statusString = { "Active", "Inactive" };

	JTextField codeText;
	JTextField flightText;
	JTextField airlineText;
	JTextField costText;
	JTextField nameText;
	JTextField citizenshipText;

	JTextField codeInput;
	JTextField airlineInput;
	JTextField nameInput;
	JComboBox statusBox;

	public ReservationsTab(ReservationManager reservationManager) {
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

		// Display code
		JLabel code = new JLabel("Code: ", SwingConstants.RIGHT);
		code.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 0;
		panelGrid.add(code, constraint);

		codeText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 0;
		panelGrid.add(codeText, constraint);
		codeText.setEditable(false);

		// Display Flight
		JLabel flight = new JLabel("Flight: ", SwingConstants.RIGHT);
		flight.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 1;
		panelGrid.add(flight, constraint);

		flightText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 1;
		panelGrid.add(flightText, constraint);
		flightText.setEditable(false);

		// Display Airline
		JLabel airline = new JLabel("Airline: ", SwingConstants.RIGHT);
		airline.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 2;
		panelGrid.add(airline, constraint);

		airlineText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 2;
		panelGrid.add(airlineText, constraint);
		airlineText.setEditable(false);

		// Display cost
		JLabel cost = new JLabel("Cost: ", SwingConstants.RIGHT);
		cost.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 3;
		panelGrid.add(cost, constraint);

		costText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 3;
		panelGrid.add(costText, constraint);
		costText.setEditable(false);

		// Display Name
		JLabel name = new JLabel("Name: ", SwingConstants.RIGHT);
		name.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 4;
		panelGrid.add(name, constraint);

		nameText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 4;
		panelGrid.add(nameText, constraint);

		// Display Citizenship
		JLabel citizenship = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		citizenship.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 5;
		panelGrid.add(citizenship, constraint);

		citizenshipText = new JTextField(15);
		constraint.gridx = 1;
		constraint.gridy = 5;
		panelGrid.add(citizenshipText, constraint);

		// Display Status
		JLabel status = new JLabel("Status: ", SwingConstants.RIGHT);
		status.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 6;
		panelGrid.add(status, constraint);

		statusBox = new JComboBox(statusString);
		constraint.gridx = 1;
		constraint.gridy = 6;
		panelGrid.add(statusBox, constraint);

		panel.add(panelGrid, BorderLayout.CENTER);

		JButton update = new JButton("Update");
		panel.add(update, BorderLayout.SOUTH);
		
		update.addActionListener(new updateListener());
		return panel;
	}
	
	/**
	 * 
	 * @return JPanel that goes in South.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel thirdtitle = new JLabel("Search", SwingConstants.CENTER);
		thirdtitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(thirdtitle, BorderLayout.NORTH);

		JPanel panelGrid = new JPanel();
		panelGrid.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;

		// Input Code
		JLabel code = new JLabel("Code: ");
		code.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 0;

		panelGrid.add(code, constraint);

		codeInput = new JTextField(80);
		constraint.gridx = 1;
		constraint.gridy = 0;
		panelGrid.add(codeInput, constraint);

		// Input Airline
		JLabel airline = new JLabel("Airline: ", SwingConstants.RIGHT);
		airline.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 1;

		panelGrid.add(airline, constraint);

		airlineInput = new JTextField(80);
		constraint.gridx = 1;
		constraint.gridy = 1;
		panelGrid.add(airlineInput, constraint);

		// Input Name
		JLabel name = new JLabel("Name: ", SwingConstants.RIGHT);
		name.setFont(new Font("serif", Font.PLAIN, 20));
		constraint.gridx = 0;
		constraint.gridy = 2;

		panelGrid.add(name, constraint);

		nameInput = new JTextField(80);
		constraint.gridx = 1;
		constraint.gridy = 2;
		panelGrid.add(nameInput, constraint);

		panel.add(panelGrid, BorderLayout.CENTER);
		JButton findReservation = new JButton("Find Reservations");
		panel.add(findReservation, BorderLayout.SOUTH);
		
		findReservation.addActionListener(new ButtonListener());

		return panel;
	}
	
	/**
	 * 
	 * @return JPanel that goes in Center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		JTextArea textInput = new JTextArea();
		textInput.setBorder(BorderFactory.createLineBorder(Color.gray));

		reservationModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationModel);

		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane;
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		scrollPane = new JScrollPane(this.reservationsList);
		panel.add(scrollPane);
		panel.add(textInput);
		return panel;

	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	public class MyListSelectionListener implements ActionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			codeText.setText(reservationsList.getSelectedValue().getCode());
			flightText.setText(reservationsList.getSelectedValue().getFlightCode());
			airlineText.setText(reservationsList.getSelectedValue().getAirline());
			codeText.setText("$" + reservationsList.getSelectedValue().getCost());
			nameText.setText(reservationsList.getSelectedValue().getName());
			citizenshipText.setText(reservationsList.getSelectedValue().getCitizenship());
			
			boolean active = reservationsList.getSelectedValue().isActive();
			 
			if (active == true) {
				statusBox.setSelectedItem(statusString[0]);
			}
			else if (active == false) {
				statusBox.setSelectedItem(statusString[1]);
			} 
		}

	}

	private class ButtonListener implements ActionListener {
		/**
		 * Called when user types reservation code, airline name, and user name.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String reserveCode = codeText.getText().toUpperCase();
			String airline = airlineInput.getText().toUpperCase();
			String name = nameText.getText().toUpperCase();
			
			if (airline.isEmpty() && name.isEmpty()) {
				reserveRecord2 = reservationManager.findReservationByCode(reserveCode);
				reservationModel.addElement(reserveRecord2);
			}
			else {
				reserveRecord = reservationManager.findReservations(reserveCode, airline, name);
			}

		}

	}
	
	private class updateListener implements ActionListener {
		String reserveCode = codeText.getText().toUpperCase();
		String flight = flightText.getText().toUpperCase();
		String name = nameText.getText().toUpperCase();
		String citizen = citizenshipText.getText().toUpperCase();
		
		String errorMessage = "name and citizenship can't be empty";
		String correctMessage = "Successfully Update!";
		
		
		/**
		 * Called when user update the reservation information
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (!name.isEmpty() && !citizen.isEmpty()) {
				
				reserveRecord2 = reservationManager.findReservationByCode(reserveCode);
				reserveRecord2.setName(name);
				reserveRecord2.setCitizenship(citizen);
				
				try {
					JOptionPane.showMessageDialog(null, correctMessage);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				if (statusBox.getSelectedItem() == statusString[0] ) {
					reserveRecord2.setActive(true);
				}
				else if (statusBox.getSelectedItem() == statusString[1]) {
					reserveRecord2.setActive(false);
					for (Flight fo : flights) {
						if (fo.getCode().toUpperCase().equals(flight)) {
							fo = new Flight(fo.getCode(), fo.getAirlineName(), fo.getFrom(), fo.getTo(), fo.getWeekday(), 
									fo.getTime(), fo.getSeats(), fo.getCostPerSeat());
						}
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, errorMessage);
			}
		}
	}

}
