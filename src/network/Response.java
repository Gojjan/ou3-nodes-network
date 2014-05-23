package network;

public class Response extends Message{
	private Event foundEvent;
	public Response(ShortestPath sp, Event event, Position pos){
		this.setPathHome(sp);
	}
}
