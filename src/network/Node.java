package network;

import java.util.Queue;

public class Node {
	private Queue<QueuedMessage> sendQueue;
	private Position position;
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
}
