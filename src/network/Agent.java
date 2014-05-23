package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Agent extends Message{
	private Hashtable eventTable;
	
	public Agent(Event event, int x, Position pos){
		
	}
	public Boolean visitedNode(Position pos){
		return false;
	}

}
