
public class BusinessPassenger extends Passenger {
	
	private String mealType;
	

	public BusinessPassenger(String passengerID, String flightNo, String name, String mealType) {
		super(passengerID, flightNo, name,'B');
		this.setMealType(mealType);
	}

	@Override
	public double checkInBaggage(double weight) {
		if (weight-20<=0)
			return 0;
		else
			return ((weight-20)*20);
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getMealType() {
		return mealType;
	}
	
	public void print(){
		super.print();
		System.out.println("Passenger type: Business. Meal Type: " + mealType );
	}

	
}
