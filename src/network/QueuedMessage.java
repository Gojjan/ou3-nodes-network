package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class QueuedMessage extends Message{
	private int type;
	private ShortestPath pathHome;
	private Position destination;
	private int timeToLive;
	private Hashtable visitedNodes;
	private Hashtable eventTable;
	private Event foundEvent;
	public QueuedMessage(Object o, Position pos){
		if(o instanceof Agent){
			type = 1;
			Agent agent = (Agent)o;
			
			
		} else if (o instanceof Request){
			type = 2;
			
		} else if (o instanceof Response){
			type = 3;
		}
	}
	public int getType(){
		return type;
	}
	public Position getDestination(){
		return destination;
	}

}
