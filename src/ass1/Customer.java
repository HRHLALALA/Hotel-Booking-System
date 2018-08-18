package ass1;

public class Customer {
	private String name;
	private Booking booking;
	
	public Customer(String name) {
		this.name = name;
		this.booking = null;
	}
	/**
	 * @return booking information
	 */
	public Booking getBooking() {
		return booking;
	}
	/**
	 * @param booking booking information
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
}
