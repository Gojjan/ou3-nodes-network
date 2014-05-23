package network;

public class Response extends Message{
	private Event foundEvent;
	public Response(ShortestPath sp, Event event, Position pos){
		this.setPathHome(sp);
	}
	//System.out.println("Hello world!");
	//System.out.println("Hello world!");
	//System.out.println("Hello world!");
}
