import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


public class ManageFlights {
	
	public static void main(String []args){
	
		Scanner input = new Scanner(System.in);
		
		Vector<Flight> flights = new Vector<Flight>();
		Vector<Passenger> passengers = new Vector<Passenger>();
		
		try
		{
		BufferedReader br = new BufferedReader(new FileReader("passengers.txt"));
		String line = br.readLine();
		
			while(line!=null)
			{
				
				StringTokenizer tk = new StringTokenizer(line,"\t");
				
				String passengerName = tk.nextToken();
				String passengerID = tk.nextToken();
				String flightNo = tk.nextToken();
				String passengerType = tk.nextToken();
				if(passengerType.equals("E"))
				{
					Passenger temp = new EconomyPassenger(passengerID,flightNo,passengerName);
					passengers.add(temp);
					
				}
				else if(passengerType.equals("B"))
				{
					String mealType = tk.nextToken();
					Passenger temp = new BusinessPassenger(passengerID,flightNo,passengerName,mealType);
					passengers.add(temp);
					
				}
			line = br.readLine();
			}
		
		BufferedReader br2 = new BufferedReader(new FileReader("flights.txt"));
		String line2 = br2.readLine();
		
			while(line2!=null)
			{
				
				
				StringTokenizer tk2 = new StringTokenizer(line2,"\t");
				
				if(tk2.countTokens()==9)
				{
					
					String flightNo = tk2.nextToken();
					String departurePoint = tk2.nextToken();
					String destination = tk2.nextToken();
					String flightLength = tk2.nextToken(); double fl = Double.parseDouble(flightLength);
					String flightDate = tk2.nextToken();
					String flightTime = tk2.nextToken();
					String flightCapacity = tk2.nextToken();int cap = Integer.parseInt(flightCapacity);
					String bookedSeats = tk2.nextToken(); int bs = Integer.parseInt(bookedSeats);
					String flightStatus = tk2.nextToken();char fs = flightStatus.charAt(0);
					
					Flight temp = new Flight(flightNo,departurePoint,destination,fl,flightDate,flightTime,cap);
					temp.setFlightStatus(fs);
					temp.setBookedSeats(bs);
					flights.add(temp);
					
				}
				else if(tk2.countTokens()==10)
				{
					
					String flightNo = tk2.nextToken();
					String departurePoint = tk2.nextToken();
					String destination = tk2.nextToken();
					String flightLength = tk2.nextToken(); double fl = Double.parseDouble(flightLength);
					String flightDate = tk2.nextToken();
					String flightTime = tk2.nextToken();
					String flightCapacity = tk2.nextToken();int cap = Integer.parseInt(flightCapacity);
					String bookedSeats = tk2.nextToken(); int bs = Integer.parseInt(bookedSeats);
					String flightStatus = tk2.nextToken();char fs = flightStatus.charAt(0);
					
					
					
					line2 = br2.readLine();
					
					StringTokenizer tk3 = new StringTokenizer(line2,"\t");
					
					String flightNo2 = tk3.nextToken();
					String departurePoint2 = tk3.nextToken();
					
					String destination2 = tk3.nextToken();
					
					tk3.nextToken(); double fl2 = Double.parseDouble(flightLength);
					String flightDate2 = tk3.nextToken();
					String flightTime2 = tk3.nextToken();
					
					tk3.nextToken();int cap2 = Integer.parseInt(flightCapacity);
					tk3.nextToken(); int bs2 = Integer.parseInt(bookedSeats);
					tk3.nextToken();char fs2 = flightStatus.charAt(0);
					
					Flight temp2 = new Flight(flightNo2,departurePoint2,destination2,fl2,flightDate2,flightTime2,cap2);
					temp2.setFlightStatus(fs2);
					temp2.setBookedSeats(bs2);
					Flight temp = new RegionalFlight(flightNo,departurePoint,destination,fl,flightDate,flightTime,cap,temp2);
					
					temp.setFlightStatus(fs);
					temp.setBookedSeats(bs);
					
					flights.add(temp);
					flights.add(temp2);
					
				}
				line2 = br2.readLine();	
			}
			br.close();
			br2.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
	finally
	{
	int choice = 0;
	while( choice != 10 ){
		
		System.out.println("\n\t\tMain Menu");
		System.out.println("1. Schedule a new flight");
		System.out.println("2. Display All Flights");
		System.out.println("3. Reserve Seats for a flight");
		System.out.println("4. Open a Flight");
		System.out.println("5. Close a Flight");
		System.out.println("6. Delay a Flight");
		System.out.println("7. Check In Passenger");
		System.out.println("8. Display details for all passengers that have checked in on a specified flight");
		System.out.println("9. Transfer Regional Flight Passengers");
		System.out.println("10.Exit");
		
		
		System.out.println("\nEnter your selection: ");
		
		choice = input.nextInt();

		if ( choice == 1 )
			ScheduleFlight(flights);
		else if ( choice == 2 )
			DisplayFlights(flights);
		else if ( choice == 3 )
			ReserveSeats(flights);
		else if ( choice == 4 )
			OpenFlight(flights);
		else if ( choice == 5 )
			CloseFlight(passengers,flights);
		else if ( choice == 6 )
			DelayFlight(flights);
		else if ( choice == 7 )
			CheckInPassenger(passengers,flights);
		else if ( choice == 8 )
			DisplayFlightPassengers(passengers,flights);
		else if ( choice == 9 )
			TransferRegionalFlightPassengers(passengers,flights);
		else if ( choice == 10 )
			Exit(passengers,flights);
		
			

	}
	}
}
	public static void DisplayFlights(Vector<Flight> flights) {
		
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			(   (Flight) e.nextElement()).print();
		}
	}
	private static void ScheduleFlight(Vector<Flight> flights) {
		
		System.out.println("\t Choose the serail number from: \n");
		System.out.println("1. Direct Flight.");
		System.out.println("2. Regional Flight.");
		Scanner key = new Scanner(System.in);
		
			int opt = key.nextInt();
		
		if (opt==1)
		{
			System.out.println("Please input flight number:");
				String fno = key.next();
			for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
			{
				if( ((Flight) e.nextElement()).getFlightNo().equals(fno)){
					System.out.println("The flight number entered already exists.");
					return;
				}
			}
			System.out.println("Please enter departure point:");
				String dp = key.next();
			System.out.println("Please enter destination:");
				String dest = key.next();
			System.out.println("Please enter flight length:");
				double fl = key.nextDouble();
			System.out.println("Please enter flight date:");
				String fd = key.next();
			System.out.println("Please enter flight time:");
				String ft = key.next();
			System.out.println("Please enter flight capacity:");
				int cap = key.nextInt();
				
			Flight temp = new Flight(fno,dp,dest,fl,fd,ft,cap);
			flights.add(temp);
		}
		else if(opt == 2)
		{
			System.out.println("Please input regional flight number:");
			String fno = key.next();
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno)){
				System.out.println("The flight number entered already exists.");
				return;
			}
		}
		System.out.println("Please enter departure point:");
			String dp = key.next();
		System.out.println("Please enter destination:");
			String dest = key.next();
		System.out.println("Please enter flight length:");
			double fl = key.nextDouble();
		System.out.println("Please enter flight date:");
			String fd = key.next();
		System.out.println("Please enter flight time:");
			String ft = key.next();
		System.out.println("Please enter flight capacity:");
			int cap = key.nextInt();
			
			
		System.out.println("Please enter connecting flight number:");	
			String cfno = key.next();
			for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
			{
				if( ((Flight) e.nextElement()).getFlightNo().equals(cfno)){
					System.out.println("The flight number entered already exists.");
					return;}
			}
				
		System.out.println("Please enter departure point of connecting flight:");
			String dpcf = key.next();
		System.out.println("Please enter destination of connecting flight:");
			String destcf = key.next();
		System.out.println("Please enter flight length of connecting flight:");
			double flcf = key.nextDouble();
		System.out.println("Please enter flight date of connecting flight:");
			String fdcf = key.next();
		System.out.println("Please enter flight time of connecting flight:");
			String ftcf = key.next();
		System.out.println("Please enter flight capacity of connecting flight:");
			int capcf = key.nextInt();
		
		Flight cf = new Flight(cfno,dpcf,destcf,flcf,fdcf,ftcf,capcf);
		RegionalFlight rf = new RegionalFlight(fno,dp,dest,fl,fd,ft,cap,cf);
		flights.add(rf);
		flights.add(cf);
		}
		else
			System.out.println("Invalid option selected.");
	}
	public static void ReserveSeats(Vector<Flight> flights)
	{
		System.out.print("Enter flight number:");
		Scanner key = new Scanner(System.in);
		
		String fno = key.nextLine();
		
		int count = -1;
		// Search through all flights
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
				count++;
			
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno)){
				
				System.out.print("Enter number of seats to reserve: ");
				int seats = key.nextInt();
				try
				{
				((Flight)flights.elementAt(count)).reserveSeats(seats);
				System.out.println( seats +" seats reserved on Flight " + fno);
				return;
				}
				catch(FlightStatusException ex){
					System.out.println(ex.getMessage());
					System.out.println("Therefore, seats were not reserved on Flight " + fno);
					return;
				}
				catch(BookingLimitException ex){
					System.out.println(ex.getMessage());
					System.out.println("Therefore, seats were not reserved on Flight " + fno);
					return;
				}	
			}
		}
		System.out.println( "No such flight exists." );
	}
	
	public static void OpenFlight(Vector<Flight> flights) {
		System.out.print("Enter flight number: ");
		Scanner key = new Scanner(System.in);
		String fno = key.nextLine();
		
		int count = -1;
		// Search through all flights
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			count++;
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno))
			{
				try
				{
					((Flight)flights.elementAt(count)).open();
					System.out.println("Flight " + fno + " opened. Flight Details: \t");
					System.out.println( ((Flight)flights.elementAt(count)).getFlightNo() + "\t" + ((Flight)flights.elementAt(count)).getDeparturePoint()
					+ "\t" + ((Flight)flights.elementAt(count)).getFlightDate() + "\t" + ((Flight)flights.elementAt(count)).getFlightTime() );
					return;
				}
				catch(FlightStatusException ex)
				{
					System.out.println(ex.getMessage());
					return;
				}
			}
		}
		// Code will reach here only if no flight exists
		System.out.println( "No such flight exists!" );
	}
	public static void CloseFlight(Vector<Passenger> passengers,Vector<Flight> flights) {
		System.out.print("Enter flight number: ");
		Scanner key = new Scanner(System.in);
		String fno = key.nextLine();
		
		int count = -1;
		// Search through all flights
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			count++;
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno))
			{
				try
				{
					((Flight)flights.elementAt(count)).close();
					System.out.println("Flight " + fno + " closed. Flight Details: \t");
					System.out.println( ((Flight)flights.elementAt(count)).getFlightNo() + "\t" + ((Flight)flights.elementAt(count)).getDeparturePoint()
					+ "\t" + ((Flight)flights.elementAt(count)).getFlightDate() + "\t" + ((Flight)flights.elementAt(count)).getFlightTime() );
					String filename = "Manifest-"+fno+".txt";
					PrintWriter outputStream = new PrintWriter(new FileOutputStream(filename));
					outputStream.println(fno+"\t"+((Flight)flights.elementAt(count)).getDeparturePoint()+"\t"+((Flight)flights.elementAt(count)).getDestination()
							+"\t"+((Flight)flights.elementAt(count)).getFlightTime()+"\n\n");
					int count2 = -1;
					for(Enumeration<Passenger> en = passengers.elements(); en.hasMoreElements();){
						count2++;
						en.nextElement();
						if(passengers.elementAt(count2).getFlightNo().equals(fno))
						outputStream.println(passengers.elementAt(count2).getPassengerID()+"\t"+passengers.elementAt(count2).getName());
					}
					outputStream.close();
					return;	
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					return;
				}
			}
		}
		// Code will reach here only if no flight exists
		System.out.println( "No such flight exists!" );
	}
		
		
	public static void DelayFlight(Vector<Flight> flights)
	{
		System.out.print("Enter flight number:");
		Scanner key = new Scanner(System.in);
		
		String fno = key.nextLine();
		
		int count = -1;
		// Search through all flights
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			count++;
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno)&&((Flight) flights.elementAt(count)).getFlightStatus()=='S')
			{
				System.out.print("Enter number of hours the flight is delayed for: ");
				int hours = key.nextInt();
				System.out.print("Enter number of minutes the flight is delayed for: ");
				int minutes = key.nextInt();
				try
				{
					((Flight)flights.elementAt(count)).delayFlight(hours,minutes);
					System.out.println("Flight " + fno + " delayed. Flight Details: \t");
					((Flight)flights.elementAt(count)).print();
					return;
				}
				catch(FlightStatusException ex)
				{
					System.out.println(ex.getMessage());
					return;
				}
				catch(FlightCancelledException ex){
					System.out.println(ex.getMessage());
					return;
				}
				
			}
		}
		// Code will reach here only if no flight exists
		System.out.println( "No such flight exists." );
	}
	
	public static void CheckInPassenger(Vector<Passenger> passengers,Vector<Flight> flights) {
		
		Scanner key = new Scanner(System.in);
		System.out.println("Please enter flight number:");
		
		String fno = key.next();
		int count=-1;
		
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			count++;
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno))
			{
				if(((Flight)flights.elementAt(count)).getFlightStatus()!='B')
				{
					System.out.println("The flight is not boarding.");
					return;
				}
				System.out.println("Choose from:");
				System.out.println("1. Economy Passenger.");
				System.out.println("2. Business Passenger.");
				
				int opt = key.nextInt();
				
				System.out.println("Please enter the name of passenger:");
				String name = key.next();
				System.out.println("Please enter passenger ID:");
				String passengerID = key.next();
				
				System.out.println("Please enter the weight of passenger:");
				double weight = key.nextDouble();
				if (opt ==1)
				{
					EconomyPassenger p = new EconomyPassenger(passengerID,fno,name);
					double charges = p.checkInBaggage(weight);
					if(charges == -1)
						{
						System.out.println("The baggage is over limit.");
						return;
						}
					else
						System.out.println("The baggage charges are: " + charges);
						
					passengers.add(p);
					return;
				}
				else if(opt ==2)
				{
					System.out.println("Please enter meal type:");
					String mealType = key.next();
					BusinessPassenger p = new BusinessPassenger(passengerID,fno,name,mealType);
					
					double charges = p.checkInBaggage(weight);
					System.out.println("The baggage charges are: " + charges);
					passengers.add(p);
					return;
				}
			}
			
		}
		System.out.println( "No such flight exists." );
	}
	
	public static void DisplayFlightPassengers(Vector<Passenger> passengers,Vector<Flight> flights) {
		Scanner key = new Scanner(System.in);
		System.out.println("Please enter flight number:");
		
		String fno = key.next();
		
		int count=-1;
		
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno))
			{
				for (Enumeration<Passenger> en = passengers.elements(); en.hasMoreElements();)
				{
					count++;
					if(((Passenger)en.nextElement()).getFlightNo().equals(fno))
					{
						((Passenger)passengers.elementAt(count)).print();
					}
				}
				return;
			}
		}
		System.out.println( "No such flight exists." );
	}
	
	public static void TransferRegionalFlightPassengers(Vector<Passenger> passengers,Vector<Flight> flights) {
		
		Scanner key = new Scanner(System.in);
		System.out.println("Please enter flight number:");
		
		String fno = key.next();
		
		int count=-1;
		int count2=-1;
		int count3 = -1;
	
		for ( Enumeration<Flight> e = flights.elements(); e.hasMoreElements(); )
		{
			
			count2++;
			if( ((Flight) e.nextElement()).getFlightNo().equals(fno))
			{
				
				try{
				(((RegionalFlight)flights.elementAt(count2)).getConnectingFlight()).open();
				
				}
				catch(Exception exception){
					System.out.println("The Flight is not regional flight or the connecting flight cannot be opened.");
					return;
				}
				for (Enumeration<Passenger> en = passengers.elements(); en.hasMoreElements();)
				{
					
					count++;
					if(((Passenger)en.nextElement()).getFlightNo().equals(fno))
					{
						
						try
						{
						((Passenger)passengers.elementAt(count)).setFlightNo(((RegionalFlight)flights.elementAt(count2)).getConnectingFlightNo());
						String name,flightNo,passID, mealType;;
						name = ((Passenger)passengers.elementAt(count)).getName();
						flightNo = ((Passenger)passengers.elementAt(count)).getFlightNo();
						passID = ((Passenger)passengers.elementAt(count)).getPassengerID();
						if((passengers.elementAt(count)).getPassengerType()=='E')
							{
								Passenger temp =  new EconomyPassenger(passID,flightNo,name);
								passengers.add(temp);	
							}
						else
							{
								mealType = ((BusinessPassenger)passengers.elementAt(count)).getMealType();
								Passenger temp =  new BusinessPassenger(passID,flightNo,name,mealType);
								passengers.add(temp);
							}
						((Passenger)passengers.elementAt(count)).setFlightNo(fno);
						}
						catch(Exception ex)
						{
							System.out.println("The flight number is not of regional flight.");
							return;
						}
					}
				}
				try{
					(((RegionalFlight)flights.elementAt(count2)).getConnectingFlight()).close();
	
					
					System.out.println("The passengers have been transferred.");
					
					
					}
					catch(Exception exception){
						System.out.println(exception.getMessage());
						System.out.println("The Flight is not regional flight or the connecting flight cannot be closed.");
						return;
					}
					try{
						
						String filename = "Manifest-"+((RegionalFlight)flights.elementAt(count2)).getConnectingFlightNo()+".txt";
						
						PrintWriter outputStream = new PrintWriter(new FileOutputStream(filename));
						
						outputStream.println(fno+"\t"+((Flight)flights.elementAt(count2)).getDeparturePoint()+"\t"+((Flight)flights.elementAt(count2)).getDestination()
								+"\t"+((Flight)flights.elementAt(count2)).getFlightTime()+"\n\n");
						
						for(Enumeration<Passenger> f = passengers.elements(); f.hasMoreElements();){
							count3++;
							
							if(f.nextElement().getFlightNo().equals(((RegionalFlight)flights.elementAt(count2)).getConnectingFlightNo()))
								{	outputStream.println(passengers.elementAt(count3).getPassengerID()+"\t"+passengers.elementAt(count3).getName());
								
								}
							}
						outputStream.close();
						return;
						}
						catch(Exception exptn){System.out.println(exptn.getMessage());return;}
			}
		
		}
		System.out.println( "No such flight exists." );
		
	}
	
	public static void Exit(Vector<Passenger> passengers,Vector<Flight> flights) {
		
		try
	{
		PrintWriter outputStream = new PrintWriter(new FileOutputStream("passengers.txt"));
		PrintWriter outputStream2 = new PrintWriter(new FileOutputStream("flights.txt"));

		
		int count = -1;
		
		for (Enumeration<Passenger> e = passengers.elements(); e.hasMoreElements();)
		{
			count++;
			String pname = ((Passenger)passengers.elementAt(count)).getName();
			String pID = ((Passenger)passengers.elementAt(count)).getPassengerID();
			String fno = ((Passenger)passengers.elementAt(count)).getFlightNo();
			char ptype = ((Passenger)passengers.elementAt(count)).getPassengerType();
			if(ptype=='E'){
				outputStream.print(pname);
				outputStream.print("\t"+pID);
				outputStream.print("\t"+fno);
				outputStream.println("\t"+ptype);
			}
			else if(ptype=='B'){
			String mealtype = ((BusinessPassenger)passengers.elementAt(count)).getMealType();
			outputStream.print(pname);
			outputStream.print("\t"+pID);
			outputStream.print("\t"+fno);
			outputStream.print("\t"+ptype);
			outputStream.println("\t"+mealtype);
			}
			e.nextElement();
		}
		outputStream.close();
		int count2 = -1;
		for ( Enumeration<Flight> f = flights.elements(); f.hasMoreElements(); )
		{
			count2++;
			
			String fno = ((Flight)flights.elementAt(count2)).getFlightNo();
			String dp = ((Flight)flights.elementAt(count2)).getDeparturePoint();
			String destination = ((Flight)flights.elementAt(count2)).getDestination();
			double flength = ((Flight)flights.elementAt(count2)).getFlightLength();
			String fdate = ((Flight)flights.elementAt(count2)).getFlightDate();
			String ftime = ((Flight)flights.elementAt(count2)).getFlightTime();
			int fcapacity = ((Flight)flights.elementAt(count2)).getCapacity();
			int bookedseats = ((Flight)flights.elementAt(count2)).getBookedSeats();
			char fstatus = ((Flight)flights.elementAt(count2)).getFlightStatus();
			
			Class c = flights.elementAt(count2).getClass();
			String classname = c.getName();
			if(classname.equals("RegionalFlight"))
			{
				outputStream2.print(fno);
				outputStream2.print("\t"+dp);
				outputStream2.print("\t"+destination);
				outputStream2.print("\t"+flength);
				outputStream2.print("\t"+fdate);
				outputStream2.print("\t"+ftime);
				outputStream2.print("\t"+fcapacity);
				outputStream2.print("\t"+bookedseats);
				outputStream2.print("\t"+fstatus);
				outputStream2.println("\tRegionalFlight");
			}
			else if(classname.equals("Flight"))
			{
				outputStream2.print(fno);
				outputStream2.print("\t"+dp);
				outputStream2.print("\t"+destination);
				outputStream2.print("\t"+flength);
				outputStream2.print("\t"+fdate);
				outputStream2.print("\t"+ftime);
				outputStream2.print("\t"+fcapacity);
				outputStream2.print("\t"+bookedseats);
				outputStream2.println("\t"+fstatus);
			}
			f.nextElement();
		}
		outputStream2.close();
		System.exit(0);
	}
	catch(Exception exception){System.out.println("File I/O exception caught.");return;}
}
	
}
