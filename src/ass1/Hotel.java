package ass1;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hotel {
	private String name;
	private ArrayList<Room> rooms;
	
	public Hotel(String name) {
		this.name = name;
		this.rooms = new ArrayList<Room>();
	}
	/**
	 * @return all the rooms in the hotel
	 */
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	/**
	 * @return the hotel name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param room a room
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
	}

	/**
	 *  Find all available rooms that meet the booking requirement. 
	 *  @return available rooms, null if cannot find all rooms
	 */

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
					break;
				case 2: 
					if(count2 < nDouble) {
						availableRooms.add(r);
						count2++;
					}
					break;
				case 3:
					if(count3 < nTriple) {
						availableRooms.add(r);
						count3++;
					}
					break;
				}
			}
		}
		if(count1!=nSingle || count2!=nDouble || count3 != nTriple) return null;
		return availableRooms;
	}

	/**
	 * display all the booking information of each room
	 */
	public void displayBooking() {
		for(Room r:this.rooms) {
			System.out.println(r.toString());
		}

	}
	
		

}
