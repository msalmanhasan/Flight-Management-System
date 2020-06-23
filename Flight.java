
public class Flight
{
	// Instance Variables
	private String flightNo;
	private String departurePoint;
	private String destination;
	private double flightLength;
	private String flightDate;
	private String flightTime;
	private int capacity;
	private int bookedSeats;


	private char flightStatus;

	// Constructor
	public Flight( String flightNo, String departurePoint,
					String destination, double flightLength ,
					String flightDate , String flightTime , int capacity )
	{
		this.flightNo = flightNo;
		this.departurePoint = departurePoint;
		this.destination = destination;
		this.flightLength = flightLength;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.capacity = capacity;

		this.flightStatus = 'S';
		this.bookedSeats = 0;
	}

	// Called when flight is opened for boarding
	public void open() throws FlightStatusException
	{
		if ( flightStatus == 'S' )
		{
			flightStatus = 'B';
			return;
		}
		throw new FlightStatusException("The flight is not scheduled and cannot be opened.");	
	}

	// Called when flight is closed for boarding
	public void close() throws FlightStatusException
	{
		if ( flightStatus == 'B' )
		{
			flightStatus = 'D';
			return;
		}
		throw new FlightStatusException("The flight is not in boarding status and cannot be closed.");
	}

	// Called when a booking is made
	public void reserveSeats(int numberOfPassengers) throws FlightStatusException,BookingLimitException
	{
		// If flight is not scheduled
		if ( flightStatus != 'S' )
		
			throw new FlightStatusException("The flight is not scheduled. Seats can't be reserved.");	
		
		// check if Bookings seats exceeds capacity
		if ( bookedSeats + numberOfPassengers > capacity  )
		
		throw new BookingLimitException("The number of seats are limited.");
			

		// Book seats by incrementing
		bookedSeats = bookedSeats + numberOfPassengers;
		
	}

	public void delayFlight(int hours, int minutes) throws FlightCancelledException, FlightStatusException
	{
		String [] str = flightTime.split(":");
		int h = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);

		int newHour = h + hours;
		int newMinutes = m + minutes;
		
		if(newMinutes>=60){
			newHour++;
			newMinutes = newMinutes-60;
		}

		int arrivalHour = newHour + (int) flightLength;
		int arrivalMinutes = (int)(newMinutes + (flightLength-arrivalHour)*60);
		
		if(arrivalMinutes>=60){
			arrivalHour++;
			arrivalMinutes = arrivalMinutes-60;
		}
		// If new flight is not beyond mid night, and flight stutus is Schedules
		if( flightStatus != 'S'){
			throw new FlightStatusException("The flight is not scheduled.");
		}
		if ( arrivalHour < 24 && newMinutes < 60  )
		{
			flightTime = newHour + ":" + newMinutes;
			return;
		}
		else
		{
			// If time goes beyond midnight
			flightStatus = 'C';
			throw new FlightCancelledException("The Flight has been cancelled.");
		}
	}

	public void print()
	{
		if ( flightStatus == 'B')
			System.out.print("Flight Status: Boarding\n");
		if ( flightStatus == 'D')
			System.out.print("Flight Status: Departed\n");
		if ( flightStatus == 'C')
			System.out.print("Flight Status: Cancelled\n");
		if ( flightStatus == 'S')
			System.out.print("Flight Status: Scheduled\n");

		System.out.println( flightNo + "\t" + departurePoint + "\t" + destination + "\t" + flightLength + " hours\t" + flightDate + "\t" + flightTime + "\t" + capacity );

	}

	public void setFlightTime(String t) {
		flightTime = t;
	}
	public void setFlightStatus(char s) {
		flightStatus = s;
	}
	public void setBookedSeats(int bookedSeats){
		this.bookedSeats = bookedSeats;
	}
	// Accessors
	public String getFlightNo() {
		return flightNo;
	}

	public String getDeparturePoint() {
		return departurePoint;
	}

	public String getDestination() {
		return destination;
	}

	public double getFlightLength() {
		return flightLength;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public String getFlightTime() {
		return flightTime;
	}
	public int getCapacity() {
		return capacity;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}


	public char getFlightStatus() {
		return flightStatus;
	}

}