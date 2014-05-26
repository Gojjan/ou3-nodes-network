package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** QueuedMessage �r ett barn av {@link Message}. Den anv�nds n�r meddelanden  
 * beh�ver sparas i en k�.
 * 
 * @author Henrik Sj�str�m
 * @version 1.0 Maj 26 2014
 * @see Node
 */
public class QueuedMessage extends Message{
	/** Vilken typ av meddelande som sparas. 
	 * 		1 = {@link Agent}
	 * 		2 = {@link Request}
	 * 		3 = {@link Response}
	 */
	private int type;
	/** Meddelandets destination
	 * @see Position
	 */
	private Position destination;
	/** Tabell med visiterade noder */
	private Hashtable visitedNodes;
	/** Tabell med meddelandets event */
	private Hashtable eventTable;
	/** Det funna {@link Event} objektet.
	 * @see Response
	 */
	private Event foundEvent;
	
	/** Skapar ett QueuedMessage av ett annat {@link Message} objekt.
	 * 
	 * @param o							meddelandet som omvandlas
	 * @param pos						meddelandets position
	 * @see Position
	 */
	public QueuedMessage(Object o, Position pos){
		if(o instanceof Agent){
			type = 1;
			Agent agent = (Agent)o;
			
			
		} else if (o instanceof Request){
			type = 2;
			Request request = (Request)o;
			
		} else if (o instanceof Response){
			type = 3;
			Response response = (Response)o;
		}
	}
	/** Returnerar meddelandets typ
	 * 		1 = {@link Agent}
	 * 		2 = {@link Request}
	 * 		3 = {@link Response} 
	 * @return							meddelandets typ (1,2 eller 3)
	 */
	public int getType(){
		return type;
	}
	/** Returnerar meddelandets desitnation.
	 * 
	 * @return							meddelandets destination
	 * @see Position
	 */
	public Position getDestination(){
		return destination;
	}
}
