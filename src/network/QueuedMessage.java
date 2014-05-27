package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** QueuedMessage är ett barn av {@link Message}. Den används när meddelanden  
 * behöver sparas i en kö.
 * 
 * @author Henrik Sjöström
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
	 * @see {@link Position}
	 */
	private Position destination;
	/** Skapar ett QueuedMessage av ett annat {@link Message} objekt.
	 * 
	 * @param o							meddelandet som omvandlas
	 * @param pos						meddelandets position
	 * @see Position
	 */
	private Agent agent;
	private Request request;
	private Response response;
	public QueuedMessage(Object o, Position pos){
		if(o instanceof Agent){
			type = 1;
			agent = (Agent)o;
		} else if (o instanceof Request){
			type = 2;
			request = (Request)o;
		} else if (o instanceof Response){
			type = 3;
			response = (Response)o;
		}
	}
	public Agent getAgent(){
		   return agent;
	}
	public Request getRequest(){
		   return request;
	}
	public Response getResponse(){
		   return response;
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
