
public abstract class Passenger {
	private String passengerID;
	private String flightNo;
	private String name;
	private char passengerType;
	
	public Passenger(String passengerID, String flightNo, String name,char passengerType){
		this.passengerID = passengerID;
		this.flightNo = flightNo;
		this.name = name;
		this.setPassengerType(passengerType);
	}
	public abstract double checkInBaggage(double weight);
	
	public String getPassengerID(){
		return passengerID;
	}
	public String getFlightNo(){
		return flightNo;
	}
	public void setFlightNo(String flightNo){
		this.flightNo = flightNo;
	}
	public String getName(){
		return name;
	}
	public void print(){
		System.out.println("Passenger ID: " + passengerID + " Flight Number: " + flightNo + " Passenger Name: "+ name);
	}
	public void setPassengerType(char passengerType) {
		this.passengerType = passengerType;
	}
	public char getPassengerType() {
		return passengerType;
	}
}
