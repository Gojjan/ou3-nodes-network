package network;

/** Klassen Request är ett barn till {@link Message}. Det är ett meddelande som 
 * skickas som ett svar till ett {@link Request}. Den bygger på rumor-routing 
 * algoritmen.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Response extends Message{
	/** Det funna {@link Event} objektet. */
	private Event foundEvent;
	
	/** Skapar en Respons.
	 * 
	 * @param sp						vägen tillbaka
	 * @param event						funna {@link Event} objektet
	 * @param pos						start positionen 
	 * @see ShortestPath
	 * @see Position
	 */
	public Response(ShortestPath sp, Event event, Position pos){
		this.setPathHome(sp);
		
	}
	
	/** Returnerar nästa {@link Position} på vägen.
	 * 
	 * @return							nästa {@link Position} objekt
	 */
	public Position getNextPostion(){
		ShortestPath sp = this.getPathHome();
		Position pos = sp.getNextDirection();
		return pos;
	}
	/** Undersöker om Responsen är framme. 
	 * 
	 * @return							om responsen är framme
	 */	
	public boolean getIsHome(){
		ShortestPath sp = this.getPathHome();
		return sp.isEmpty();
	}
	/** Tar bort nästa {@link Position} objekt från vägen tillbaka. */
	public void popNextPosition(){
		ShortestPath sp = this.getPathHome();
		sp.popNextDirection();
		this.setPathHome(sp);
	}
	/** Returnerar det funna {@link Event} objektet. */
	public Event getEvent(){
		return foundEvent;
	}
}
