package network;

public class Request extends Message{
	private int targetID;
	private boolean isOnTrack;
	public Request(int eventID, int timeToLive, Position pos){
		targetID = eventID;
		this.setTimeToLive(timeToLive);
		this.setPathHome(new ShortestPath(pos));
	}
	public int getTargetId(){
		return targetID;
	}
	public boolean getIsOnTrack(){
		return isOnTrack;
	}
	public void addPosToPathHome(Position pos){
		ShortestPath sp = this.getPathHome();
		sp.addDirection(pos);
		this.setPathHome(sp);
	}
}
