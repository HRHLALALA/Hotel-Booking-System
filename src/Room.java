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
	public Hotel getHotel() {
		return this.hotel;
	}


	public int getId() {
		return id;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
		
	}
	public int getCapacity() {
		return capacity;
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	public int findMin(int flag) { //find the index of Stock array with min price from the flag position
		int min =flag;
		for(int i=flag+1;i<this.bookings.size();i++){
			Booking a = this.bookings.get(i);
			Booking b = this.bookings.get(min);
			if(b.getArrivalTime().isAfter(a.getArrivalTime())) min = i;
		}
		return min;
	}
	
	public void sortBookings() { // selectionSort for Stock
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
	
	public void addBooking(Booking booking) {
		this.bookings.add(booking);

	}
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
	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}


	@Override
	public String toString() {
		return "Room:\n hotel=" + hotel.getName() + "\n id=" + id + "\n capacity=" + capacity + "\n";
	}



}
