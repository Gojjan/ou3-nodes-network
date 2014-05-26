package network;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Agent är en implementation av en agent från rumor-routing algoritmen. 
 * 
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Agent extends Message{
	/** Tabell med händelser. */
	private Hashtable eventTable;
	/** */
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	
	/** Skapar en agent från rumor-routing algoritmen. 
	 * @param event
	 * @param x
	 * @param pos
	 * @see 					Event
	 * @see 					Position
	 */
	public Agent(Event event, int x, Position pos){
		
	}
	
	/** Returnerar tabellen med händelser. 
	 * @return eventTable		
	 */
	public Hashtable getEventTable(){
		return eventTable;
	}
	
	/** Undersöker om agenten har visiterat det sökta {@link Node} objektet.
	 * 
	 * @param pos
	 * @return
	 */
	public Boolean visitedNode(Position pos){
		return false;
	}
	
	/** 
	 * 
	 * @param al
	 */
	public void setDefinedKeys(ArrayList al){
		definedKeys = al;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList getDefinedKeys(){
		return definedKeys;
	}

}
