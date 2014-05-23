package network;

import java.util.ArrayList;
import java.util.Queue;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Node {
	private Hashtable eventTable;
	private Hashtable sendRequests;
	private boolean isRepeater;
	private boolean isHoldingMessage;
	private Position pos;
	private ArrayList neighbours;
	private Queue<QueuedMessage> sendQueue;
	private Position position;
	public Node(Position npos){
		
	}
	public void receiveMessage(Object o){
		if(o instanceof Agent){
			Agent agent = (Agent)o;
			agent.setTimeToLive(agent.getTimeToLive()-1);
			QueuedMessage qdMessage = new QueuedMessage(agent, position);
			sendQueue.add(qdMessage);
		} else if (o instanceof Request){
			
		} else if (o instanceof Response){
			
		}
	}
	public void timeTick(){
		
	}
	public void sendMessage(QueuedMessage qdm){
		
	}
	public boolean getIsHoldingMessage(){
		return isHoldingMessage;
	}
	public Position getPosition(){
		return pos;
	}
	public void setRepeater(){
		
	}
}
