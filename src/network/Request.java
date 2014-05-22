package network;

public class Request extends Message{
	private int targetID;
	public Request(int eventID, int timeToLive, Position pos){
		targetID = eventID;
		this.setTimeToLive(timeToLive);
		this.setPathHome(new ShortestPath(pos));
	}
}
