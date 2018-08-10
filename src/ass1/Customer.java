package ass1;

public class Customer {
	private String name;
	private Booking booking;
	public Customer(String name) {
		this.name = name;
		this.booking = null;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public String getName() {
		return name;
	}

	
}
