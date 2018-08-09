import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hotel {
	private String name;
	private ArrayList<Room> rooms;
	private ArrayList<Booking> bookings;

	
	public Hotel(String name) {
		this.name = name;
		this.rooms = new ArrayList<Room>();
		this.bookings = new ArrayList<Booking>();
	}
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public String getName() {
		return this.name;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	public void addBookings(Booking booking) {
		this.bookings.add(booking);
	}
	public void removeBookings(Booking booking) {
		this.bookings.remove(booking);
	}

	public ArrayList<Room> assignRooms(int nTriple,int nDouble,int nSingle,LocalDate arrivalTime,int nights){
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for(Room r:this.rooms){
			if(r.isAvailable(arrivalTime, nights)) {
				switch(r.getCapacity()) {
				case 1: 
					if(count1 < nSingle) {
						availableRooms.add(r);
						count1++;
					}
				case 2: 
					if(count2 < nDouble) {
						availableRooms.add(r);
						count2++;
					}
				case 3:
					if(count1 < nTriple) {
						availableRooms.add(r);
						count3++;
					}
				}
			}
		}
		if(count1!=nSingle || count2!=nDouble || count3 != nTriple) return null;
		return availableRooms;
	}
	public String convertMonth(int month) {
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
		String output="";
		for(Room r:this.rooms) {
			output = output+this.name+ " ";
			output = output+r.getId();
			for(Booking b:r.getBookings()) {
				int Month = b.getArrivalTime().getMonthValue();
				output = output + " "+ this.convertMonth(Month)+" "+b.getArrivalTime().getDayOfMonth()+" "+b.getNights();
			}
			output +="\n";
		}	
		return output;
		
	}
	
		

}
