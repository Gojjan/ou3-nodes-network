package network;

public class Response extends Message{
	private Event foundEvent;
	public Response(ShortestPath sp, Event event, Position pos){
		this.setPathHome(sp);
		
	}
	public Position getNextPostion(){
		ShortestPath sp = this.getPathHome();
		Position pos = sp.getNextDirection();
		return pos;
	}
	public boolean getIsHome(){
		ShortestPath sp = this.getPathHome();
		return sp.isEmpty();
	}
	public void popNextPosition(){
		ShortestPath sp = this.getPathHome();
		sp.popNextDirection();
		this.setPathHome(sp);
	}
	public Event getEvent(){
		return foundEvent;
	}
}
