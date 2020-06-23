

public class RegionalFlight extends Flight
{
	private Flight connectingFlight;


// Constructor
	public RegionalFlight( String flightNo, String departurePoint,
					String destination, double flightLength ,
					String flightDate , String flightTime , int capacity , Flight cf )
	{
		super ( flightNo ,  departurePoint,destination ,flightLength, flightDate, flightTime, capacity );

		this.connectingFlight = cf;
	}

	public void reserveSeats(int numberOfPassengers) throws FlightStatusException, BookingLimitException // called when a booking is made
	{
		try
		{
		super.reserveSeats(numberOfPassengers);
		connectingFlight.reserveSeats(numberOfPassengers);
		}
		catch(FlightStatusException e){
			throw new FlightStatusException(e.getMessage());
		}
		catch(BookingLimitException e){
			throw new BookingLimitException(e.getMessage());
		}
	}

	public void delayFlight(int hours, int minutes) throws FlightStatusException // called when flight is delayed
, FlightCancelledException
	{

		String [] str = connectingFlight.getFlightTime().split(":");
		int h = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);

		int newHour = h + hours;
		int newMinutes = m + minutes;
		if(newMinutes>=60){
			newHour++;
			newMinutes = newMinutes-60;
		}

		int arrivalHour = newHour + (int) connectingFlight.getFlightLength();
		int arrivalMinutes = (int)(newMinutes + (connectingFlight.getFlightLength()-arrivalHour)*60);
		
		if(arrivalMinutes>=60){
			arrivalHour++;
			arrivalMinutes = arrivalMinutes-60;
		}
		// If new flight is not beyond mid night, and flight stutus is Schedules
		if( connectingFlight.getFlightStatus() != 'S'|| getFlightStatus() != 'S'){
			throw new FlightStatusException("The flight is not scheduled.");
		}
		if ( arrivalHour < 24 && newMinutes < 60  )
		{
			connectingFlight.setFlightTime(newHour + ":" + newMinutes); 
			
			String [] string = getFlightTime().split(":");
			int hrs = Integer.parseInt(string[0]);
			int mins = Integer.parseInt(string[1]);

			int newHourR = hrs + hours;
			int newMinutesR = mins + minutes;
			if(newMinutesR>=60){
				newHourR++;
				newMinutesR = newMinutesR-60;
			}
			
			setFlightTime(newHourR + ":" + newMinutesR);
		}
		else
		{
			// If time goes beyond midnight
			setFlightStatus('C'); 
			connectingFlight.setFlightStatus('C');
			throw new FlightCancelledException("The regional and connecting flights have been cancelled.");
		}

	}

	public String getConnectingFlightNo(){
		return connectingFlight.getFlightNo();
	}
	public Flight getConnectingFlight(){
		return connectingFlight;
	}
	public void print()
	{
		super.print();
		System.out.print("Connecting Flight: " );
		
	}

}