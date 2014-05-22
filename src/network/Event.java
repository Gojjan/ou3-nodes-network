package network;

public class Event {
	private int uniqueID;
	private Position homePos;
	private int dateOfBirth;
	
	public Event(int id, Position pos, int dob){
		uniqueID = id;
		homePos = pos;
		dateOfBirth = dob;
	}
	public int getID(){
		return uniqueID;
	}
	public int getDateOfBirth(){
		return dateOfBirth;
	}
	public Position getHomePos(){
		return homePos;
	}
}
