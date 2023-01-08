package sait.frms.problemdomain;


public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	private char flight_first_letter;

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		super();
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	public Flight(String code, String from, String to, String weekday, String time, int seats, double costPerSeat) {
		super();
		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * 
	 * @return gets code.
	 */

	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code sets code.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return gets Airline name.
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * 
	 * @param airlineName sets Airline name.
	 */
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	/**
	 * 
	 * @return gets departure of airport.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * 
	 * @param from sets departure.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * 
	 * @return gets arrival.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * 
	 * @param to sets arrival.
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * 
	 * @return gets the day of weekday.
	 */
	public String getWeekday() {
		return weekday;
	}
	/**
	 * 
	 * @param weekday sets the day of weekday.
	 */
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	/**
	 * 
	 * @return gets time.
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 
	 * @param time sets time.
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 
	 * @return gets a seat.
	 */
	public int getSeats() {
		return seats;
	}
	/**
	 * 
	 * @param seats sets a seat.
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	/**
	 * 
	 * @return gets cost for per seat.
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
	/**
	 * 
	 * @param costPerSeat sets cost for per seat.
	 */
	public void setCostPerSeat(double costPerSeat) {
		this.costPerSeat = costPerSeat;
	}
	/**
	 * 
	 * @return boolean result if it's domestic.
	 */
	public boolean isDomestic() {
		if (flight_first_letter == 'Y') {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @param code parses codes from the code. 
	 */
	private void parseCode(String code) {

		if (code.substring(0, 1).equals("OA")) {
			this.airlineName = "Otto Airlines";
		}

		else if (code.substring(0, 1).equals("CA")) {
			this.airlineName = "Conned Air";
		}

		else if (code.substring(0, 1).equals("TB")) {
			this.airlineName = "Try a Bus Airways";
		}

		else if (code.substring(0, 1).equals("VW")) {
			this.airlineName = "Vertical Airways";
		}

	}

	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineName=" + airlineName + ", from=" + from + ", to=" + to + ", weekday="
				+ weekday + ", time=" + time + ", seats=" + seats + ", costPerSeat=" + costPerSeat + "]";
	}

}
