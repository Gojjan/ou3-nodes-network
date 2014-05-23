package network;

public class Request extends Message{
	private int targetID;
	private boolean isOnTrack;
	public Request(int eventID, int timeToLive, Position pos, ShortestPath sp){
		targetID = eventID;
		this.setTimeToLive(timeToLive);
		this.setPathHome(new ShortestPath(pos));
	}
	public boolean getIsOnTrack(){
		return isOnTrack;
	}
}
