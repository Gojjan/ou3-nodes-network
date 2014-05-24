package network;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Agent extends Message{
	private Hashtable eventTable;
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	
	public Agent(Event event, int x, Position pos){
		
	}
	public Hashtable getEventTable(){
		return eventTable;
	}
	public Boolean visitedNode(Position pos){
		return false;
	}
	public void setDefinedKeys(ArrayList al){
		definedKeys = al;
	}
	public ArrayList getDefinedKeys(){
		return definedKeys;
	}

}
