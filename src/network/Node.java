package network;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Node{
	private Hashtable eventTable;
	private Hashtable sentRequestsHT;
	private ArrayList requests;
	private ArrayList events;
	private boolean isRepeater = false;
	private boolean isHoldingMessage;
	private Position pos;
	private ArrayList neighbours = new ArrayList();
	private Queue<QueuedMessage> sendQueue;
	private Position position;
	private ArrayList<Integer> definedKeys = new ArrayList<Integer>();
	private double eventChance;
	private double agentChance;
	private Network network;
	private ArrayList<Event> eventArrayList = new ArrayList<Event>();
	private int repeaterTime;
	
	public Node(Position npos, double eventchance, double agentchance, Network nnetwork){
		pos = npos;
		eventChance = eventchance;
		agentChance = agentchance;
		network = nnetwork;
		sendQueue = new LinkedList<QueuedMessage>();
		eventTable = new Hashtable();
		events = new ArrayList();
	}
	public void receiveMessage(Object o){
		if(o instanceof QueuedMessage){
			QueuedMessage qd = (QueuedMessage) o;
			if(qd.getType() == 1){
				Agent agent = (Agent)o;
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
				if(agent.getTimeToLive() > 1){
					agent.setEventTable(agentTable);
					//måste ändra så att den (om möjligt) skickar/köar till random granne den inte varit hos
					QueuedMessage qdMessage = new QueuedMessage(agent, position);
					sendQueue.add(qdMessage);
				}
			} else if (qd.getType() == 2){
				//Någonstans måste det läggas till så att den kollar om distance till eventet är noll från denna nod och då göra en response.
				Request request = (Request) o;
				Position nextpos;
				boolean isOnTrack = false;
				boolean foundEvent = false;
				for(int i = 0; i < definedKeys.size(); i++){
					if(definedKeys.get(i) == request.getTargetId()){
						isOnTrack = true;
						ShortestPath sp =  (ShortestPath) eventTable.get(definedKeys.get(i));
						if(sp.getDistance() == 0){
							foundEvent = true;
						}
					}
				}
				if(isOnTrack){
					ShortestPath sp = (ShortestPath) eventTable.get(request.getTargetId());
					nextpos = sp.getNextDirection();
					QueuedMessage qdMessage = new QueuedMessage(request, position);
					sendQueue.add(qdMessage);
				} else if (foundEvent != true){
					request.setTimeToLive(request.getTimeToLive()-1);
					if(request.getTimeToLive() > 1){
						request.addPosToPathHome(position);
						//måste ändra så att den (om möjligt) skickar/köar till random granne den inte varit hos
						QueuedMessage qdMessage = new QueuedMessage(request, position);
						sendQueue.add(qdMessage);
					}
				} else {
					
					//create Response
				}
			} else if (qd.getType() == 3){
				Response response = (Response) o;
				response.popNextPosition();
				if(response.getIsHome()){
					Event responseEvent = response.getEvent();
					Position eventpos = responseEvent.getHomePos();
					System.out.println("Event id: "+responseEvent.getID()+", event date of birth: "
							+responseEvent.getDateOfBirth()+", event place of birth: ["+eventpos.getX()+","+eventpos.getY()+"]\n");
				} else {
					Position nextpos = response.getNextPostion();
					QueuedMessage qdMessage = new QueuedMessage(response, nextpos);
					sendQueue.add(qdMessage);
				}
			}
		}
	}
	public void timeTick(){
		if(neighbours.isEmpty()){
			neighbours = network.checkNeighbours(pos);
		}
		if(isRepeater){
			//System.out.println("Node ["+pos.getX()+","+pos.getY()+"] is a repeater");
			
			//lower time by all sent requests by 1
			for(int i = 0; i < requests.size(); i++){
				
			}
			//check if some event hasn't returned a response 
			//check if it's time to send
			//create request
			if(repeaterTime == 0){
				repeaterTime = 400;
				Position nextpos = null;
				int requestID = network.getRequestID();
				boolean lucky = false;
				Request request = new Request(requestID, network.getRequestTimeToLive(), pos);
				for(int i = 0; i < definedKeys.size(); i++){
					if(requestID == definedKeys.get(i)){
						ShortestPath sp = (ShortestPath) eventTable.get(requestID);
						float distance = sp.getDistance();
						if(distance == 0){
							lucky = true;
						}else{
							nextpos = sp.getNextDirection();
						}
					}
				}
				if(nextpos == null){
					nextpos = (Position) neighbours.get((int) Math.random()*(neighbours.size()));
				}
				QueuedMessage qdm = new QueuedMessage(request, nextpos);
				if(!lucky){
					sendQueue.add(qdm);
				} else {
					//skriv ut skit
				}
			}
			if(repeaterTime > 0)
			repeaterTime--;
		}
		if(Math.random() <= eventChance){
			Event event = new Event(network.createUniqueID(),pos,network.getTime());
			events.add(event);
			ShortestPath sp = new ShortestPath(pos);
			eventTable.put(event.getID(), sp);
			
			if(Math.random() <= agentChance){
				Agent agent = new Agent(event, network.getAgentTimeToLive(), pos);
				//create agent
				//add agent to sendqueue
			}
		}
		if(sendQueue.poll() != null){
			QueuedMessage qdm = sendQueue.remove();
			sendMessage(qdm);
		}
	}
	public boolean sendMessage(QueuedMessage qdm){
		Position posa = qdm.getDestination();
		network.GetNodeAtPosition(posa).receiveMessage(qdm);
		return true;
	}
	public boolean getIsHoldingMessage(){
		return isHoldingMessage;
	}
	public Position getPosition(){
		return pos;
	}
	public void setRepeater(){
		isRepeater = true;
		requests = new ArrayList();
		sentRequestsHT = new Hashtable();
		repeaterTime = 400;
	}
	public boolean getRepeater(){
		return isRepeater;
	}
}
