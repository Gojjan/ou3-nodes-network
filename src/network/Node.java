package network;

import java.util.ArrayList;
import java.util.Enumeration;
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
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	public Node(Position npos){
		
	}
	public void receiveMessage(Object o){
		if(o instanceof Agent){
			Agent agent = (Agent)o;
			if(agent.getTimeToLive() > 1){
			agent.setTimeToLive(agent.getTimeToLive()-1);
			Hashtable agentTable = agent.getEventTable();
			ArrayList<Integer> agentKeys = agent.getDefinedKeys();
			for(int i = 0; i < agentKeys.size(); i++){
				ShortestPath sp0 = (ShortestPath) agentTable.get(agentKeys.get(i));
				sp0.addDirection(pos);
				agentTable.put(agentKeys.get(i), sp0);
				boolean defined = false;
				for(int j = 0; j < definedKeys.size(); j++){
					if(agentKeys.get(i) == definedKeys.get(j)){
						ShortestPath sp1 = (ShortestPath) agentTable.get(agentKeys.get(i));
						ShortestPath sp2 = (ShortestPath) eventTable.get(definedKeys.get(j));
						if(sp2.compareDistance(sp1) > 0){
							//sp1 is shorter
							eventTable.put(definedKeys.get(j), sp1);
						} else if(sp2.compareDistance(sp1) < 0){
							//sp1 is longer
							agentTable.put(agentKeys.get(i), sp2);
						}
						defined = true;
					}
				}
				if(!defined){
					eventTable.put(agentKeys.get(i), sp0);
				}
			}
			for(int i = 0; i < definedKeys.size(); i++){
				boolean defined = false;
				for(int j = 0; j < agentKeys.size(); j++){
					if(definedKeys.get(i) == agentKeys.get(j)){
						ShortestPath sp1 = (ShortestPath) eventTable.get(definedKeys.get(i));
						ShortestPath sp2 = (ShortestPath) agentTable.get(agentKeys.get(j));
						if(sp2.compareDistance(sp1) > 0){
							//sp1 is shorter
							eventTable.put(definedKeys.get(j), sp1);
						} else if(sp2.compareDistance(sp1) < 0){
							//sp1 is longer
							agentTable.put(agentKeys.get(i), sp2);
						}
						defined = true;
					}
				}
				if(!defined){
					agentTable.put(definedKeys.get(i), eventTable.get(definedKeys.get(i)));
				}
			}
			/*ArrayList<Integer> agentKeys = new ArrayList<Integer>();
			Enumeration keyenum = agentTable.keys();
			while(keyenum.hasMoreElements()){
				Object element = keyenum.nextElement();
				agentKeys.add((Integer)element);
			}
			
			for(int i = 0; i < agentKeys.size(); i++){
				
			}*/
			QueuedMessage qdMessage = new QueuedMessage(agent, position);
			sendQueue.add(qdMessage);
			
			}
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
