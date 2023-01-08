package sait.frms.problemdomain;

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	public Reservation() {
		super();
	}
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
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
	 * @return gets Flight code.
	 */
	public String getFlightCode() {
		return flightCode;
	}
	/**
	 * 
	 * @param flightCode sets flight Code.
	 */
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	/**
	 * 
	 * @return gets Airline name.
	 */
	public String getAirline() {
		return airline;
	}
	/**
	 * 
	 * @param airline sets airline name.
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
	/**
	 * 
	 * @return gets name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name sets the name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return gets user's citizenship.
	 */
	public String getCitizenship() {
		return citizenship;
	}
	/**
	 * 
	 * @param citizenship sets user's citizenship.
 	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	/**
	 * 
	 * @return gets cost.
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * 
	 * @param cost sets cost.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * 
	 * @return checks boolean results if it's active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * 
	 * @param active sets boolean results if it's active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}
	
}
