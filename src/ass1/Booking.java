package ass1;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
	private Customer customer;
	private LocalDate arrivalTime;
	private int nights;
	private Hotel hotel;
	private ArrayList<Room> rooms;
	
	public Booking(Customer customer,LocalDate arrivalTime,int nights,Hotel hotel) {
		this.customer = customer;
		this.arrivalTime = arrivalTime;
		this.nights = nights;
		this.rooms = new ArrayList<Room>();getClass();
		this.hotel = hotel;
	}
	/**
	 * @return customer information
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @return arrival date
	 */

	public LocalDate getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * Calculate the leave date
	 * @return the leave date
	 */
	public  LocalDate getLeaveTime() {
		return arrivalTime.plusDays(nights);
	}
	/**
	 * @return number of nights
	 */
	public int getNights() {
		return nights;
	}
	/**
	 * @return all rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	/**
	 * put all booked rooms into booking
	 * @param rooms
	 */
	public void setRooms(ArrayList<Room> rooms) {
		for(Room r:rooms) {
			r.addBooking(this);
		}
		this.rooms = rooms;
	}
	/**
	 * @return hotel information
	 */
	public Hotel getHotel() {
		return hotel;
	}

	@Override
	public String toString() {
		String out = this.customer.getName()+" "+this.hotel.getName()+" ";
		for(Room r:this.rooms) {
			out = out + r.getId()+" ";
		}
		return out;
	}

	
}
