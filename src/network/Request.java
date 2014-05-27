package network;

/** Klassen Request är ett barn till {@link Message}. Det är ett meddelande som 
 * efterfrågar ett event med en viss identifikation. Den bygger på rumor-routing 
 * algoritmen.
 * 
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Request extends Message{
	/** Identifikationen på {@link Event} objektet som söks. */
	private int targetID;
	
	/** Skapar ett Request.
	 * 
	 * @param eventID						sökta identifikationen
	 * @param timeToLive					tidssteg kvar
	 * @param pos							{@link Position} där den skapas
	 */
	public Request(int eventID, int timeToLive, Position pos){
		System.out.println("Request");
		targetID = eventID;
		this.setTimeToLive(timeToLive);
		this.setPathHome(new ShortestPath(pos));
	}
	/** Returnerar den söka identifikationen
	 * 
	 * @return								sökta identifikaionen
	 */
	public int getTargetId(){
		return targetID;
	}
	/** Lägg till {@link Position} till vägen tillbaka till Requestets 
	 * skapelseposition.
	 * 
	 * @param pos							{@link Position} objekt som läggs till
	 * @see Position
	 */
	public void addPosToPathHome(Position pos){
		ShortestPath sp = this.getPathHome();
		sp.addDirection(pos);
		this.setPathHome(sp);
	}
}
