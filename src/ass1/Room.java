package ass1;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Room {
	private Hotel hotel;
	private int id;
	private int capacity;
	private ArrayList<Booking> bookings;
	
	public Room(int id,int capacity) {
		this.id = id;
		this.capacity = capacity;
		this.bookings = new ArrayList<Booking>();
	}
	/**
	 * @return the hotel information
	 */
	public Hotel getHotel() {
		return this.hotel;
	}
	/**
	 * @return id of the room
	 */

	public int getId() {
		return id;
	}
	/**
	 * @param hotel which contains this room
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	/**
	 * @return The capacity of the room
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @return list of bookings
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	/**
	 * help function to find the index of the min arrival date from the flag index
	 * @param flag start from which index
	 * @return the index of min arrival time
	 */
	private int findMin(int flag) { //find the index of Stock array with min price from the flag position
		int min =flag;
		for(int i=flag+1;i<this.bookings.size();i++){
			Booking a = this.bookings.get(i);
			Booking b = this.bookings.get(min);
			if(b.getArrivalTime().isAfter(a.getArrivalTime())) min = i;
		}
		return min;
	}
	/**
	 * a help function for sorting the order of the booking with available time
	 */
	private void sortBookings() { // selectionSort for Stock
		for(int flag=0;flag<this.bookings.size();flag++) {
			int min = findMin(flag);
			//swap the first with min
			if(min==flag) continue;
			Booking swap = this.bookings.get(min);
			this.bookings.remove(min);
			this.bookings.add(min,this.bookings.get(flag));
			this.bookings.remove(flag);
			this.bookings.add(flag,swap);
		}
	}
	/**
	 * @param booking booking information
	 */
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
		this.sortBookings();

	}
	/**
	 * Find if the room is available with the booking days
	 * @return true if is available, otherwise false
	 */
	public boolean isAvailable(LocalDate arrivalTime, int nights) {
		LocalDate leavingDate = arrivalTime.plusDays(nights);
		if (this.bookings.size()==0) return true;
		LocalDate firstBookingDay = this.bookings.get(0).getArrivalTime();
		if (!leavingDate.isAfter(firstBookingDay)) return true;
		for (int i=0;i<this.bookings.size();i++) {
			Booking b = this.bookings.get(i);
			if(!arrivalTime.isBefore(b.getLeaveTime())) {
				if(i+1!=this.bookings.size()) {
					b = this.bookings.get(i+1);
					if(!leavingDate.isAfter(b.getArrivalTime())) {
						return true;
					}
				}
				else{
					return true;
				}
				
			}
		}
		return false;
	}
	/**
	 *  remove a booking
	 * @param booking the booking information
	 */
	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}
	/**
	 * convert the month from integer to string
	 * @param month integer month
	 * @return string month
	 */
	private String convertMonth(int month) {
		switch (month){
		case 1: return "Jan";
		case 2: return "Feb";
		case 3: return "Mar";
		case 4: return "Apr";
		case 5: return "May";
		case 6: return "Jun";
		case 7: return "Jul";
		case 8: return "Aug";
		case 9: return "Sep";
		case 10: return "Oct";
		case 11: return "Nov";
		case 12: return "Dec";
		}
		return "";
	}
	
	@Override
	public String toString() {
		String output = this.getHotel().getName()+" "+this.id+" ";
		for(Booking b :this.bookings) {
			int month = b.getArrivalTime().getMonthValue();
			output = output + this.convertMonth(month)+" "+b.getArrivalTime().getDayOfMonth()+ " "+b.getNights()+" ";
		}
		return output;
	}



}
