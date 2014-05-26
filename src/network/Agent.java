package network;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Agent extends Message{
	private Hashtable eventTable = new Hashtable();
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	
	public Agent(Event event, int x, Position pos){
		ShortestPath sp = new ShortestPath(pos);
		eventTable.put(event.getID(), sp);
		this.setTimeToLive(x);
	}
	public Hashtable getEventTable(){
		return eventTable;
	}
	public void visitNode(Position pos){
		this.visitNodeI(pos);
	}
	public boolean visitedNode(Position pos){
		return this.visitedNodeI(pos);
	}
	public void setDefinedKeys(ArrayList<Integer> al){
		definedKeys = al;
	}
	public ArrayList<Integer> getDefinedKeys(){
		return definedKeys;
	}
	public void setEventTable(Hashtable hs){
		eventTable = hs;
	}

}
