package network;

/** Klassen Request �r ett barn till {@link Message}. Det �r ett meddelande som 
 * skickas som ett svar till ett {@link Request}. Den bygger p� rumor-routing 
 * algoritmen.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Response extends Message{
	/** Det funna {@link Event} objektet. */
	private Event foundEvent;
	
	/** Skapar en Respons.
	 * 
	 * @param sp						v�gen tillbaka
	 * @param event						funna {@link Event} objektet
	 * @param pos						start positionen 
	 * @see ShortestPath
	 * @see Position
	 */
	public Response(ShortestPath sp, Event event, Position pos){
		this.setPathHome(sp);
		
	}
	
	/** Returnerar n�sta {@link Position} p� v�gen.
	 * 
	 * @return							n�sta {@link Position} objekt
	 */
	public Position getNextPostion(){
		ShortestPath sp = this.getPathHome();
		Position pos = sp.getNextDirection();
		return pos;
	}
	/** Unders�ker om Responsen �r framme. 
	 * 
	 * @return							om responsen �r framme
	 */	
	public boolean getIsHome(){
		ShortestPath sp = this.getPathHome();
		return sp.isEmpty();
	}
	/** Tar bort n�sta {@link Position} objekt fr�n v�gen tillbaka. */
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
