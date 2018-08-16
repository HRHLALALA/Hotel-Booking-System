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
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public String getName() {
		return this.name;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
	}


	public ArrayList<Room> assignRooms(int nTriple,int nDouble,int nSingle,LocalDate arrivalTime,int nights){
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		//System.out.println("nSingle "+ nSingle+" nDouble "+nDouble + " nTriple "+nTriple);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for(Room r:this.rooms){
			if(r.isAvailable(arrivalTime, nights)) {
				//System.out.println(r.getId()+" "+r.getCapacity());
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
		//System.out.println(" count1 "+count1+" count2 "+count2+" count3 "+count3);
		if(count1!=nSingle || count2!=nDouble || count3 != nTriple) return null;
		return availableRooms;
	}


	public void displayBooking() {
		for(Room r:this.rooms) {
			System.out.println(r.toString());
		}

	}
	
		

}
