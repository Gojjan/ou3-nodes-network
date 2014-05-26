package network;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class QueuedMessage extends Message{
	private int type;
	private ShortestPath pathHome;
	private Position destination;
	private int timeToLive;
	private Hashtable visitedNodes;
	private Hashtable eventTable;
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	private Event foundEvent;
	private int targetId;
	private boolean isOnTrack;
	public QueuedMessage(Object o, Position pos){
		if(o instanceof Agent){
			type = 1;
			Agent agent = (Agent)o;
			definedKeys = agent.getDefinedKeys();
			eventTable = agent.getEventTable();
			
		} else if (o instanceof Request){
			type = 2;
			Request request = (Request)o;
			timeToLive = request.getTimeToLive();
			pathHome = request.getPathHome();
			visitedNodes = request.getVisitedNodes();
			targetId = request.getTargetId();
			isOnTrack = request.getIsOnTrack();
			
		} else if (o instanceof Response){
			type = 3;
			Response response = (Response)o;
			timeToLive = response.getTimeToLive();
			pathHome = response.getPathHome();
			visitedNodes = response.getVisitedNodes();
			foundEvent = response.getEvent();
		}
	}
	public int getType(){
		return type;
	}
	public Position getDestination(){
		return destination;
	}
}
