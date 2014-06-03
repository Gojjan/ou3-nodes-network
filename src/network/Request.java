package network;

/** Klassen Request �r ett barn till {@link Message}. Det �r ett meddelande som 
 * efterfr�gar ett event med en viss identifikation. Den bygger p� rumor-routing 
 * algoritmen.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 */
public class Request extends Message{
	/** Identifikationen p� {@link Event} objektet som s�ks. */
	private int targetID;
	
	/** Skapar ett Request.
	 * 
	 * @param eventID						s�kta identifikationen
	 * @param timeToLive					tidssteg kvar
	 * @param pos							{@link Position} d�r den skapas
	 */
	public Request(int eventID, int timeToLive, Position pos){
		targetID = eventID;
		this.setTimeToLive(timeToLive);
		this.setPathHome(new ShortestPath(pos));
	}
	/** Returnerar den s�ka identifikationen
	 * 
	 * @return								s�kta identifikaionen
	 */
	public int getTargetId(){
		return targetID;
	}
	/** L�gg till {@link Position} till v�gen tillbaka till Requestets 
	 * skapelseposition.
	 * 
	 * @param pos							{@link Position} objekt som l�ggs till
	 * @see Position
	 */
	public void addPosToPathHome(Position pos){
		ShortestPath sp = this.getPathHome();
		sp.addDirection(pos);
		this.setPathHome(sp);
	}
}
