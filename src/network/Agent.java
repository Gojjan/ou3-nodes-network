package network;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** Agent är en implementation av en agent från rumor-routing algoritmen. 
 * 
 * @author Viktor Bengtsson
 * @author Henrik Sjöström
 * @version 1.0 Maj 26 2014
 */
public class Agent extends Message{
	/** Tabell med händelser. */
	private Hashtable eventTable = new Hashtable();
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
		ShortestPath sp = new ShortestPath(pos);
		eventTable.put(event.getID(), sp);
		this.setTimeToLive(x);
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
	public void visitNode(Position pos){
		this.visitNodeI(pos);
	}
	public boolean visitedNode(Position pos){
		return this.visitedNodeI(pos);
	}
	
	/** 
	 * 
	 * @param al
	 */
	public void setDefinedKeys(ArrayList<Integer> al){
		definedKeys = al;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getDefinedKeys(){
		return definedKeys;
	}
	public void setEventTable(Hashtable hs){
		eventTable = hs;
	}

}
