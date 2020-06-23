
public class EconomyPassenger extends Passenger {
	 

	public EconomyPassenger(String passengerID, String flightNo, String name) {
		super(passengerID, flightNo, name,'E');
	}

	@Override
	public double checkInBaggage(double weight) {
		if (weight>20)
			return -1;
		else if (weight-15<=0)
			return 0;
		else
		    return ((weight-15)*20);		
	}

	public void print(){
		super.print();
		System.out.println("Passenger type: Economy.");
	}


}
