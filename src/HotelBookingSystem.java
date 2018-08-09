import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelBookingSystem {
	private ArrayList<Hotel> hotels;
	private ArrayList<Customer> customers;
	
	public HotelBookingSystem() {
		this.hotels = new ArrayList<Hotel>();
		this.customers = new ArrayList<Customer>();
	}
	/*
	 * Add single rooms, double rooms and triple rooms in hotel with category
	 */
	public void addHotelRoom(String hotelName,int roomNumber,int capacity) {
		Room newRoom = new Room(roomNumber,capacity);
		Hotel hotel = this.createHotel(hotelName);
		hotel.addRoom(newRoom);
		newRoom.setHotel(hotel);
	}
	/*
	 * Create a hotel. If has this hotel, return it directly
	 */
	public Hotel createHotel(String hotelName) {
		for(Hotel h:this.hotels) {
			if(hotelName.equals(h.getName()))
				return h;
		}
		Hotel hotel = new Hotel(hotelName);
		this.hotels.add(hotel);
		return hotel;
		
	}
	public Customer addCustomer(String name) {
		for(Customer c:this.customers) {
			if(name.equals(c.getName()))
				return c;
		}
		Customer customer = new Customer(name);
		this.customers.add(customer);
		return customer;
	}
	public void cancelOrder(String customerName) {
		Customer customer = this.addCustomer(customerName);
		Booking booking = customer.getBooking();
		Hotel hotel = booking.getHotel();
		//change the leave date of all room to null 
		for(Room room:booking.getRooms()) {
			room.removeBooking(booking);
		}
		hotel.removeBookings(booking);
		customer.setBooking(null);
		
	}
	
	public int convertMonth(String month) {
		switch (month){
		case "Jan": return 1;
		case "Feb": return 2;
		case "Mar": return 3;
		case "Apr": return 4;
		case "May": return 5;
		case "Jun": return 6;
		case "Jul": return 7;
		case "Aug": return 8;
		case "Sep": return 9;
		case "Oct": return 10;
		case "Nov": return 11;
		case "Dec": return 12;
		}
		return 0;
	}
	
	/*
	 * Split the command string and handle it with its command
	 */
	public void handleCommand(String sentence) {
		String[] cmd = sentence.split(" ");
		switch(cmd[0]){
			case "Hotel":
				this.addHotelRoom(cmd[1], Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
				break;
			case "Booking":
				this.CollectOrder(cmd);
				break;
			case "Change":
				this.cancelOrder(cmd[1]);
				this.CollectOrder(cmd);
				break;
			case "Cancel":
				this.cancelOrder(cmd[1]);
				System.out.println(cmd[0]+" "+cmd[1]);
			case "Print":
				Hotel hotel = this.createHotel(cmd[1]);
				System.out.println(hotel.toString());
				
		}	
	}
	public Booking makeBooking(String name,LocalDate arrivalTime,int nights,int nSingle,int nDouble,int nTriple) {
		Customer customer = this.addCustomer(name);
		for (Hotel h :this.hotels) {
			ArrayList<Room> availableRooms = h.assignRooms(nTriple, nDouble, nSingle,arrivalTime,nights);
			if(availableRooms != null) {
				Booking booking = new Booking(customer,arrivalTime,nights,h);
				booking.setRooms(availableRooms);
				h.addBookings(booking);
				customer.setBooking(booking);
				return booking;
			}
		}
		return null;
	}
	public void CollectOrder(String[] cmd) {
		String name = cmd[1];
		int Month = this.convertMonth(cmd[2]);
		int day = Integer.parseInt(cmd[3]);
		LocalDate arrivalTime = LocalDate.of(2018, Month, day);
		int nights = Integer.parseInt(cmd[4]);
		int nSingle = 0;
		int nDouble = 0;
		int nTriple = 0;
		for(int i=5;i<cmd.length;i++) {
			switch(cmd[i]) {
			case "single":
				nSingle = nSingle + Integer.parseInt(cmd[i+1]);
				break;
			case "double":
				nDouble = nDouble + Integer.parseInt(cmd[i+1]);
				break;
			case "triple":
				nTriple = nTriple + Integer.parseInt(cmd[i+1]);
			}	
		}
		Booking booking = this.makeBooking(name,arrivalTime,nights,nSingle,nDouble,nTriple);
		if (booking == null) System.out.println(cmd[0]+" rejected");
		else System.out.println(cmd[0]+" "+ booking.toString());
		
	}

	
	public ArrayList<String> readFile(String f) {
		Scanner sc = null;
		ArrayList<String> lines = new ArrayList<String>();
	    try {
	    	sc = new Scanner(new File(f));
	    	while(sc.hasNextLine()) {
	    		lines.add(sc.nextLine());
	    	}
	    	
	    }
	    // Read input from the scanner her
	    catch (FileNotFoundException e){
	    	System.out.println(e.getMessage());
	    }
	    finally{
	    	if (sc != null) sc.close();
	    }
	    return lines;
	}
	
	public static void main(String[] args) {
		HotelBookingSystem sys = new HotelBookingSystem();
		ArrayList<String> sentences = sys.readFile(args[0]);
		for (String s:sentences) {
			//System.out.println(s);
			sys.handleCommand(s);
		}
		System.out.println("----------------------------");

	}


}
